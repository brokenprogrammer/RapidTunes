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

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class to handle binary operations connected to reading binary files.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name BinaryUtil.java
 */
public class BinaryUtil {
	
	/**
	 * Reads and populates b with the next bytes within the InputStream.
	 * If end of file was reached a detailed EOFException will be thrown.
	 * 
	 * @param input - InputStream of the file to read.
	 * @param b - Byte array to populate with the next bytes within the InputStream.
	 * @throws IOException - When the first byte cannot be read for any other reason than EOF.
	 */
	public static void readBytes(InputStream input, byte[] b) throws IOException{
		readBytes(input, b, 0, b.length);
	}
	
	/**
	 * Reads and populates b with the next bytes within the InputStream.
	 * If end of file was reached a detailed EOFException will be thrown.
	 * 
	 * @param input - InputStream of the file to read.
	 * @param b - Byte array to populate with the next bytes within the InputStream.
	 * @param off - The start offset in array b at which the data is written.
	 * @param len - Max number of bytes to read.
	 * @throws IOException - When the first byte cannot be read for any other reason than EOF.
	 */
	public static void readBytes(InputStream input, byte[] b, int off, int len) throws IOException {
		int totalReadBytes = 0;
		int readBytes;
		
		while (totalReadBytes < len) {
			readBytes = input.read(b, off+totalReadBytes, len-totalReadBytes);
			
			//The InputStream reached end off file
			if(readBytes == -1) {
				throw new EOFException("Reached end of file while reading " + len + " bytes from"
						+ "the offset " + off + " and hit EOF at " + totalReadBytes);
			}
			
			totalReadBytes += readBytes;
		}
	}
}
