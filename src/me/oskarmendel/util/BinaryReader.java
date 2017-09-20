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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class that provides helper methods to read individual bits and bytes
 * from files.
 * TODO: Tests and javadoc.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name BinaryReader.java
 */
public class BinaryReader implements LowLevelInput{
	
	private InputStream input;
	
	private long bitBuffer;
	private int bitBufferLen;
	
	private int readBytes;
	
	/**
	 * Constructs a new BinaryReader using the specified file 
	 * and opens a connection to it using an InputStream. Then proceeds 
	 * with initializing all members to default values.
	 * 
	 * @param file - File to open InputStream to.
	 * 
	 * @throws FileNotFoundException - If the file does not exist, 
	 *  	is a directory rather than a regular file.
	 */
	public BinaryReader(File file) throws FileNotFoundException {
		this.input = new BufferedInputStream(new FileInputStream(file));
		
		this.bitBuffer = 0;
		this.bitBufferLen = 0;
		this.readBytes = 0;
	}
	
	/**
	 * Constructs a new BinaryReader using the specified InputStream
	 * and proceeds with initializing all members to default values.
	 * 
	 * @param input - InputStream to read bits & bytes from.
	 */
	public BinaryReader(InputStream input) {
		this.input = input;
		
		this.bitBuffer = 0;
		this.bitBufferLen = 0;
		this.readBytes = 0;
	}

	/**
	 * Reads the next specified number of bits as an unsigned integer.
	 * 
	 * @param n - Number of bits to read.
	 * 
	 * @return - Unsigned integer built from read bits.
	 * 
	 * @throws IOException 
	 */
	@Override
	public int readUnsignedInt(int n) throws IOException {
		if (n < 0 || n > 32) {
			throw new IllegalArgumentException("Specified number of bits (" + 
					n + ") does not fit in an unsigned integer.");
		}
		
		while (this.bitBufferLen < n) {
			int t = input.read();
			this.readBytes++;
			
			if (t == -1) {
				throw new IllegalStateException("Reached EOF.");
			}
			
			this.bitBuffer = (this.bitBuffer << 8) | t;
			this.bitBufferLen += 8;
		}
		
		int res = (int) this.bitBuffer >>> (this.bitBufferLen - n);
		
		if (n != 32) {
			res &= (1 << n) - 1;
			if ((res >>> n) != 0) {
				throw new IllegalStateException("Invalid result recieved when reading bits.");
			}
		}
			
		this.bitBufferLen -= n;
		return res;
	}

	/**
	 * Reads the next specified number of bits as a signed integer.
	 * 
	 * @param n - Number of bits to read.
	 * 
	 * @return - Signed integer built from read bits.
	 * 
	 * @throws IOException
	 */
	@Override
	public int readSignedInt(int n) throws IOException {
		int s = 32 - n;
		
		return (readUnsignedInt(n) << s) >> s;
	}

	/**
	 * Reads the next unsigned byte value (0 - 255).
	 * 
	 * @return - Byte value ranging from 0 - 255.
	 * 
	 * @throws IOException
	 */
	@Override
	public int readByte() throws IOException {
		if (this.bitBufferLen >= 8) {
			return readUnsignedInt(8);
		} else {
			this.readBytes++;
			return input.read();
		}
	}

	/**
	 * Closes the input stream by invalidating it and / or freeing all the 
	 * resources used. 
	 * 
	 * @throws IOException
	 */
	@Override
	public void close() throws IOException {
		input.close();
		this.input = null;
		this.bitBuffer = -1;
		this.bitBufferLen = -1;
		this.readBytes = -1;
	}

	/**
	 * Changes the position to read from to the specified position.
	 * 
	 * @param position - Target position to move the input stream to.
	 * 
	 * @throws IOException
	 */
	@Override
	public void seekTo(long position) throws IOException {
		if (position < this.readBytes) {
			// Throw error, cannot seek backwards?
		}
		
		this.input.skip(position - this.readBytes);
		this.readBytes += (position - this.readBytes);
	}

	/**
	 * Getter for the current byte position within the input stream.
	 * 
	 * @return - Current byte position within the input stream.
	 */
	@Override
	public long getPosition() {
		return this.readBytes;
	}

	/**
	 * Getter for the current bit position within the input stream.
	 * This returns the number of read bits in the current byte.
	 * If the BinaryReader is invalidated (closed) this will return -1 instead.
	 * 
	 * @return - Current bit position within the input stream.
	 */
	@Override
	public int getBitPosition() {
		if (this.bitBufferLen != -1) {
			return (-this.bitBufferLen) & 7;
		} else {
			return -1;
		}
	}

	/**
	 * Getter for the total number of bytes available in the current
	 * input stream.
	 * 
	 * @return - Total number of bytes within the current input stream.
	 */
	@Override
	public int getLength() {
		int len = -1;
		
		if (this.input != null) {
			try {
				len = input.available();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return len;
	}
	
	/**
	 * Getter for the underlying InputStream of this BinaryReader.
	 * 
	 * @return - InputStream of this BinaryReader.
	 */
	public InputStream getInputStream() {
		return this.input;
	}
}
