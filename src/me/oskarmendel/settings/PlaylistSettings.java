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

package me.oskarmendel.settings;

import java.util.Properties;

/**
 * PlaylistSettings class that stores and controls settings handling
 * playlists.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name PlaylistSettings.java
 */
public class PlaylistSettings extends Settings {
	
	private static final String PATH = "settings/playlist.properties";
	
	private static final String DEFAULT_PLAYLISTDIRECTORY = "playlists/";
	
	private static final boolean DEFAULT_AUTOEXPORT_YOUTUBE = false;
	private static final boolean DEFAULT_AUTOEXPORT_SOUNDCLOUD = false;
	
	private String playlistDirectory;			// Path for the directory to store the users playlists in.
	
	private boolean autoExportYouTube;			// Toggle to enable exporting playlists to YouTube.
	private boolean autoExportSoundCloud;		// Toggle to enable exporting playlists to SoundCloud.
	
	/**
	 * Default constructor for the PlaylistSettings that simply calls
	 * the parent constructor and leaves all members uninitialized.
	 */
	public PlaylistSettings() {
		super();
	}
	
	/**
	 * Constructor that initializes all members using the specified Properties
	 * object.
	 * 
	 * @param properties - Properties object to retrieve data from.
	 */
	public PlaylistSettings(Properties properties) {
		super();
		
		this.playlistDirectory = properties.getProperty("playlistDirectory");
		this.autoExportYouTube = Boolean.getBoolean(properties.getProperty("autoExportYouTube"));
		this.autoExportSoundCloud = Boolean.getBoolean(properties.getProperty("autoExportSoundCloud"));
	}
	
	/**
	 * Getter for the playlistDirectory value of this PlaylistSettings.
	 * 
	 * @return - PlaylistDirectory value of this PlaylistSettings.
	 */
	public String getPlaylistDirectory() {
		return playlistDirectory;
	}

	/**
	 * Getter for the autoExportYouTube value of this PlaylistSettings.
	 * 
	 * @return - AutoExportYouTube value of this PlaylistSettings.
	 */
	public boolean isAutoExportYouTube() {
		return autoExportYouTube;
	}

	/**
	 * Getter for the autoExportSoundCloud value of this PlaylistSettings.
	 * 
	 * @return - AutoExportSoundCloud value of this PlaylistSettings.
	 */
	public boolean isAutoExportSoundCloud() {
		return autoExportSoundCloud;
	}

	/**
	 * Setter for the playlistDirectory value of this PlaylistSettings.
	 * 
	 * @param playlistDirectory - PlaylistDirectory value to set.
	 */
	public void setPlaylistDirectory(String playlistDirectory) {
		this.playlistDirectory = playlistDirectory;
	}

	/**
	 * Setter for the autoExportYouTube value of this PlaylistSettings.
	 * 
	 * @param autoExportYouTube - AutoExportYouTube value to set.
	 */
	public void setAutoExportYouTube(boolean autoExportYouTube) {
		this.autoExportYouTube = autoExportYouTube;
	}

	/**
	 * Setter for the autoExportSoundCloud value of this PlaylistSettings.
	 * 
	 * @param autoExportSoundCloud - AutoExportSoundCloud value to set.
	 */
	public void setAutoExportSoundCloud(boolean autoExportSoundCloud) {
		this.autoExportSoundCloud = autoExportSoundCloud;
	}

	/**
	 * Returns the path for the settings file.
	 * This path is used when saving / loading setting files.
	 * 
	 * @return - Path of the Settings file.
	 */
	@Override
	public String getPath() {
		return PlaylistSettings.PATH;
	}

	/**
	 * Returns a Settings object converted into a Properties object.
	 * 
	 * @return - Properties object with all the Settings defined by the target Settings object.
	 */
	@Override
	public Properties toProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("playlistDirectory", this.playlistDirectory);
		properties.setProperty("autoExportYouTube", String.valueOf(this.autoExportYouTube));
		properties.setProperty("autoExportSoundCloud", String.valueOf(this.autoExportSoundCloud));
		
		return properties;
	}
	
	/**
	 * Returns the default Properties object defined by the target Settings object.
	 * 
	 * @return - Properties object with all the default properties.
	 */
	@Override
	public Properties getDefaultProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("playlistDirectory", DEFAULT_PLAYLISTDIRECTORY);
		properties.setProperty("autoExportYouTube", String.valueOf(DEFAULT_AUTOEXPORT_YOUTUBE));
		properties.setProperty("autoExportSoundCloud", String.valueOf(DEFAULT_AUTOEXPORT_SOUNDCLOUD));
		
		return properties;
	}
}
