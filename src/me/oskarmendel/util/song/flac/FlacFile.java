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

import java.io.File;
import java.io.IOException;

/**
 * Object to represent files of the Flac format.
 * Implemented using the formatting for Flac files represented here:
 * https://xiph.org/flac/format.html
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FlacFile.java
 */
public class FlacFile {
	
	//File information contained within the Flac files Streaminfo metadata block.
	private int minimumBlockSize;	//Represented as 16 bits
	private int maximumBlockSize;	//Represented as 16 bits
	private int minimumFrameSize;	//Represented as 24 bits
	private int maximumFrameSize;	//Represented as 24 bits
	
	private int sampleRate;			//Represented as 20 bits
	private int numChannels;		//Represented as 3 bits
	private int bisPerSample;		//Represented as 5 bits
	private int numSamples;			//Represented as 36 bits
	
	//Data from the Vorbis Comments within the Flac file. 
	private String vendor;
	
	private String artist;
	private String album;
	private String title;
	private String genre;
	private String trackNumber;
	private String date;
	
	/**
	 * 
	 * @param path
	 * @throws IOException 
	 */
	public FlacFile(String path) throws IOException {
		File file = new File(path);
		
		//FlacParser.parseFlacFile(file, this);
	}
	
	/**
	 * 
	 * @param file
	 * @throws IOException 
	 */
	public FlacFile(File file) throws IOException {
		FlacParser.parseFlacFile(file, this);
	}



	/**
	 * @return the minimumBlockSize
	 */
	public int getMinimumBlockSize() {
		return minimumBlockSize;
	}



	/**
	 * @param minimumBlockSize the minimumBlockSize to set
	 */
	public void setMinimumBlockSize(int minimumBlockSize) {
		this.minimumBlockSize = minimumBlockSize;
	}



	/**
	 * @return the maximumBlockSize
	 */
	public int getMaximumBlockSize() {
		return maximumBlockSize;
	}



	/**
	 * @param maximumBlockSize the maximumBlockSize to set
	 */
	public void setMaximumBlockSize(int maximumBlockSize) {
		this.maximumBlockSize = maximumBlockSize;
	}



	/**
	 * @return the minimumFrameSize
	 */
	public int getMinimumFrameSize() {
		return minimumFrameSize;
	}



	/**
	 * @param minimumFrameSize the minimumFrameSize to set
	 */
	public void setMinimumFrameSize(int minimumFrameSize) {
		this.minimumFrameSize = minimumFrameSize;
	}



	/**
	 * @return the maximumFrameSize
	 */
	public int getMaximumFrameSize() {
		return maximumFrameSize;
	}



	/**
	 * @param maximumFrameSize the maximumFrameSize to set
	 */
	public void setMaximumFrameSize(int maximumFrameSize) {
		this.maximumFrameSize = maximumFrameSize;
	}



	/**
	 * @return the sampleRate
	 */
	public int getSampleRate() {
		return sampleRate;
	}



	/**
	 * @param sampleRate the sampleRate to set
	 */
	public void setSampleRate(int sampleRate) {
		this.sampleRate = sampleRate;
	}



	/**
	 * @return the numChannels
	 */
	public int getNumChannels() {
		return numChannels;
	}



	/**
	 * @param numChannels the numChannels to set
	 */
	public void setNumChannels(int numChannels) {
		this.numChannels = numChannels;
	}



	/**
	 * @return the bisPerSample
	 */
	public int getBisPerSample() {
		return bisPerSample;
	}



	/**
	 * @param bisPerSample the bisPerSample to set
	 */
	public void setBisPerSample(int bisPerSample) {
		this.bisPerSample = bisPerSample;
	}



	/**
	 * @return the numSamples
	 */
	public int getNumSamples() {
		return numSamples;
	}



	/**
	 * @param numSamples the numSamples to set
	 */
	public void setNumSamples(int numSamples) {
		this.numSamples = numSamples;
	}



	/**
	 * @return the vendor
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * @param vendor the vendor to set
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}



	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}



	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}



	/**
	 * @param album the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}



	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}



	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}



	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}



	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}



	/**
	 * @return the trackNumber
	 */
	public String getTrackNumber() {
		return trackNumber;
	}



	/**
	 * @param trackNumber the trackNumber to set
	 */
	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}



	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}



	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
}
