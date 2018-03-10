/**
 * RapidTunes.
 * The music application to help you use all your music sources in one place.
 *
 * The MIT License (MIT)
 *
 * Copyright (C) 2018 The RapidTunes
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.oskarmendel.util.song.flac.inputstream;

import java.io.IOException;

/**
 * A input stream with additional functionality specifically to aid in
 * decoding FLAC files. Functionality specific for FLAC includes methods such 
 * as CRC calculation and Rice decoding.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FlacInputStream.java
 */
public interface FlacInputStream extends AutoCloseable{

	/**
	 * Returns the total number of bytes in the Flac file that this
	 * input stream is reading. 
	 * 
	 * @return - Total number of bytes in underlying file.
	 */
	public long getLength();
	
	/**
	 * Returns the current byte position of the stream.
	 * 
	 * @return - Current byte position of the stream.
	 */
	public long getPosition();
	
	/**
	 * Returns the current number of read bits in the current byte.
	 * This is in a range from 0 - 7
	 * 
	 * @return - Current number of bits read in current byte.
	 */
	public int getBitPosition();
	
	/**
	 * Changes the position of the input stream to the specific byte offset
	 * from the start of the stream.
	 * This also resets the CRCs and the bit position.
	 * 
	 * @param position - Byte offset to change the stream to.
	 * 
	 * @throws IOException - TODO
	 */
	public void seekTo(long position) throws IOException;
	
	/**
	 * Reads the next specified number of bits as an unsigned integer.
	 * 
	 * @param n - Number of bits to read.
	 * 
	 * @return - An unsigned integer read with the specified number of bits.
	 * 
	 * @throws IOException - TODO
	 */
	public int readUnsignedInt(int n) throws IOException;
	
	/**
	 * Reads the next specified number of bits as a signed integer.
	 * 
	 * @param n - Number of bits to read.
	 * 
	 * @return - An signed integer read with the specified number of bits.
	 * 
	 * @throws IOException - TODO
	 */
	public int readSignedInt(int n) throws IOException;
	
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
	public void readRiceSignedInts(int param, long[] result, int start, int end) throws IOException;

	/**
	 * Reads the next unsigned byte value.
	 * 
	 * @return - The next byte as an unsigned byte value in the range of 0 - 255.
	 * 
	 * @throws IOException - TODO
	 * @throws IllegalStateException - If the input stream is at an invalid byte boundary.
	 */
	public int readByte() throws IOException;
	
	/**
	 * Reads the specified byte array fully.
	 * 
	 * @param b - Byte array to read all the bytes into.
	 * 
	 * @throws IOException - TODO
	 * @throws IllegalStateException - If the input stream is at an invalid byte boundary.
	 */
	public void readAll(byte[] b) throws IOException;
	
	/**
	 * Sets the current byte position to the start of the CRC calculations.
	 * 
	 * @throws IllegalStateException - If the input stream is at an invalid byte boundary.
	 */
	public void resetCrcs();
	
	/**
	 * Returns the CRC8 hash of the read bytes. 
	 * 
	 * @return - CRC8 hash of the read bytes.
	 * 
	 * @throws IllegalStateException - If the input stream is at an invalid byte boundary.
	 */
	public int getCrc8();
	
	/**
	 * Returns the CRC16 hash of the read bytes.
	 * 
	 * @return - CRC16 hash of the read bytes.
	 * 
	 * @throws IllegalStateException - If the input stream is at an invalid byte boundary.
	 */
	public int getCrc16();
	
	/**
	 * Closes the underlying objects and resources and discards the buffers.
	 * Calling this invalidates the input stream and it is then forbidden to call its members.
	 */
	public void close() throws IOException;
}
