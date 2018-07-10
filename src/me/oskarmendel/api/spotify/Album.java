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
 * A Spotify Album object.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Album.java
 */
public class Album {
	
	private String albumType;
	private Artist[] artists;
	private String[] availableMarkets;
	private Copyright[] copyrights;
	private ExternalId externalIds;
	private ExternalUrl externalUrls;
	private String[] genres;
	private String href;
	private String id;
	private Image[] images;
	private String label;
	private String name;
	private int popularity;
	private String releaseDate;
	private String releaseDatePrecision;
	private Track[] tracks;
	private String type;
	private String uri;
	
	public Album() {
		
	}

	/**
	 * @return the albumType
	 */
	public String getAlbumType() {
		return albumType;
	}

	/**
	 * @param albumType the albumType to set
	 */
	public void setAlbumType(String albumType) {
		this.albumType = albumType;
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
	 * @return the copyrights
	 */
	public Copyright[] getCopyrights() {
		return copyrights;
	}

	/**
	 * @param copyrights the copyrights to set
	 */
	public void setCopyrights(Copyright[] copyrights) {
		this.copyrights = copyrights;
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
	 * @return the genres
	 */
	public String[] getGenres() {
		return genres;
	}

	/**
	 * @param genres the genres to set
	 */
	public void setGenres(String[] genres) {
		this.genres = genres;
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
	 * @return the images
	 */
	public Image[] getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(Image[] images) {
		this.images = images;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
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
	 * @return the releaseDate
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return the releaseDatePrecision
	 */
	public String getReleaseDatePrecision() {
		return releaseDatePrecision;
	}

	/**
	 * @param releaseDatePrecision the releaseDatePrecision to set
	 */
	public void setReleaseDatePrecision(String releaseDatePrecision) {
		this.releaseDatePrecision = releaseDatePrecision;
	}

	/**
	 * @return the tracks
	 */
	public Track[] getTracks() {
		return tracks;
	}

	/**
	 * @param tracks the tracks to set
	 */
	public void setTracks(Track[] tracks) {
		this.tracks = tracks;
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
