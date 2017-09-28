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

import me.oskarmendel.util.BinaryReader;
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
	 */
	private int currentBlockSize;
	
	/**
	 * 
	 * @param input
	 * @param bitsPerSample
	 */
	public FrameDecoder(InputStream input, int bitsPerSample) {
		this.input = input;
		this.bitsPerSample = bitsPerSample;
		this.currentBlockSize = -1;
	}
	
	/**
	 * 
	 * @param outSamples
	 * @param outOffset
	 * 
	 * @return
	 */
	public Frame readFrame(int[][] outSamples, int outOffset) {
		Frame frame = null;
		
		try {
			BinaryReader reader = new BinaryReader(this.input);
			
			long startByte = reader.getPosition(); //TODO: Will return 0 since we just created the BinaryReader..
			frame = new Frame(this.input);
			
			if (frame == null) {
				return null;
			}
			
			if (frame.getSampleSize() != -1 && frame.getSampleSize() != this.bitsPerSample) {
				//Throw error, Depth missmatch.
			}
			
			// Check arguments.
			this.currentBlockSize = frame.getBlockSize();
			
			//TODO: Check outSamples is not null and stuff..
			
			// Decode Subframes
			
			// Read padding and footer.
			
			// Handle frame size and misc.....
			
			return frame;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return frame;
	}
}
