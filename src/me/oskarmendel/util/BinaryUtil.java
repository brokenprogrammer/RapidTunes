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
import java.nio.charset.Charset;

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
	public static void readBytes(InputStream input, byte[] b) throws IOException {
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
	
	/**
	 * Converts an integer to a signed byte. 
	 * 
	 * Note: In Java a byte is a signed 8-bit whose range is -128 to 127.
	 * But you can use all 256 values in the byte so this method the 
	 * integer i signed by doing i - 256 if necessary. 
	 * 
	 * @param i - Integer to convert to byte.
	 * @return The integer i converted to a byte.
	 */
	public static byte intToByte(int i) {
		//The given integer is too large for a byte.
		if (i > 256) {
			throw new IllegalArgumentException("Integer is too large for a byte.");
		}
		
		if (i > 127) {
			return (byte)(i-256);
		}
		
		return (byte)i;
	}
	
	/**
	 * Converts a byte into an integer.
	 * 
	 * @param b - Byte to convert into an integer value.
	 * @return An integer converted from the byte b.
	 */
	public static int byteToInt(byte b) {
		//If the byte is larger than 127 the value will be negative. (Byte range -128 to 127)
		if (b < 0) {
			return b & 0xFF;
		}
		return b;
	}
	
	
	/**
	 * Retrieves the bit on the target position.
	 * 
	 * Example: If we have the number 5 in binary, 00000101 and we want to
	 * get a certain bit in a specific position of this byte. We use this method
	 * together with its position counted from the right. In the binary number 
	 * provided in this example getting the bits at position 0 and 2 would return 1.
	 * 
	 * @param b - Byte to retrieve bit from.
	 * @param position - Position of target bit.
	 * @return Bit at the target position of the byte.
	 */
	public static int getBitAt(byte b, int position) {
		return (b >> position) & 1;
	}
	
	/**
	 * Retrieves the bit on the target position using Big-Endian approach.
	 * 
	 * Note: This is same as the getBitAt method except this method starts to 
	 * count from the left. Example: 10000100 at position 0 and 5 this method would
	 * return 1. 
	 * 
	 * @param b - Byte to retrieve bit from.
	 * @param position - Position of target bit.
	 * @return Bit at the target position of the byte.
	 */
	public static int getBitAtBE(byte b, int position) {
		return (b >> (7 - position)) & 1;
	}
	
	/**
	 * Combines two bytes into a number.
	 * 
	 * Note: When a number is too small to be stored in one byte it will
	 * need another byte and this method converts such a number from 
	 * bytes into an integer.
	 * 
	 * @param x1
	 * @param x2
	 * @return Integer constructed with all the byte values.
	 */
	public static int addBytesToInt(int x1, int x2) {
		return (x2 << 8) | (x1 & 0xFF);
	}
	
	/**
	 * Combines three bytes into a number.
	 * 
	 * @param x1
	 * @param x2
	 * @param x3
	 * @return long constructed with all the byte values.
	 */
	public static long addBytesToInt(int x1, int x2, int x3) {
		return (x3 << 16) | (x2 << 8) | (x1 & 0xFF);
	}
	
	/**
	 * Combines four bytes into a number.
	 * 
	 * @param x1
	 * @param x2
	 * @param x3
	 * @param x4
	 * @return Integer constructed with all the byte values.
	 */
	public static long addBytesToInt(int x1, int x2, int x3, int x4) {
		return (x3 << 32) | (x3 << 16) | (x2 << 8) | (x1 & 0xFF);
	}
	
	/**
	 * Combines two bytes into a number using Big-Endian approach.
	 * 
	 * @param x1
	 * @param x2
	 * @return Integer constructed with all the byte values.
	 */
	public static int addBytesToIntBE(int x1, int x2) {
		return (x1 << 8) | (x2 & 0xFF);
	}
	
	/**
	 * Combines three bytes into a number using Big-Endian approach.
	 * 
	 * @param x1
	 * @param x2
	 * @param x3
	 * @return long constructed with all the byte values.
	 */
	public static long addBytesToIntBE(int x1, int x2, int x3) {
		return (x1 << 16) | (x2 << 8) | (x3 & 0xFF);
	}

	/**
	 * Combines four bytes into a number using Big-Endian approach.
	 * 
	 * @param x1
	 * @param x2
	 * @param x3
	 * @param x4
	 * @return long constructed with all the byte values.
	 */
	public static long addBytesToIntBE(int x1, int x2, int x3, int x4) {
		return (x1 << 16) | (x2 << 8) | (x3 & 0xFF);
	}
	
	
	/**
	 * Combines four bytes into a number from a byte array.
	 * 
	 * @param b - Target byte array.
	 * @return long constructed using the next four bytes in the array.
	 */
	public static long addBytesToInt4(byte[] b) {
		return addBytesToInt4(b, 0);
	}
	
	/**
	 * Combines four bytes into a number from a byte array.
	 * Also lets you specify an offset for where in the byte array the bytes
	 * should be retrieved from.
	 * 
	 * @param b - Target byte array.
	 * @param offset - Offset in the byte array to start getting objects from.
	 * @return long constructed using the next four bytes in the array.
	 */
	public static long addBytesToInt4(byte[] b, int offset) {
		int i=offset;
        int x1 = b[i++] & 0xFF;
        int x2 = b[i++] & 0xFF;
        int x3 = b[i++] & 0xFF;
        int x4 = b[i++] & 0xFF;
        return addBytesToInt(x1, x2, x3, x4);
	}
	
	/**
	 * Combines four bytes into a number from a byte array using Big-Endian approach.
	 * 
	 * @param b - Target byte array.
	 * @return long constructed using the next four bytes in the array.
	 */
	public static long addBytesToInt4BE(byte[] b) {
		return addBytesToInt4BE(b, 0);
	}
	
	/**
	 * Combines four bytes into a number from a byte array using Big-Endian approach.
	 * Also lets you specify an offset for where in the byte array the bytes
	 * should be retrieved from.
	 * 
	 * @param b - Target byte array.
	 * @param offset - Offset in the byte array to start getting objects from.
	 * @return long constructed using the next four bytes in the array.
	 */
	public static long addBytesToInt4BE(byte[] b, int offset) {
		int i=offset;
        int x1 = b[i++] & 0xFF;
        int x2 = b[i++] & 0xFF;
        int x3 = b[i++] & 0xFF;
        int x4 = b[i++] & 0xFF;
        return addBytesToInt(x1, x2, x3, x4);
	}
	
	/**
	 * Converts a set of bytes into a human readable String.
	 * 
	 * @param b - Target byte array.
	 * @param offset - Offset in the byte array to start getting objects from.
	 * @param length - Length of the data to be used.
	 * @return String constructed from bytes at given offset with given length from byte array.
	 */
	public static String getBytesToString(byte[] b, int offset, int length) {
		return new String(b, offset, length, Charset.forName("UTF-8"));
	}
}