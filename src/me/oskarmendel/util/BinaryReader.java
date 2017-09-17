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

import java.io.File;
import java.io.IOException;

/**
 * Utility class that provides helper methods to read individual bits and bytes
 * from files.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name BinaryReader.java
 */
public class BinaryReader implements LowLevelInput{
	
	private byte[] data;
	private int offset;
	
	public BinaryReader(File file) {
		
	}
	
	public BinaryReader(byte[] data) {
		
	}

	@Override
	public int readUnsignedInt(int n) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int readSignedInt(int n) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int readByte() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void seekTo(long position) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBitPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLength() {
		// TODO Auto-generated method stub
		return 0;
	}
}
