package me.oskarmendel.util.flac;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

/**
 * An abstract implementation of the FlacInputStream.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name AbstractFlacInputStream.java
 */
public abstract class AbstractFlacInputStream implements FlacInputStream {
	
	private static final int BYTE_BUFFER_SIZE = 4096;
	private static final int CRC_TABLE_SIZE = 256;
	
	private static byte[] CRC8_TABLE  = new byte[CRC_TABLE_SIZE];
	private static char[] CRC16_TABLE = new char[CRC_TABLE_SIZE];
	
	//TODO: These must be changed into something sane.
	private static final int RICE_DECODING_TABLE_BITS = 13;  // Configurable, must be positive
	private static final int RICE_DECODING_TABLE_MASK = (1 << RICE_DECODING_TABLE_BITS) - 1;
	private static final byte[][] RICE_DECODING_CONSUMED_TABLES = new byte[31][1 << RICE_DECODING_TABLE_BITS];
	private static final int[][] RICE_DECODING_VALUE_TABLES = new int[31][1 << RICE_DECODING_TABLE_BITS];
	private static final int RICE_DECODING_CHUNK = 4;  // Configurable, must be positive, and RICE_DECODING_CHUNK * RICE_DECODING_TABLE_BITS <= 64
	
	private long byteBufferStartPosition;
	private byte[] byteBuffer;
	private int byteBufferLength;
	private int byteBufferIndex;
	
	private long bitBuffer;
	private int bitBufferLength;
	
	private int crc8;
	private int crc16;
	private int crcStartIndex;
	
	/**
	 * Constructs a new AbstractFlacInputStream, this initializes the 
	 * bytes buffer and resets the position to 0.
	 */
	public AbstractFlacInputStream() {
		this.byteBuffer = new byte[BYTE_BUFFER_SIZE];
		this.positionChanged(0);
	}
	
	/**
	 * When child class handles seeking this method must be used to flush
	 * the buffers from incoming data.
	 * 
	 * @param position - Target position to change the buffer start position to.
	 */
	protected void positionChanged(long position) {
		this.byteBufferStartPosition = position;
		Arrays.fill(byteBuffer, (byte)0);
		byteBufferLength = 0;
		byteBufferIndex = 0;
		bitBuffer = 0;
		bitBufferLength = 0;
		this.resetCrcs();
	}
	
	/**
	 * Reads the next specified number of bits as an unsigned integer.
	 * 
	 * @param n - Number of bits to read.
	 * 
	 * @return - An unsigned integer read with the specified number of bits.
	 * 
	 * @throws IOException - TODO
	 */
	public int readUnsignedInt(int n) throws IOException {
		if (n < 0 || n > 32) {
			throw new IllegalArgumentException();
		}
		
		while (this.bitBufferLength < n) {
			int b = readUnderlying();
			if (b == -1) {
				throw new EOFException();
			}
			
			this.bitBuffer = (this.bitBuffer << 8) | b;
			this.bitBufferLength += 8;
		}
		
		int result = (int)(this.bitBuffer >>> (this.bitBufferLength - n));
		if (n != 32) {
			result &= (1 << n) - 1;
		}
		
		this.bitBufferLength -= n;
		
		return result;
	}
	
	/**
	 * Reads the next specified number of bits as a signed integer.
	 * 
	 * @param n - Number of bits to read.
	 * 
	 * @return - An signed integer read with the specified number of bits.
	 * 
	 * @throws IOException - TODO
	 */
	public int readSignedInt(int n) throws IOException {
		int bitShift = 32 - n;
		return (readUnsignedInt(n) << bitShift) >> bitShift;
	}
	
	/**
	 * Reads and decodes the next batch of Rice-coded signed integers.
	 * Rice-coded integers might read a very large number of bits from the underlying stream.
	 * 
	 * @param param - The encoding parameter for the rice partition.
	 * @param result - The array to store the result within.
	 * @param start - Start index of the result.
	 * @param end - End index of the result.
	 * 
	 * @throws IOException - TODO
	 */
	public void readRiceSignedInts(int param, long[] result, int start, int end) throws IOException {
		if (param < 0 || param > 31) {
			throw new IllegalArgumentException("TODO: ");
		}
		
		long unaryLimit = 1L << (53 - param);
		byte[] consumeTable = RICE_DECODING_CONSUMED_TABLES[param];
		int[] valueTable = RICE_DECODING_VALUE_TABLES[param];
		while(true) {
			outerLoop:
			
			while (start <= end - RICE_DECODING_CHUNK) {
				if (this.bitBufferLength < RICE_DECODING_CHUNK * RICE_DECODING_TABLE_BITS) {
					if (this.byteBufferIndex <= this.byteBufferLength - 8) {
						this.fillBitBuffer();
					} else {
						break;
					}
				}
				
				for (int i = 0; i < RICE_DECODING_CHUNK; i++, start++) {
					int extracted = (int)(bitBuffer >>> (bitBufferLength - RICE_DECODING_TABLE_BITS)) 
							& RICE_DECODING_TABLE_MASK;
					int consumed = consumeTable[extracted];
					
					if (consumed == 0) {
						break outerLoop;
					}
					
					this.bitBufferLength -= consumed;
					result[start] = valueTable[extracted];
				}
			}
			
			if (start >= end) {
				break;
			}
			
			long value = 0;
			
			while (this.readUnsignedInt(1) == 0) {
				if (value >= unaryLimit) {
					// Decoded data is too large.
					throw new IOException("Residual value is too large."); //TODO: Create dataformatexception.
				}
				
				value++;
			}
			
			value = (value << param) | this.readUnsignedInt(param);
			
			if ((value >>> 53) != 0) {
				throw new IllegalArgumentException("TODO: ");
			}
			
			value = (value >>> 1) ^ -(value & 1);
			
//			if ((value >> 52) != 0 || (value >> 52) != -1) {
//				throw new IllegalArgumentException("TODO: ");
//			}
			
			result[start] = value;
			start++;
		}
	}
	
