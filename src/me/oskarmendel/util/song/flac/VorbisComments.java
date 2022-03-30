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
import me.oskarmendel.util.song.flac.structure.FlacVorbisCommentFields;

/**
 * Represents the vorbis comments metadata block.
 * TODO: Javadoc for getters & setters.
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
	 * Default constructor of the VorbisComment which creates a new
	 * VorbisComment object with default values.
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
	 * Constructs a new VorbisComment object by parsing the comments from the
	 * specified data array.
	 * 
	 * @param data - VorbisComment data block.
	 * 
	 * @throws IllegalStateException - If parsed comment was invalid.
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
			int len = (int) BinaryUtil.addBytesToInt4(data, bitOffset);
			bitOffset += 4;
			
			String currentComment = BinaryUtil.getBytesToString(data, bitOffset, len);
			bitOffset += len;
			
			int delim = currentComment.indexOf('=');
			if (delim == -1) {
				throw new IllegalStateException("Unable to parse comment '"+currentComment+"'");
			}
			
			//Splitting the key / value pair.
			String key = currentComment.substring(0, delim);
			String value = currentComment.substring(delim+1);
			
			//Act depending on what key for the comment.
			if (key.equals(FlacVorbisCommentFields.TITLE.getName())) {
				this.title = value;
			} else if (key.equals(FlacVorbisCommentFields.ALBUM.getName())) {
				this.album = value;
			} else if (key.equals(FlacVorbisCommentFields.TRACKNUMBER.getName())) {
				this.trackNumber = value;
			} else if (key.equals(FlacVorbisCommentFields.ARTIST.getName())) {
				this.artist = value;
			} else if (key.equals(FlacVorbisCommentFields.GENRE.getName())) {
				this.genre = value;
			} else if (key.equals(FlacVorbisCommentFields.DATE.getName())) {
				this.date = value;
			}
		}
	}

	/**
	 * @return the vendor
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @return the trackNumber
	 */
	public String getTrackNumber() {
		return trackNumber;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @return the performer
	 */
	public String getPerformer() {
		return performer;
	}

	/**
	 * @return the copyright
	 */
	public String getCopyright() {
		return copyright;
	}

	/**
	 * @return the license
	 */
	public String getLicense() {
		return license;
	}

	/**
	 * @return the organization
	 */
	public String getOrganization() {
		return organization;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @return the isrc
	 */
	public String getIsrc() {
		return isrc;
	}

	/**
	 * @param vendor the vendor to set
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * @param trackNumber the trackNumber to set
	 */
	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @param performer the performer to set
	 */
	public void setPerformer(String performer) {
		this.performer = performer;
	}

	/**
	 * @param copyright the copyright to set
	 */
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	/**
	 * @param license the license to set
	 */
	public void setLicense(String license) {
		this.license = license;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @param isrc the isrc to set
	 */
	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}
}
