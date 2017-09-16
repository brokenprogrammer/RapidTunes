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

package me.oskarmendel.util.song.flac;

import java.io.IOException;
import java.io.InputStream;

import me.oskarmendel.util.BinaryUtil;

/**
 * Represents a Frame from the Flac format.
 * TODO: Javadoc..
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Frame.java
 */
public class Frame {
	
	private int frameIndex;
	private long sampleOffset;
	
	private int syncCode;
	private int blockingStrategy;
	private int blockSize;
	private int sampleRate;
	private int channelAssignment;
	private int sampleSize;
	
	/**
	 * 
	 */
	public Frame() {
		this.frameIndex = -1;
		this.syncCode = -1;
		this.blockingStrategy = -1;
		this.blockSize = -1;
		this.sampleRate = -1;
		this.channelAssignment = -1;
		this.sampleSize = -1;
	}
	
	public Frame(InputStream input) throws IOException {
		//TODO: Add frame crc here as well.
		int temp = input.read();
		int n = input.read();
		
		if (temp == -1) {
			//TODO: Make this an invalid state.. return null;
			System.out.println("First byte was -1, Invalid state.");
		}
		
		// FrameSize = -1
		
		
		// Read sync code.
		this.syncCode = (temp << 6) | (n >> 2);
		if (this.syncCode != 0x3FFE) {
			// TODO: Throw new error / exception.
			System.out.println("Sync code didn't match");
		}
		
		// Read next fields
		if (BinaryUtil.getBitAtBE((byte) n, 7) != 0) {
			//TODO: Throw exception, Reserved bit
		}
		
		this.blockingStrategy = BinaryUtil.getBitAtBE((byte) n, 8);
		
		n = input.read();
		
		this.blockSize = (n >> 4);
		this.sampleRate = (n & 0x0F);
		
		n = input.read();
		
		this.channelAssignment = (n >> 4);
		if (this.channelAssignment < 8) {
			
		} else if (8 <= this.channelAssignment && this.channelAssignment <= 10) {
			
		} else {
			
		}
		
		// Next 3 bits = sampleDepth = sampleSize?
		this.sampleSize = (n & 0x0E);
		
		if (BinaryUtil.getBitAtBE((byte) n, 8) != 0) {
			// Throw new exception, Reserved bit.
		}
		
		long position = BinaryUtil.readUtf8Integer(input);
		
		if (this.blockingStrategy == 0) {
			if ((position >>> 31) != 0) {
				// Exception, Frame index is too large.
			}
			
			this.frameIndex = (int) position;
			this.sampleOffset = -1;
		} else if (this.blockingStrategy == 1) {
			this.sampleOffset = position;
			this.frameIndex = -1;
		} else {
			// Assertion error.
		}
		
		//TODO: Decode blockSize
		//TODO: Decode sampleRate
		//TODO: Get crc8
	}
}
