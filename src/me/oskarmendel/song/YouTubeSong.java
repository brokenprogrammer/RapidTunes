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

package me.oskarmendel.song;

import java.util.Objects;

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
		super();
		this.graphic = "FontAwesome|YOUTUBE_PLAY";
		this.thumbnailURL = "";
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
	public void setLength(long length) {
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
	
	/**
	 * Indicates whether some other object is "equal to" this one.
	 * 
	 * @param o - the reference object with which to compare.
	 * 
	 * @return - true if this object is the same as the obj argument; false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof YouTubeSong)) {
			return false;
		}
		
		YouTubeSong s = (YouTubeSong) o;
		
		return s.getTitle().equals(title)
				&& s.getArtist().equals(artist)
				&& s.getAlbum().equals(album)
				&& s.getLength() == length
				&& s.getPath().equals(path)
				&& s.getGraphic().equals(graphic)
				&& s.getThumbnailURL().equals(thumbnailURL);
	}
	
	/**
	 * Returns a hash code value for the object. This method is supported for the 
	 * benefit of hash tables such as those provided by java.util.HashMap. 
	 * 
	 * @return - a hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(title, artist, album, length, path, graphic, thumbnailURL);
	}
}
