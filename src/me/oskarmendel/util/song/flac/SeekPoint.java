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
 * Represents a seek point within a SeekTable.
 * TODO: Javadoc for getters & setters.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SeekPoint.java
 */
public class SeekPoint {
	
	/**
	 * The sample number of the first sample in the target frame.
	 * A value of 0xFFFFFFFFFFFFFFFF indicates a placeholder point.
	 * Represented as 64 bit unsigned integer.
	 */
	private long sampleNumber;
	
	/**
	 * Offset specified in bytes from the start of the first frame.
	 * Represented as 64 bit unsigned integer.
	 */
	private long byteOffset;
	
	/**
	 * Number of samples in the target frame.
	 * Represented as 16 bit unsigned integer.
	 */
	private int numberSamples;

	/**
	 * @return the sampleNumber
	 */
	public long getSampleNumber() {
		return sampleNumber;
	}

	/**
	 * @return the byteOffset
	 */
	public long getByteOffset() {
		return byteOffset;
	}

	/**
	 * @return the numberSamples
	 */
	public int getNumberSamples() {
		return numberSamples;
	}

	/**
	 * @param sampleNumber the sampleNumber to set
	 */
	public void setSampleNumber(long sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

	/**
	 * @param byteOffset the byteOffset to set
	 */
	public void setByteOffset(long byteOffset) {
		this.byteOffset = byteOffset;
	}

	/**
	 * @param numberSamples the numberSamples to set
	 */
	public void setNumberSamples(int numberSamples) {
		this.numberSamples = numberSamples;
	}
}
