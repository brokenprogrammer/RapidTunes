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

import me.oskarmendel.util.BinaryReader;
import me.oskarmendel.util.BinaryUtil;

/**
 * Represents a Frame from the Flac format.
 * TODO: Javadoc.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Frame.java
 */
public class Frame {
	
	BinaryReader reader;
	
	private int frameIndex;
	private long sampleOffset;
	
	private int syncCode;
	private int blockingStrategy;
	private int blockSize;
	private int sampleRate;
	private int channelAssignment;
	private int sampleSize;
	
	private static final int[] BLOCK_SIZE_CODES = {
		-1, 192, 576, 1152, 2304, 4608, -1, -1, 256, 
		512, 1024, 2048, 4096, 8192, 16384, 32768
	};
	
	private static final int[] SAMPLE_SIZE_CODES = {
		-1, 8, 12, -1, 16, 20, 24, -1 
	};
	
	private static final int[] SAMPLE_RATE_CODES = {
		-1, 88200, 176400, 192000, 8000, 16000, 
		22050, 24000, 32000, 44100, 48000, 96000
	};
	
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
		reader = new BinaryReader(input);
		
		int temp = reader.readByte();
		//int n = input.read();
		
		if (temp == -1) {
			//TODO: Make this an invalid state.. return null;
			System.out.println("First byte was -1, Invalid state.");
		}
		
		// FrameSize = -1
		
		// Read sync code.
		this.syncCode = (temp << 6) | reader.readUnsignedInt(6);
		if (this.syncCode != 0x3FFE) {
			// TODO: Throw new error / exception.
			System.out.println("Sync code didn't match");
		}
		
		// Read next fields
		if (reader.readUnsignedInt(1) != 0) {
			//TODO: Throw exception, Reserved bit
		}
		
		this.blockingStrategy  = reader.readUnsignedInt(1);
		this.blockSize 		   = reader.readUnsignedInt(4);
		this.sampleRate 	   = reader.readUnsignedInt(4);
		this.channelAssignment = reader.readUnsignedInt(4);
		
		if (this.channelAssignment < 8) {
			//TODO
		} else if (8 <= this.channelAssignment && this.channelAssignment <= 10) {
			//TODO
		} else {
			//TODO
		}
		
		this.sampleSize = decodeSampleSize(reader.readUnsignedInt(3));
		
		if (reader.readUnsignedInt(1) != 0) {
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
		
		this.blockSize = decodeBlockSize(this.blockSize);
		this.sampleRate = decodeSampleRate(this.sampleRate);
		
		int crc8 = 0;
		if (reader.readUnsignedInt(8) != crc8) {
			//Throw error, CRC8 missmatch.
		}
		
		//TODO: Get crc8
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	private int decodeSampleSize(int code) {
		if ((code >>> 3) != 0)  {
			// Throw illegalargumentexception
		}
		
		if (code == 0) {
			return -1;
		} else {
			int res = SAMPLE_SIZE_CODES[code];
			
			if (res == -1) {
				// Throw error.
			}
			
			return res;
		}
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 * @throws IOException
	 */
	private int decodeBlockSize(int code) throws IOException {
		if ((code >>> 4) != 0) {
			// Throw illegalargumentexception.
		}
		
		switch (code) {
		case 0:
			// Throw error, Reserved bit.
		case 6:
			return reader.readUnsignedInt(8);
		case 7:
			return reader.readUnsignedInt(16);
		default:
			int res = BLOCK_SIZE_CODES[code];
			
			// TODO: Maybe check the res value?
			
			return res;
		}
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 * @throws IOException
	 */
	private int decodeSampleRate(int code) throws IOException {
		if ((code >>> 4) != 0) {
			// Throw illegalargumentexception.
		}
		
		switch (code) {
		case 0:
			return -1;
		case 12:
			return reader.readUnsignedInt(8);
		case 13:
			return reader.readUnsignedInt(16);
		case 14:
			return reader.readUnsignedInt(16) * 10;
		case 15:
			// Throw error, invalid sample rate.
		default:
			int res = SAMPLE_RATE_CODES[code];
			
			// TODO: Maybe check the res value?
			
			return res;
		}
	}

	/**
	 * @return the frameIndex
	 */
	public int getFrameIndex() {
		return frameIndex;
	}

	/**
	 * @return the sampleOffset
	 */
	public long getSampleOffset() {
		return sampleOffset;
	}

	/**
	 * @return the syncCode
	 */
	public int getSyncCode() {
		return syncCode;
	}

	/**
	 * @return the blockingStrategy
	 */
	public int getBlockingStrategy() {
		return blockingStrategy;
	}

	/**
	 * @return the blockSize
	 */
	public int getBlockSize() {
		return blockSize;
	}

	/**
	 * @return the sampleRate
	 */
	public int getSampleRate() {
		return sampleRate;
	}

	/**
	 * @return the channelAssignment
	 */
	public int getChannelAssignment() {
		return channelAssignment;
	}

	/**
	 * @return the sampleSize
	 */
	public int getSampleSize() {
		return sampleSize;
	}
}
