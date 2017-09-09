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

import me.oskarmendel.util.BinaryUtil;

/**
 * Represents the vorbis comments metadata block.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name VorbisComments.java
 */
public class VorbisComments {
	
	private String vendor;
	private String title;
	private String version;
	private String album;
	private String trackNumber;
	private String artist;
	private String performer;
	private String copyright;
	private String license;
	private String organization;
	private String description;
	private String genre;
	private String date;
	private String location;
	private String contact;
	private String isrc;
	
	/**
	 * 
	 */
	public VorbisComments() {
		this.vendor = "";
		this.title = "";
		this.version = "";
		this.album = "";
		this.trackNumber = "";
		this.artist = "";
		this.performer = "";
		this.copyright = "";
		this.license = "";
		this.organization = "";
		this.description = "";
		this.genre = "";
		this.date = "";
		this.location = "";
		this.contact = "";
		this.isrc = "";
	}
	
	/**
	 * 
	 * @param data
	 */
	public VorbisComments(byte[] data) {
		int bitOffset = 0;
		int vendorLength = (int) BinaryUtil.addBytesToInt4(data, bitOffset);
		bitOffset += 4;
		
		this.vendor = BinaryUtil.getBytesToString(data, bitOffset, vendorLength);
		bitOffset += vendorLength;
		
		int numComments = (int) BinaryUtil.addBytesToInt4(data, bitOffset);
		bitOffset += 4;
		
		// Parse each comment.
		for (int i = 0; i < numComments; i++) {
			
		}
	}
}
