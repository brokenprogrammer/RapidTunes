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
		// positionChanged(0);
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
		this.resetCrc();
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
		
		this.bitBufferLength = this.bitBufferLength - n;
		
		return result;
	}
	
	/**
	 * 
	 */
	public int readSignedInt(int n) throws IOException {
		return 0;
	}
	
	/**
	 * 
	 */
	public void readRiceSignedInts(int param, long[] result, int start, int end) throws IOException {
		
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	private void fillBitBuffer() throws IOException {
		
	}
	
	/**
	 * 
	 */
	public int readByte() throws IOException {
		return 0;
	}
	
	/**
	 * 
	 */
	public void readAll(byte[] b) throws IOException {
		
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private int readUnderlying() throws IOException {
		return 0;
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
		
	}
	
	/**
	 * 
	 */
	public int getCrc8() {
		return 0;
	}
	
	/**
	 * 
	 */
	public int getCrc16() {
		return 0;
	}
	
	/*
	 * 
	 */
	private void updateCrcs(int trailingBytes) {
		
	}
	
	/**
	 * 
	 */
	public void close() throws IOException {
		
	}
	
	/**
	 * 
	 */
	public long getPosition() {
		return this.byteBufferStartPosition + this.byteBufferIndex 
				- (this.byteBufferLength + 7) / 8;
	}
	
	/**
	 * 
	 */
	public int getBitPosition() {
		return (-this.bitBufferLength) & 7;
	}
}
