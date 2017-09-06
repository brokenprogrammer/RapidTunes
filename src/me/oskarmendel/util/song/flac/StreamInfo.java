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

/**
 * Represents the stream info metadata block.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name StreamInfo.java
 */
public class StreamInfo {
	
	/**
	 * The minimum block size (in samples) used in the stream.
	 * (Minimum blocksize == maximum blocksize) implies a fixed-blocksize stream.
	 * Represented as 16 bit unsigned integer.
	 */
	private int minimumBlockSize;
	
	/**
	 * The maximum block size (in samples) used in the stream.
	 * Represented as 16 bit unsigned integer.
	 */
	private int maximumBlockSize;
	
	/**
	 * The minimum frame size (in bytes) used in the stream. A value of 0 implies
	 * that the value is not known.
	 * Represented as 24 bit unsigned integer.
	 */
	private int minimumFrameSize;
	
	/**
	 * The maximum frame size (in bytes) used in the stream. A value of 0 implies
	 * that the value is not known.
	 * Represented as 24 bit unsigned integer.
	 */
	private int maximumFrameSize;
	
	/**
	 * Sample rate in Hz(Hertz). A value of 0 is invalid.
	 * Represented as a 20 bit unsigned integer.
	 */
	private int sampleRate;
	
	/**
	 * Number of channels, FLAC supports from 1 to 8 channels.
	 * Represented as a 3 bit unsigned integer.
	 */
	private int numChannels;
	
	/**
	 * Bits per sample, FLAC supports from 4 to 32 bits per sample.
	 * Represented as a 5 bit unsigned integer.
	 */
	private int bitsPerSample;
	
	/**
	 * The total number of samples within the stream. A value of 0 means
	 * that the total number of samples are unknown.
	 * Represented as a 36 bit unsigned integer.
	 */
	private long numSamples;
	
	/**
	 * The 16 byte MD5 signature of the un-encoded audio data.
	 */
	private byte[] md5Sig;
	
	/**
	 * Default constructor of the StreamInfo which creates a new StreamInfo 
	 * object with default values.
	 */
	public StreamInfo() {
		// Sets all the fields to invalid values.
		this.minimumBlockSize = 0;
		this.maximumBlockSize = 0;
		this.sampleRate = 0;
		
		// Sets all the fields to unknown values.
		this.minimumFrameSize = 0;
		this.maximumFrameSize = 0;
		this.numSamples = 0;
		this.md5Sig = new byte[16];
	}
	
	/**
	 * Constructs a StreamInfo object by parsing the specified 34 byte metadata block.
	 * 
	 * @param data - Metadata block to parse.
	 * 
	 * @throws IllegalArgumentException - If the data array length is not equal to 34.
	 */
	public StreamInfo(byte[] data) {
		if (data.length != 34) {
			throw new IllegalArgumentException("Invalid length of the metadata block.");
		}
		
		// TODO: Read metadata..
	}
}
