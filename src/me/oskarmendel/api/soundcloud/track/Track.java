/**
 * RapidTunes.
 * The music application to help you use all your music sources in one place.
 *
 * The MIT License (MIT)
 *
 * Copyright (C) 2018 The RapidTunes
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

package me.oskarmendel.api.soundcloud.track;

import java.time.LocalDate;

/**
 * A SoundCloud Track.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Track.java
 */
public class Track {
	private int id;
	private LocalDate createdAt;
	private int userId;
	//private User user;
	private String title;
	private String permalink;
	private String permalinkURL;
	private String uri;
	private String sharing;			// can be public / private
	private String embeddableBy;	// can be "all" "me" "none"
	private String purchaseURL;
	private String artworkURL;
	private String description;
	private long duration;	
	private String genre;
	private String tagList;
	private int labelID;
	private String labelName;
	private int release;
	private int releaseDay;
	private int releaseMonth;
	private int releaseYear;
	private boolean streamable;
	private boolean downloadable;
	private State state;
	private License license;
	private TrackType trackType;
	private String waveformURL;
	private String downloadURL;
	private String streamURL;
	private String videoURL;
	private int BPM;
	private boolean commentable;
	private String isrc;
	private String keySignature;
	private int commentCount;
	private int downloadCount;
	private int playbackCount;
	private int favouritingCount;
	private long originalContentSize;
	
	// assetData	binary data of the audio file.
	// artworkData	binary data of the artwork image.
	private boolean userFavourite;			// Track favourited by user. Only available for authenticated users.
	
