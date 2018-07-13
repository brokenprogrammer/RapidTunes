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

package me.oskarmendel.api.spotify;

/**
 * A Spotify Track object.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Track.java
 */
public class Track {
	
	private Album album;
	private Artist[] artists;
	private String[] availableMarkets;
	private int discNumber;
	private int durationMs;
	private boolean explicit;
	private ExternalId externalIds;
	private ExternalUrl externalUrls;
	private String href;
	private String id;
	private boolean isPlayable;
	private TrackLink linkedFrom;
	private Restrictions restrictions;
	private String name;
	private int popularity;
	private String previewUrl;
	private int trackNumber;
	private String type;
	private String uri;

	/**
	 * Default constructor for the Track object creating an empty Track.
	 */
	public Track() {
		
	}

	/**
	 * @return the album
	 */
	public Album getAlbum() {
		return album;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(Album album) {
		this.album = album;
	}

	/**
	 * @return the artists
	 */
	public Artist[] getArtists() {
		return artists;
	}

	/**
	 * @param artists the artists to set
	 */
	public void setArtists(Artist[] artists) {
		this.artists = artists;
	}

	/**
	 * @return the availableMarkets
	 */
	public String[] getAvailableMarkets() {
		return availableMarkets;
	}

	/**
	 * @param availableMarkets the availableMarkets to set
	 */
	public void setAvailableMarkets(String[] availableMarkets) {
		this.availableMarkets = availableMarkets;
	}

	/**
	 * @return the discNumber
	 */
	public int getDiscNumber() {
		return discNumber;
	}

	/**
	 * @param discNumber the discNumber to set
	 */
	public void setDiscNumber(int discNumber) {
		this.discNumber = discNumber;
	}

	/**
	 * @return the durationMs
	 */
	public int getDurationMs() {
		return durationMs;
	}

	/**
	 * @param durationMs the durationMs to set
	 */
	public void setDurationMs(int durationMs) {
		this.durationMs = durationMs;
	}

	/**
	 * @return the explicit
	 */
	public boolean isExplicit() {
		return explicit;
	}

	/**
	 * @param explicit the explicit to set
	 */
	public void setExplicit(boolean explicit) {
		this.explicit = explicit;
	}

	/**
	 * @return the externalIds
	 */
	public ExternalId getExternalIds() {
		return externalIds;
	}

	/**
	 * @param externalIds the externalIds to set
	 */
	public void setExternalIds(ExternalId externalIds) {
		this.externalIds = externalIds;
	}

	/**
	 * @return the externalUrls
	 */
	public ExternalUrl getExternalUrls() {
		return externalUrls;
	}

	/**
	 * @param externalUrls the externalUrls to set
	 */
	public void setExternalUrls(ExternalUrl externalUrls) {
		this.externalUrls = externalUrls;
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the isPlayable
	 */
	public boolean isPlayable() {
		return isPlayable;
	}

	/**
	 * @param isPlayable the isPlayable to set
	 */
	public void setPlayable(boolean isPlayable) {
		this.isPlayable = isPlayable;
	}

	/**
	 * @return the linkedFrom
	 */
	public TrackLink getLinkedFrom() {
		return linkedFrom;
	}

	/**
	 * @param linkedFrom the linkedFrom to set
	 */
	public void setLinkedFrom(TrackLink linkedFrom) {
		this.linkedFrom = linkedFrom;
	}

	/**
	 * @return the restrictions
	 */
	public Restrictions getRestrictions() {
		return restrictions;
	}

	/**
	 * @param restrictions the restrictions to set
	 */
	public void setRestrictions(Restrictions restrictions) {
		this.restrictions = restrictions;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the popularity
	 */
	public int getPopularity() {
		return popularity;
	}

	/**
	 * @param popularity the popularity to set
	 */
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	/**
	 * @return the previewUrl
	 */
	public String getPreviewUrl() {
		return previewUrl;
	}

	/**
	 * @param previewUrl the previewUrl to set
	 */
	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	/**
	 * @return the trackNumber
	 */
	public int getTrackNumber() {
		return trackNumber;
	}

	/**
	 * @param trackNumber the trackNumber to set
	 */
	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
}
