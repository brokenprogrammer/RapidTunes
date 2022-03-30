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

package me.oskarmendel.settings.listener;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import me.oskarmendel.settings.PlaylistSettings;

/**
 * Tests for the PlaylistSettingsChangeListener.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name PlaylistSettingsChangeListenerTest.java
 */
public class PlaylistSettingsChangeListenerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link me.oskarmendel.settings.listener.PlaylistSettingsChangeListener#changed(javafx.beans.value.ObservableValue, me.oskarmendel.settings.PlaylistSettings, me.oskarmendel.settings.PlaylistSettings)}.
	 */
	@Test
	public final void testChanged() {
		PlaylistSettingsChangeListener playlistSettingListener = new PlaylistSettingsChangeListener();
		
		// Setting up PlaylistSettings object to bind the listener to.
		final ObjectProperty<PlaylistSettings> playlistSettings = new SimpleObjectProperty<>();
		playlistSettings.set(createPlaylistSettings());
		playlistSettings.addListener(playlistSettingListener);
		
		assertEquals("Current save playlists locally is set to true.", playlistSettings.get().getSavePlaylistLocal(), true);
		assertEquals("Current playlist directory is playlists/.", playlistSettings.get().getPlaylistDirectory().equals("playlists/"), true);
		assertEquals("Current export playlists to YouTube is set to false.", playlistSettings.get().isAutoExportYouTube(), false);
		assertEquals("Current export playlists to SoundCloud is set to false.", playlistSettings.get().isAutoExportSoundCloud(), false);
		
		playlistSettings.set(createPlaylistSettings(false, "newDir/", true, true));
		
		assertEquals("Current save playlists locally is set to false.", playlistSettings.get().getSavePlaylistLocal(), false);
		assertEquals("Current playlist directory is newDir/.", playlistSettings.get().getPlaylistDirectory().equals("newDir/"), true);
		assertEquals("Current export playlists to YouTube is set to true.", playlistSettings.get().isAutoExportYouTube(), true);
		assertEquals("Current export playlists to SoundCloud is set to true.", playlistSettings.get().isAutoExportSoundCloud(), true);
	}
	
	/**
	 * Helper method to create a dummy PlaylistSettings object.
	 * 
	 * @return - PlaylistSettings object with preset values.
	 */
	private PlaylistSettings createPlaylistSettings() {
		PlaylistSettings settings = new PlaylistSettings();
		
		settings.setSavePlaylistsLocal(true);
		settings.setPlaylistDirectory("playlists/");
		settings.setAutoExportYouTube(false);
		settings.setAutoExportSoundCloud(false);
		
		return settings;
	}
	
	/**
	 * Helper method to create a dummy PlaylistSettings object with
	 * specified values.
	 * 
	 * @param savePlaylistLocal - Toggle saving playlists locally.
	 * @param playlistDir - Directory to save playlists in.
	 * @param exportYouTube - Toggle exporting playlist to YouTube.
	 * @param exportSoundCloud - Toggle exporting playlist to SoundCloud.
	 * 
	 * @return - PlaylistSettings object with the specified values.
	 */
	private PlaylistSettings createPlaylistSettings(boolean savePlaylistLocal, String playlistDir, 
			boolean exportYouTube, boolean exportSoundCloud) {
		PlaylistSettings settings = new PlaylistSettings();
		
		settings.setSavePlaylistsLocal(savePlaylistLocal);
		settings.setPlaylistDirectory(playlistDir);
		settings.setAutoExportYouTube(exportYouTube);
		settings.setAutoExportSoundCloud(exportSoundCloud);
		
		return settings;
	}
}
