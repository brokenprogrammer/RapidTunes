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
 * Object representing a song from the local hard drive.
 * 
 * @author Jesper Bergström
 * @version 0.00.00
 * @name LocalSong.java
 */
public class LocalSong extends Song{
	
	private LocalSongFormat songFormat;
	
	private int sampleRate;
	
	private int numChannels;
	
	private int bitsPerSample;
	
	/**
	 * Default constructor for a LocalSong. Initializes the graphic
	 * object needed for a displaying a LocalSong.
	 */
	public LocalSong() {
		super();
		this.graphic = "FontAwesome|HDD_ALT";
		this.songFormat = LocalSongFormat.UNKNOWN_FORMAT;
		this.sampleRate = 0;
		this.numChannels = 0;
		this.bitsPerSample = 0;
	}
	
	/**
	 * Setter for the song format of this local song.
	 * 
	 * @param songFormat - Target song format to set.
	 */
	public void setSongFormat(LocalSongFormat songFormat) {
		this.songFormat = songFormat;
	}
	
	/**
	 * Getter for the song format of this local song.
	 * 
	 * @return - Song format of this local song.
	 */
	public LocalSongFormat getSongFormat() {
		return this.songFormat;
	}
	
	/**
	 * Setter for the SampleRate of this local song.
	 * 
	 * @param sampleRate - Target SampleRate to set.
	 */
	public void setSampleRate(int sampleRate) {
		this.sampleRate = sampleRate;
	}
	
	/**
	 * Getter for the SampleRate of this local song.
	 * 
	 * @return - SampleRate of this local song.
	 */
	public int getSampleRate() {
		return this.sampleRate;
	}
	
	/**
	 * Setter for the Number of channels of this local song.
	 * 
	 * @param numChannels - Target number of channels to set.
	 */
	public void setNumChannels(int numChannels) {
		this.numChannels = numChannels;
	}
	
	/**
	 * Getter for the number of channels in this local song.
	 * 
	 * @return - Number of channels in this local song.
	 */
	public int getNumChannels() {
		return this.numChannels;
	}
	
	/**
	 * Setter for the bits per sample in this local song.
	 * 
	 * @param bitsPerSample - Target bits per sample to set.
	 */
	public void setBitsPerSample(int bitsPerSample) {
		this.bitsPerSample = bitsPerSample;
	}
	
	/**
	 * Getter for the bits per sample in this local song.
	 * 
	 * @return - Bits per sample in this local song.
	 */
	public int getBitsPerSample() {
		return this.bitsPerSample;
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
		if (!(o instanceof LocalSong)) {
			return false;
		}
		
		LocalSong s = (LocalSong) o;
		
		return s.getTitle().equals(title)
				&& s.getArtist().equals(artist)
				&& s.getAlbum().equals(album)
				&& s.getLength() == length
				&& s.getPath().equals(path)
				&& s.getGraphic().equals(graphic)
				&& s.getSongFormat() == this.songFormat
				&& s.getSampleRate() == this.sampleRate
				&& s.getNumChannels() == this.numChannels
				&& s.getBitsPerSample() == this.bitsPerSample;
	}
	
	/**
	 * Returns a hash code value for the object. This method is supported for the 
	 * benefit of hash tables such as those provided by java.util.HashMap. 
	 * 
	 * @return - a hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(title, artist, album, length, path, graphic, 
				songFormat, sampleRate, numChannels, bitsPerSample);
	}
}
