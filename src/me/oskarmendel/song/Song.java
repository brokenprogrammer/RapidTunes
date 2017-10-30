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
 * Object representing a song
 * 
 * @author Jesper Bergstr�m
 * @version 0.00.00
 * @name Song.java
 */
public abstract class Song {
	
	protected String title;
	protected String artist;
	protected String album;
	protected long length;
	protected String path;
	
	protected String graphic;
	
	/**
	 * Default constructor for the abstract Song class initializing all 
	 * members to default values.
	 */
	public Song(){
		this.title = "";
		this.artist = "";
		this.album = "";
		this.length = 0;
		this.path = "";
		this.graphic = "";
	}
	
	/**
	 * Get song title
	 * 
	 * @return title
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 * Set song title
	 * 
	 * @param title
	 */
	abstract void setTitle(String title);
	
	/**
	 * Get artist
	 * 
	 * @return artist
	 */
	public String getArtist(){
		return artist;
	}
	
	/**
	 * Set artist
	 * 
	 * @param artist
	 */
	abstract void setArtist(String artist);
	
	/**
	 * Get album
	 * 
	 * @return album
	 */
	public String getAlbum(){
		return album;
	}
	
	/**
	 * Set album
	 * 
	 * @param album
	 */
	abstract void setAlbum(String album);
	
	/**
	 * Get song length
	 * 
	 * @return length
	 */
	public long getLength(){
		return length;
	}
	
	/**
	 * Set song length
	 * 
	 * @param length
	 */
	abstract void setLength(long length);
	
	/**
	 * Get song path
	 * 
	 * @return path
	 */
	public String getPath(){
		return path;
	}
	
	/**
	 * Set song path
	 * 
	 * @param path
	 */
	abstract void setPath(String path);
	
	/**
	 * Setter for the Graphic of this Song.
	 * 
	 * @param glyph - Glyph graphic to set for this Song.
	 */
	public void setGraphic(String glyph) {
		this.graphic = glyph;
	}
	
	/**
	 * Getter for the Graphic of this Song.
	 * 
	 * @return - Glyph graphic for this Song.
	 */
	public String getGraphic() {
		return this.graphic;
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
		if (!(o instanceof Song)) {
			return false;
		}
		
		Song s = (Song) o;
		
		return s.getTitle().equals(title)
				&& s.getArtist().equals(artist)
				&& s.getAlbum().equals(album)
				&& s.getLength() == length
				&& s.getPath().equals(path)
				&& s.getGraphic().equals(graphic);
	}
	
	/**
	 * Returns a hash code value for the object. This method is supported for the 
	 * benefit of hash tables such as those provided by java.util.HashMap. 
	 * 
	 * @return - a hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(title, artist, album, length, path, graphic);
	}
}
