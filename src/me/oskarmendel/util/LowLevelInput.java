/**
 * RapidTunes.
 * The music application to help you use all your music sources in one place.
 *
 * The MIT License (MIT)
 *
 * Copyright (C) 2016 The RapidTunes
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

package me.oskarmendel.util;

import java.io.IOException;

/**
 * Interface defining methods for low level input mainly focusing
 * methods that handles bit reading.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name LowLevelInput.java
 */
public interface LowLevelInput {
	
	/**
	 * Reads the next specified number of bits as an unsigned integer.
	 * 
	 * @param n - Number of bits to read.
	 * 
	 * @return - Unsigned integer built from read bits.
	 * 
	 * @throws IOException 
	 */
	public int readUnsignedInt(int n) throws IOException;
	
	/**
	 * Reads the next specified number of bits as a signed integer.
	 * 
	 * @param n - Number of bits to read.
	 * 
	 * @return - Signed integer built from read bits.
	 * 
	 * @throws IOException
	 */
	public int readSignedInt(int n) throws IOException;
	
	/**
	 * Reads the next unsigned byte value (0 - 255).
	 * 
	 * @return - Byte value ranging from 0 - 255.
	 * 
	 * @throws IOException
	 */
	public int readByte() throws IOException;
	
	/**
	 * Closes the input stream by invalidating it and / or freeing all the 
	 * resources used. 
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException;
	
	/**
	 * Changes the position to read from to the specified position.
	 * 
	 * @param position - Target position to move the input stream to.
	 * 
	 * @throws IOException
	 */
	public void seekTo(long position) throws IOException;
	
	/**
	 * Getter for the current byte position within the input stream.
	 * 
	 * @return - Current byte position within the input stream.
	 */
	public long getPosition();
	
	/**
	 * Getter for the current bit position within the input stream.
	 * This returns the number of read bits in the current byte.
	 * 
	 * @return - Current bit position within the input stream.
	 */
	public int getBitPosition();
	
	/**
	 * Getter for the total number of bytes available in the current
	 * input stream.
	 * 
	 * @return - Total number of bytes within the current input stream.
	 */
	public int getLength();
}
