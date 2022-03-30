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

package me.oskarmendel.util.song.flac.structure;

/**
 * Enum for the different types of metadata blocks within a file using 
 * the Flac file format.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FlacMetadataBlockType.java
 */
public enum FlacMetadataBlockType {
	STREAMINFO(0),
	PADDING(1),
	APPLICATION(2),
	SEEKTABLE(3),
	VORBIS_COMMENT(4),
	CUESHEET(5),
	PICTURE(6);
	
	private final byte type;
	
	/**
	 * Creates a new Enum that should contain the specified
	 * integer as it's type.
	 * 
	 * @param type - Metadata block identifier, later 
	 * 				 converted from int to byte.
	 */
	private FlacMetadataBlockType(int type) {
		this.type = (byte)type;
	}
	
	/**
	 * Getter for the metadata block type.
	 * 
	 * @return - Metadata block type.
	 */
	public byte getType() {
		return this.type;
	}
}
