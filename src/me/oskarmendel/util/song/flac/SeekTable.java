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

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the seek table metadata block.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SeekTable.java
 */
public class SeekTable {
	
	private ArrayList<SeekPoint> seekPoints;
	
	/**
	 * 
	 */
	public SeekTable() {
		this.seekPoints = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param data
	 * 
	 * @throws IllegalArgumentException - 
	 */
	public SeekTable(byte[] data) {
		this.seekPoints = new ArrayList<>();
		
		// According to the Flac format specification:
		// The number of seek points is implied by the metadata 
		// header 'length' field, i.e. equal to length / 18.
		if (data.length % 18 != 0) {
			throw new IllegalArgumentException("Data contains invalid length of seekpoints.");
		}
		
		try {
		DataInput input = new DataInputStream(new ByteArrayInputStream(data));
		
		for (int i = 0; i < data.length; i+= 18) {
			SeekPoint point = new SeekPoint();
			point.setSampleNumber(input.readLong());
			point.setByteOffset(input.readLong());
			point.setNumberSamples(input.readInt());
			this.seekPoints.add(point);
		}
		
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void checkValues() {
		//TODO: Implement..
	}
	
	/**
	 * Writes seek table metadata..
	 */
	public void write() {
		//TODO: Implement..
	}
}
