package me.oskarmendel.util.flac;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

/**
 * 
 * @author Oskar
 *
 */
public abstract class AbstractFlacInputStream implements FlacInputStream {
	
	private static byte[] CRC8_TABLE  = new byte[256];
	private static char[] CRC16_TABLE = new char[256];
	
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
	 * 
	 */
	public AbstractFlacInputStream() {
		this.byteBuffer = new byte[4096];
		this.positionChanged(0);
	}
	
	/**
	 * 
	 * @param position
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
	 * 
	 */
	public int readSignedInt(int n) throws IOException {
		int bitShift = 32 - n;
		return (readUnsignedInt(n) << bitShift) >> bitShift;
	}
	
	/**
	 * 
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
	 * 
	 * @throws IOException
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
	 * 
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
	 * 
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
	 * 
	 * @return
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
	 * 
	 * @param buf
	 * @param off
	 * @param len
	 * @return
	 * @throws IOException
	 */
	protected abstract int readUnderlying(byte[] buf, int off, int len) throws IOException;
	
	/**
	 * 
	 * @return
	 */
	private boolean checkByteAligned() {
		if (bitBufferLength % 8 != 0) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
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
	 * 
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
	 * 
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
	
	/*
	 * 
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
	 * 
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
	
	/**
	 * 
	 */
	public long getPosition() {
		return this.byteBufferStartPosition + this.byteBufferIndex 
				- (this.bitBufferLength + 7) / 8;
	}
	
	/**
	 * 
	 */
	public int getBitPosition() {
		return (-this.bitBufferLength) & 7;
	}
	
	/**
	 * 
	 */
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
