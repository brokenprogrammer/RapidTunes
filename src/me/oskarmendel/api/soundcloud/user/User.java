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

package me.oskarmendel.api.soundcloud.user;

/**
 * A SoundCloud User.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name User.java
 */
public class User {

	private int id;
	private String permalink;
	private String username;
	private String uri;
	private String permalinkURL;
	private String avatarURL;
	private String country;
	private String fullName;
	private String city;
	private String description;
	private String discogsName;
	private String myspaceName;
	private String website;
	private String websiteTitle;
	private boolean online;
	private int trackCount;
	private int playlistCount;
	private int followersCount;
	private int followingsCount;
	private int publicFavouritesCount;
	
	//avatarData	binary data for of the user avatar.
	
	/**
	 * Default constructor for the User object. Creates an empty user object.
	 */
	public User() {
		
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the avatarURL
	 */
	public String getAvatarURL() {
		return avatarURL;
	}

	/**
	 * @param avatarURL the avatarURL to set
	 */
	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
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
	 * @return the discogsName
	 */
	public String getDiscogsName() {
		return discogsName;
	}

	/**
	 * @param discogsName the discogsName to set
	 */
	public void setDiscogsName(String discogsName) {
		this.discogsName = discogsName;
	}

	/**
	 * @return the myspaceName
	 */
	public String getMyspaceName() {
		return myspaceName;
	}

	/**
	 * @param myspaceName the myspaceName to set
	 */
	public void setMyspaceName(String myspaceName) {
		this.myspaceName = myspaceName;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the websiteTitle
	 */
	public String getWebsiteTitle() {
		return websiteTitle;
	}

	/**
	 * @param websiteTitle the websiteTitle to set
	 */
	public void setWebsiteTitle(String websiteTitle) {
		this.websiteTitle = websiteTitle;
	}

	/**
	 * @return the online
	 */
	public boolean isOnline() {
		return online;
	}

	/**
	 * @param online the online to set
	 */
	public void setOnline(boolean online) {
		this.online = online;
	}

	/**
	 * @return the trackCount
	 */
	public int getTrackCount() {
		return trackCount;
	}

	/**
	 * @param trackCount the trackCount to set
	 */
	public void setTrackCount(int trackCount) {
		this.trackCount = trackCount;
	}

	/**
	 * @return the playlistCount
	 */
	public int getPlaylistCount() {
		return playlistCount;
	}

	/**
	 * @param playlistCount the playlistCount to set
	 */
	public void setPlaylistCount(int playlistCount) {
		this.playlistCount = playlistCount;
	}

	/**
	 * @return the followersCount
	 */
	public int getFollowersCount() {
		return followersCount;
	}

	/**
	 * @param followersCount the followersCount to set
	 */
	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	/**
	 * @return the followingsCount
	 */
	public int getFollowingsCount() {
		return followingsCount;
	}

	/**
	 * @param followingsCount the followingsCount to set
	 */
	public void setFollowingsCount(int followingsCount) {
		this.followingsCount = followingsCount;
	}

	/**
	 * @return the publicFavouritesCount
	 */
	public int getPublicFavouritesCount() {
		return publicFavouritesCount;
	}

	/**
	 * @param publicFavouritesCount the publicFavouritesCount to set
	 */
	public void setPublicFavouritesCount(int publicFavouritesCount) {
		this.publicFavouritesCount = publicFavouritesCount;
	}
}