	/**
	 * Default constructor for the Track object. Creates an empty track object.
	 */
	public Track() {
		
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the createdAt
	 */
	public LocalDate getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
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
	 * @return the permalink
	 */
	public String getPermalink() {
		return permalink;
	}

	/**
	 * @param permalink the permalink to set
	 */
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	/**
	 * @return the permalinkURL
	 */
	public String getPermalinkURL() {
		return permalinkURL;
	}

	/**
	 * @param permalinkURL the permalinkURL to set
	 */
	public void setPermalinkURL(String permalinkURL) {
		this.permalinkURL = permalinkURL;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return the sharing
	 */
	public String getSharing() {
		return sharing;
	}

	/**
	 * @param sharing the sharing to set
	 */
	public void setSharing(String sharing) {
		this.sharing = sharing;
	}

	/**
	 * @return the embeddableBy
	 */
	public String getEmbeddableBy() {
		return embeddableBy;
	}

	/**
	 * @param embeddableBy the embeddableBy to set
	 */
	public void setEmbeddableBy(String embeddableBy) {
		this.embeddableBy = embeddableBy;
	}

	/**
	 * @return the purchaseURL
	 */
	public String getPurchaseURL() {
		return purchaseURL;
	}

	/**
	 * @param purchaseURL the purchaseURL to set
	 */
	public void setPurchaseURL(String purchaseURL) {
		this.purchaseURL = purchaseURL;
	}

	/**
	 * @return the artworkURL
	 */
	public String getArtworkURL() {
		return artworkURL;
	}

	/**
	 * @param artworkURL the artworkURL to set
	 */
	public void setArtworkURL(String artworkURL) {
		this.artworkURL = artworkURL;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration;
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
	 * @return the tagList
	 */
	public String getTagList() {
		return tagList;
	}

	/**
	 * @param tagList the tagList to set
	 */
	public void setTagList(String tagList) {
		this.tagList = tagList;
	}

	/**
	 * @return the labelID
	 */
	public int getLabelID() {
		return labelID;
	}

	/**
	 * @param labelID the labelID to set
	 */
	public void setLabelID(int labelID) {
		this.labelID = labelID;
	}

	/**
	 * @return the labelName
	 */
	public String getLabelName() {
		return labelName;
	}

	/**
	 * @param labelName the labelName to set
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * @return the release
	 */
	public int getRelease() {
		return release;
	}

	/**
	 * @param release the release to set
	 */
	public void setRelease(int release) {
		this.release = release;
	}

	/**
	 * @return the releaseDay
	 */
	public int getReleaseDay() {
		return releaseDay;
	}

	/**
	 * @param releaseDay the releaseDay to set
	 */
	public void setReleaseDay(int releaseDay) {
		this.releaseDay = releaseDay;
	}

	/**
	 * @return the releaseMonth
	 */
	public int getReleaseMonth() {
		return releaseMonth;
	}

	/**
	 * @param releaseMonth the releaseMonth to set
	 */
	public void setReleaseMonth(int releaseMonth) {
		this.releaseMonth = releaseMonth;
	}

	/**
	 * @return the releaseYear
	 */
	public int getReleaseYear() {
		return releaseYear;
	}

	/**
	 * @param releaseYear the releaseYear to set
	 */
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	/**
	 * @return the streamable
	 */
	public boolean isStreamable() {
		return streamable;
	}

	/**
	 * @param streamable the streamable to set
	 */
	public void setStreamable(boolean streamable) {
		this.streamable = streamable;
	}

	/**
	 * @return the downloadable
	 */
	public boolean isDownloadable() {
		return downloadable;
	}

	/**
	 * @param downloadable the downloadable to set
	 */
	public void setDownloadable(boolean downloadable) {
		this.downloadable = downloadable;
	}

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * @return the license
	 */
	public License getLicense() {
		return license;
	}

	/**
	 * @param license the license to set
	 */
	public void setLicense(License license) {
		this.license = license;
	}

	/**
	 * @return the trackType
	 */
	public TrackType getTrackType() {
		return trackType;
	}

	/**
	 * @param trackType the trackType to set
	 */
	public void setTrackType(TrackType trackType) {
		this.trackType = trackType;
	}

	/**
	 * @return the waveformURL
	 */
	public String getWaveformURL() {
		return waveformURL;
	}

	/**
	 * @param waveformURL the waveformURL to set
	 */
	public void setWaveformURL(String waveformURL) {
		this.waveformURL = waveformURL;
	}

	/**
	 * @return the downloadURL
	 */
	public String getDownloadURL() {
		return downloadURL;
	}

	/**
	 * @param downloadURL the downloadURL to set
	 */
	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	/**
	 * @return the streamURL
	 */
	public String getStreamURL() {
		return streamURL;
	}

	/**
	 * @param streamURL the streamURL to set
	 */
	public void setStreamURL(String streamURL) {
		this.streamURL = streamURL;
	}

	/**
	 * @return the videoURL
	 */
	public String getVideoURL() {
		return videoURL;
	}

	/**
	 * @param videoURL the videoURL to set
	 */
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	/**
	 * @return the bPM
	 */
	public int getBPM() {
		return BPM;
	}

	/**
	 * @param bPM the bPM to set
	 */
	public void setBPM(int bPM) {
		BPM = bPM;
	}

	/**
	 * @return the commentable
	 */
	public boolean isCommentable() {
		return commentable;
	}

	/**
	 * @param commentable the commentable to set
	 */
	public void setCommentable(boolean commentable) {
		this.commentable = commentable;
	}

	/**
	 * @return the isrc
	 */
	public String getIsrc() {
		return isrc;
	}

	/**
	 * @param isrc the isrc to set
	 */
	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}

	/**
	 * @return the keySignature
	 */
	public String getKeySignature() {
		return keySignature;
	}

	/**
	 * @param keySignature the keySignature to set
	 */
	public void setKeySignature(String keySignature) {
		this.keySignature = keySignature;
	}

	/**
	 * @return the commentCount
	 */
	public int getCommentCount() {
		return commentCount;
	}

	/**
	 * @param commentCount the commentCount to set
	 */
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * @return the downloadCount
	 */
	public int getDownloadCount() {
		return downloadCount;
	}

	/**
	 * @param downloadCount the downloadCount to set
	 */
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	/**
	 * @return the playbackCount
	 */
	public int getPlaybackCount() {
		return playbackCount;
	}

	/**
	 * @param playbackCount the playbackCount to set
	 */
	public void setPlaybackCount(int playbackCount) {
		this.playbackCount = playbackCount;
	}

	/**
	 * @return the favouritingCount
	 */
	public int getFavouritingCount() {
		return favouritingCount;
	}

	/**
	 * @param favouritingCount the favouritingCount to set
	 */
	public void setFavouritingCount(int favouritingCount) {
		this.favouritingCount = favouritingCount;
	}

	/**
	 * @return the originalContentSize
	 */
	public long getOriginalContentSize() {
		return originalContentSize;
	}

	/**
	 * @param originalContentSize the originalContentSize to set
	 */
	public void setOriginalContentSize(long originalContentSize) {
		this.originalContentSize = originalContentSize;
	}

	/**
	 * @return the userFavourite
	 */
	public boolean isUserFavourite() {
		return userFavourite;
	}

	/**
	 * @param userFavourite the userFavourite to set
	 */
	public void setUserFavourite(boolean userFavourite) {
		this.userFavourite = userFavourite;
	}
}
