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

package me.oskarmendel.entities;

/**
 * Object representing a song from the youtube
 * 
 * @author Jesper Bergström
 * @version 0.00.00
 * @name Song.java
 */
public class YouTubeSong extends Song{
	
	private String thumbnailURL;
	
	/**
	 * Default constructor for a YouTubeSong. Initializes the graphic
	 * object needed for a displaying a YouTubeSong.
	 */
	public YouTubeSong() {
		this.graphic = "FontAwesome|YOUTUBE_PLAY";
	}
	
	/**
	 * Set song title
	 * 
	 * @param title
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Set song artist
	 * 
	 * @param artist
	 */
	@Override
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	/**
	 * Set song album
	 * 
	 * @param album
	 */
	@Override
	public void setAlbum(String album) {
		this.album = album;
	}
	
	/**
	 * Set song length
	 * 
	 * @param length
	 */
	@Override
	public void setLength(String length) {
		this.length = length;
	}
	
	/**
	 * Set song path
	 * 
	 * @param path
	 */
	@Override
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the thumbnailURL
	 */
	public String getThumbnailURL() {
		return thumbnailURL;
	}

	/**
	 * @param thumbnailURL the thumbnailURL to set
	 */
	public void setThumbnailURL(String thumbnailURL) {
		this.thumbnailURL = thumbnailURL;
	}
}
