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

import java.util.Objects;

import me.oskarmendel.util.BinaryUtil;

/**
 * Represents the stream info metadata block.
 * TODO: Javadoc for getters & setters.
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
	 * @throws IllegalStateException - If read data values contains invalid values.
	 */
	public StreamInfo(byte[] data) {
		if (data.length != 34) {
			throw new IllegalArgumentException("Invalid length of the metadata block.");
		}
		
		int bitOffset = 0;
		this.minimumBlockSize = BinaryUtil.addBytesToIntBE(
				BinaryUtil.byteToInt(data[bitOffset++]), 
				BinaryUtil.byteToInt(data[bitOffset++]));
		
		if (this.minimumBlockSize < 16) {
			throw new IllegalStateException("Minimum block size is less than 16.");
		}
		
		this.maximumBlockSize = BinaryUtil.addBytesToIntBE(
				BinaryUtil.byteToInt(data[bitOffset++]), 
				BinaryUtil.byteToInt(data[bitOffset++]));
		
		if (this.maximumBlockSize > 65535) {
			throw new IllegalStateException("Maximum block size is greater than 65535.");
		}
		
		if (this.maximumBlockSize < this.minimumBlockSize) {
			throw new IllegalStateException("Maximum block size is less than minimum block size.");
		}
		
		this.minimumFrameSize = (int)BinaryUtil.addBytesToIntBE(
				BinaryUtil.byteToInt(data[bitOffset++]), 
				BinaryUtil.byteToInt(data[bitOffset++]),
				BinaryUtil.byteToInt(data[bitOffset++]));
		
		this.maximumFrameSize = (int)BinaryUtil.addBytesToIntBE(
				BinaryUtil.byteToInt(data[bitOffset++]), 
				BinaryUtil.byteToInt(data[bitOffset++]),
				BinaryUtil.byteToInt(data[bitOffset++]));
		
		if (this.minimumFrameSize != 0 && this.maximumFrameSize != 0 && this.maximumFrameSize < this.minimumFrameSize) {
			throw new IllegalStateException("Maximum frame size is less than minimum frame size.");
		}
		
		this.sampleRate = 0;
		this.sampleRate += (BinaryUtil.byteToInt(data[bitOffset++]) << 12); // Shift with 12 to make place for 8 + 4 bits.
		this.sampleRate += (BinaryUtil.byteToInt(data[bitOffset++]) << 4);  // Shift with 4 to make place for the rest 4 bits.
		this.sampleRate += (BinaryUtil.getTopNibble(data[bitOffset]));
		
		if (this.sampleRate == 0 || this.sampleRate > 65535) {
			throw new IllegalStateException("Sample rate is invalid.");
		}
		
		this.numChannels = 0;
		this.numChannels += ((BinaryUtil.byteToInt(data[bitOffset]) & 0x0E) >> 1);
		this.numChannels += 1;
		
		this.bitsPerSample = 0;
		this.bitsPerSample += ((BinaryUtil.byteToInt(data[bitOffset++]) & 0x01) << 4); // Shift with 4 to make place for 4 bits.
		this.bitsPerSample += ((BinaryUtil.byteToInt(data[bitOffset]) & 0xF0) >> 4);
		this.bitsPerSample += 1;
		
		this.numSamples = 0;
		this.numSamples += ((BinaryUtil.byteToInt(data[bitOffset++]) & 0x0F) << 30);
		this.numSamples += (BinaryUtil.byteToInt(data[bitOffset++]) << 24);
		this.numSamples += (BinaryUtil.byteToInt(data[bitOffset++]) << 16);
		this.numSamples += (BinaryUtil.byteToInt(data[bitOffset++]) << 8);
		this.numSamples += (BinaryUtil.byteToInt(data[bitOffset++]));
		
		this.md5Sig = new byte[16];
		for (int i = 0; i < md5Sig.length; i++) {
			md5Sig[i] = data[bitOffset++];
		}
	}
	
	/**
	 * Checks the state of this object and throws exception if this
	 * object is in an illegal state.
	 * 
	 * @throws IllegalStateException - When any member contains an invalid value.
	 */
	public void checkValues() {
		if (this.minimumBlockSize < 16 || (this.minimumBlockSize >>> 16) != 0) {
			throw new IllegalStateException("Minimum Block Size contains invalid value.");
		}
		
		if (this.maximumBlockSize > 65535 || (this.maximumBlockSize >>> 16) != 0) {
			throw new IllegalStateException("Maximum Block Size contains invalid value.");
		}
		
		if ((this.minimumFrameSize >>> 24) != 0) {
			throw new IllegalStateException("Minimum Frame Size contains invalid value.");
		}
		
		if ((this.maximumFrameSize >>> 24) != 0) {
			throw new IllegalStateException("Maximum Frame Size contains invalid value.");
		}
		
		if (this.sampleRate == 0 || this.sampleRate > 65535 || (this.sampleRate >>> 20) != 0) {
			throw new IllegalStateException("Sample Rate contains invalid value.");
		}
		
		if (this.numChannels < 1  || this.numChannels > 8) {
			throw new IllegalStateException("Number of channels contains invalid value.");
		}
		
		if (this.bitsPerSample < 4 || this.bitsPerSample > 32) {
			throw new IllegalStateException("Bits per sample contains invalid value.");
		}
		
		if ((numSamples >>> 36) != 0) {
			throw new IllegalStateException("Number of samples contains invalid value.");
		}
		
		Objects.requireNonNull(this.md5Sig);
		if (this.md5Sig.length != 16) {
			throw new IllegalStateException("MD5 signature has invalid length.");
		}
	}
	
	/**
	 * Writes stream info metadata..
	 */
	public void write() {
		//TODO: Implement.
	}

	/**
	 * @return the minimumBlockSize
	 */
	public int getMinimumBlockSize() {
		return minimumBlockSize;
	}

	/**
	 * @return the maximumBlockSize
	 */
	public int getMaximumBlockSize() {
		return maximumBlockSize;
	}

	/**
	 * @return the minimumFrameSize
	 */
	public int getMinimumFrameSize() {
		return minimumFrameSize;
	}

	/**
	 * @return the maximumFrameSize
	 */
	public int getMaximumFrameSize() {
		return maximumFrameSize;
	}

	/**
	 * @return the sampleRate
	 */
	public int getSampleRate() {
		return sampleRate;
	}

	/**
	 * @return the numChannels
	 */
	public int getNumChannels() {
		return numChannels;
	}

	/**
	 * @return the bitsPerSample
	 */
	public int getBitsPerSample() {
		return bitsPerSample;
	}

	/**
	 * @return the numSamples
	 */
	public long getNumSamples() {
		return numSamples;
	}

	/**
	 * @return the md5Sig
	 */
	public byte[] getMd5Sig() {
		return md5Sig;
	}

	/**
	 * @param minimumBlockSize the minimumBlockSize to set
	 */
	public void setMinimumBlockSize(int minimumBlockSize) {
		this.minimumBlockSize = minimumBlockSize;
	}

	/**
	 * @param maximumBlockSize the maximumBlockSize to set
	 */
	public void setMaximumBlockSize(int maximumBlockSize) {
		this.maximumBlockSize = maximumBlockSize;
	}

	/**
	 * @param minimumFrameSize the minimumFrameSize to set
	 */
	public void setMinimumFrameSize(int minimumFrameSize) {
		this.minimumFrameSize = minimumFrameSize;
	}

	/**
	 * @param maximumFrameSize the maximumFrameSize to set
	 */
	public void setMaximumFrameSize(int maximumFrameSize) {
		this.maximumFrameSize = maximumFrameSize;
	}

	/**
	 * @param sampleRate the sampleRate to set
	 */
	public void setSampleRate(int sampleRate) {
		this.sampleRate = sampleRate;
	}

	/**
	 * @param numChannels the numChannels to set
	 */
	public void setNumChannels(int numChannels) {
		this.numChannels = numChannels;
	}

	/**
	 * @param bitsPerSample the bitsPerSample to set
	 */
	public void setBitsPerSample(int bitsPerSample) {
		this.bitsPerSample = bitsPerSample;
	}

	/**
	 * @param numSamples the numSamples to set
	 */
	public void setNumSamples(long numSamples) {
		this.numSamples = numSamples;
	}

	/**
	 * @param md5Sig the md5Sig to set
	 */
	public void setMd5Sig(byte[] md5Sig) {
		this.md5Sig = md5Sig;
	}
}