	/**
	 * Appends bits to the bit buffer to fill it properly.
	 * 
	 * @throws IOException - TODO
	 */
	private void fillBitBuffer() throws IOException {
		int index = this.byteBufferIndex;
		int n = Math.min(64 - this.bitBufferLength >>> 3, this.byteBufferLength - index);
		byte[] b = this.byteBuffer;
		
		if (n > 0) {
			for (int i = 0; i < n; i++, index++) {
				this.bitBuffer = (this.bitBuffer << 8) | b[index] & 0xFF;
			}
			this.bitBufferLength += n << 3;
		} else if (this.bitBufferLength <= 56) {
			int t = readUnderlying();
			if (t == -1) {
				throw new EOFException();
			}
			this.bitBuffer = (this.bitBuffer << 8) | t;
			this.bitBufferLength += 8;
		}
		
		if (!(8 <= this.bitBufferLength && this.bitBufferLength <= 64)) {
			throw new IllegalStateException("TODO: ");
		}
		
		this.byteBufferIndex += n;
	}
	
	/**
	 * Reads the next unsigned byte value.
	 * 
	 * @return - The next byte as an unsigned byte value in the range of 0 - 255.
	 * 
	 * @throws IOException - TODO
	 * @throws IllegalStateException - If the input stream is at an invalid byte boundary.
	 */
	public int readByte() throws IOException {
		if (!this.checkByteAligned()) {
			throw new IllegalStateException("Not at a byte boundary.");
		}
		
		if (this.bitBufferLength >= 8) {
			return readUnsignedInt(8);
		} else {
			if (this.bitBufferLength != 0) {
				throw new IllegalStateException("TODO: ");
			}
			return readUnderlying();
		}
	}
	
