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

package me.oskarmendel.util.song.flac.decoder;

import java.io.IOException;
import java.io.InputStream;

import me.oskarmendel.util.song.flac.Frame;

/**
 * Class to decode frames within a Flac format file.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FrameDecoder.java
 */
public class FrameDecoder {
	
	/**
	 * 
	 */
	private InputStream input;
	
	/**
	 * 
	 */
	private int bitsPerSample;
	
	/**
	 * 
	 * @param input
	 * @param bitsPerSample
	 */
	public FrameDecoder(InputStream input, int bitsPerSample) {
		this.input = input;
		this.bitsPerSample = bitsPerSample;
	}
	
	public Frame readFrame(int[][] samples, int offset) {
		try {
			Frame frame = new Frame(this.input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Frame(); //TODO: Implement..
	}
}