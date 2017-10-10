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
	
	private String playlistDirectory;			//
	
	private boolean autoExportYouTube;			//
	private boolean autoExportSoundCloud;		//
	
	/**
	 * 
	 */
	public PlaylistSettings() {
		super();
	}
	
	/**
	 * 
	 * @param properties
	 */
	public PlaylistSettings(Properties properties) {
		super();
	}
	
	/**
	 * @return the playlistDirectory
	 */
	public String getPlaylistDirectory() {
		return playlistDirectory;
	}

	/**
	 * @return the autoExportYouTube
	 */
	public boolean isAutoExportYouTube() {
		return autoExportYouTube;
	}

	/**
	 * @return the autoExportSoundCloud
	 */
	public boolean isAutoExportSoundCloud() {
		return autoExportSoundCloud;
	}

	/**
	 * @param playlistDirectory the playlistDirectory to set
	 */
	public void setPlaylistDirectory(String playlistDirectory) {
		this.playlistDirectory = playlistDirectory;
	}

	/**
	 * @param autoExportYouTube the autoExportYouTube to set
	 */
	public void setAutoExportYouTube(boolean autoExportYouTube) {
		this.autoExportYouTube = autoExportYouTube;
	}

	/**
	 * @param autoExportSoundCloud the autoExportSoundCloud to set
	 */
	public void setAutoExportSoundCloud(boolean autoExportSoundCloud) {
		this.autoExportSoundCloud = autoExportSoundCloud;
	}

	@Override
	public String getPath() {
		return PlaylistSettings.PATH;
	}

	@Override
	public Properties toProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("playlistDirectory", this.playlistDirectory);
		properties.setProperty("autoExportYouTube", String.valueOf(this.autoExportYouTube));
		properties.setProperty("autoExportSoundCloud", String.valueOf(this.autoExportSoundCloud));
		
		return properties;
	}
	
	@Override
	public Properties getDefaultProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("playlistDirectory", DEFAULT_PLAYLISTDIRECTORY);
		properties.setProperty("autoExportYouTube", String.valueOf(DEFAULT_AUTOEXPORT_YOUTUBE));
		properties.setProperty("autoExportSoundCloud", String.valueOf(DEFAULT_AUTOEXPORT_SOUNDCLOUD));
		
		return properties;
	}
}