	/**
	 * Reads the specified byte array fully.
	 * 
	 * @param b - Byte array to read all the bytes into.
	 * 
	 * @throws IOException - TODO
	 * @throws IllegalStateException - If the input stream is at an invalid byte boundary.
	 */
	public void readAll(byte[] b) throws IOException {
		if (b == null) {
			throw new IllegalArgumentException("Byte array is not initialized.");
		}
		if (!this.checkByteAligned()) {
			throw new IllegalStateException("Not at a byte boundary.");
		}
		
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte)readUnsignedInt(8);
		}
	}
	
	/**
	 * Reads a byte from the byte buffer returning a unsigned integer from the read byte.
	 * 
	 * @return - A read unsigned 8 bit integer and -1 on failure.
	 * 
	 * @throws IOException
	 */
	private int readUnderlying() throws IOException {
		if (this.byteBufferIndex >= byteBufferLength) {
			if (this.byteBufferLength == -1) {
				return -1;
			}
			
			this.byteBufferStartPosition += this.byteBufferLength;
			this.updateCrcs(0);
			this.byteBufferLength = readUnderlying(this.byteBuffer, 0, this.byteBuffer.length);
			this.crcStartIndex = 0;
			
			if (this.byteBufferLength <= 0) {
				return -1;
			}
			
			this.byteBufferIndex = 0;
		}
		
		int tmp = this.byteBuffer[this.byteBufferIndex] & 0xFF;
		this.byteBufferIndex++;
		return tmp;
	}
	
	/**
	 * Abstract readUnderlying method that allows for child's to specify how to read
	 * a byte from its underlying byte buffer.
	 * 
	 * @param buf - The buffer into which the data is read.
	 * @param off - The start offset in array b at which the data is written.
	 * @param len - The maximum number of bytes read.
	 * 
	 * @return - the total number of bytes read into the buffer, 
	 * 	or -1 if there is no more data because the end of the file has been reached.
	 * 
	 * @throws IOException - TODO
	 */
	protected abstract int readUnderlying(byte[] buf, int off, int len) throws IOException;
	
	/**
	 * Checks if the byte is aligned.
	 * 
	 * @return True if it is aligned; False otherwise.
	 */
	private boolean checkByteAligned() {
		if (bitBufferLength % 8 != 0) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Sets the current byte position to the start of the CRC calculations.
	 * 
	 * @throws IllegalStateException - If the input stream is at an invalid byte boundary.
	 */
	public void resetCrcs() {
		if (!this.checkByteAligned()) {
			throw new IllegalStateException("Not at a byte boundary.");
		}
		
		this.crcStartIndex = this.byteBufferIndex - this.bitBufferLength / 8;
		this.crc8 = 0;
		this.crc16 = 0;
	}
	
	/**
	 * Returns the CRC8 hash of the read bytes. 
	 * 
	 * @return - CRC8 hash of the read bytes.
	 * 
	 * @throws IllegalStateException - If the input stream is at an invalid byte boundary.
	 */
	public int getCrc8() {
		if (!this.checkByteAligned()) {
			throw new IllegalStateException("Not at a byte boundary.");
		}
		
		this.updateCrcs(this.bitBufferLength / 8);
		if ((this.crc8 >>> 8) != 0) {
			throw new IllegalStateException("TODO:");
		}
		
		return this.crc8;
	}
	
	/**
	 * Returns the CRC16 hash of the read bytes.
	 * 
	 * @return - CRC16 hash of the read bytes.
	 * 
	 * @throws IllegalStateException - If the input stream is at an invalid byte boundary.
	 */
	public int getCrc16() {
		if (!this.checkByteAligned()) {
			throw new IllegalStateException("Not at a byte boundary.");
		}
		
		this.updateCrcs(this.bitBufferLength / 8);
		if ((this.crc16 >>> 16) != 0) {
			throw new IllegalStateException("TODO:");
		}
		
		return this.crc16;
	}
	
	/**
	 * Updates the two CRC hashes using the data within the byte buffer.
	 * 
	 * @param trailingBytes - The number of trailing unused bytes within the buffer.
	 */
	private void updateCrcs(int trailingBytes) {
		int end = this.byteBufferIndex - trailingBytes;
		
		for (int i = this.crcStartIndex; i < end; i++) {
			int b = this.byteBuffer[i] & 0xFF;
			
			crc8 = CRC8_TABLE[crc8 ^ b] & 0xFF;
			crc16 = CRC16_TABLE[(crc16 >>> 8) ^ b] ^ ((crc16 & 0xFF) << 8);
			
			if (((this.crc8 >>> 8) != 0) && ((this.crc16 >>> 16) != 0)) {
				throw new IllegalStateException("TODO:");
			}
		}
		
		this.crcStartIndex = end;
	}
	
	/**
	 * Returns the current byte position of the stream.
	 * 
	 * @return - Current byte position of the stream.
	 */
	public long getPosition() {
		return this.byteBufferStartPosition + this.byteBufferIndex 
				- (this.bitBufferLength + 7) / 8;
	}
	
	/**
	 * Returns the current number of read bits in the current byte.
	 * This is in a range from 0 - 7
	 * 
	 * @return - Current number of bits read in current byte.
	 */
	public int getBitPosition() {
		return (-this.bitBufferLength) & 7;
	}
	
	/**
	 * Closes the underlying objects and resources and discards the buffers.
	 * Calling this invalidates the input stream and it is then forbidden to call its members.
	 */
	public void close() throws IOException {
		this.byteBuffer = null;
		this.byteBufferLength = -1;
		this.byteBufferIndex = -1;
		this.bitBuffer = 0;
		this.bitBufferLength = -1;
		this.crc8 = -1;
		this.crc16 = -1;
		this.crcStartIndex = -1;
	}
	
	static {
		for (int i = 0; i < CRC8_TABLE.length; i++) {
			int c8 = i;
			int c16 = i << 8;
			
			for (int j = 0; j < 8; j++) {
				c8 = (c8 << 1) ^ ((c8 >>> 7) * 0x107);
				c16 = (c16 << 1) ^ ((c16 >>> 15) * 0x18005);
			}
			
			CRC8_TABLE[i] = (byte)c8;
			CRC16_TABLE[i] = (char)c16;
		}
	}
	
	static {
		for (int p = 0; p < RICE_DECODING_CONSUMED_TABLES.length; p++) {
			byte[] consumed = RICE_DECODING_CONSUMED_TABLES[p];
			int[] values = RICE_DECODING_VALUE_TABLES[p];
			for (int i = 0;; i++) {
				int numBits = (i >>> p) + 1 + p;
				
				if (numBits > RICE_DECODING_TABLE_BITS) {
					break;
				}
				
				int bits = ((1 << p) | (i & ((1 << p) - 1)));
				int bitShift = RICE_DECODING_TABLE_BITS - numBits;
				
				for (int j = 0; j < (1 << bitShift); j++) {
					consumed[(bits << bitShift) | j] = (byte)numBits;
					values[(bits << bitShift) | j] = (i >>> 1) ^ -(i & 1);
				}
			}
			
			if (consumed[0] != 0) {
				throw new AssertionError("TODO: ");
			}
		}
	}
}
