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

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the PlaylistSettings.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name PlaylistSettingsTest.java
 */
public class PlaylistSettingsTest {

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
	 * Test method for {@link me.oskarmendel.settings.PlaylistSettings#PlaylistSettings(java.util.Properties)}.
	 */
	@Test
	public final void testPlaylistSettingsProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("playlistDirectory", "myPlaylistDirectory/is/long");
		properties.setProperty("autoExportYouTube", "true");
		properties.setProperty("autoExportSoundCloud", "true");
		
		PlaylistSettings settings = new PlaylistSettings(properties);
		
		assertEquals("Playlist directory is set to: \"myPlaylistDirectory/is/long\"", settings.getPlaylistDirectory().equals("myPlaylistDirectory/is/long"), true);
		
		assertEquals("Auto export to YouTube is set to true.", settings.isAutoExportYouTube(), true);
		assertEquals("Auto export to SoundCloud is set to true.", settings.isAutoExportSoundCloud(), true);
	}
	
	/**
	 * Test method for {@link me.oskarmendel.settings.PlaylistSettings#toProperties()}.
	 */
	@Test
	public final void testToProperties() {
		PlaylistSettings settings = new PlaylistSettings();
		
		settings.setPlaylistDirectory("long/testing/directory");
		settings.setAutoExportYouTube(true);
		settings.setAutoExportSoundCloud(true);
		
		Properties properties = settings.toProperties();
		
		assertEquals("Playlist directory is set to: \"long/testing/directory\"", properties.get("playlistDirectory").equals("long/testing/directory"), true);
		
		assertEquals("Auto export to YouTube is set to true.", properties.get("autoExportYouTube").equals("true"), true);
		assertEquals("Auto export to SoundCloud is set to true.", properties.get("autoExportSoundCloud").equals("true"), true);
	}

	/**
	 * Test method for {@link me.oskarmendel.settings.PlaylistSettings#getDefaultProperties()}.
	 */
	@Test
	public final void testGetDefaultProperties() {
		PlaylistSettings settings = new PlaylistSettings();
		
		Properties properties = settings.getDefaultProperties();
		
		assertEquals("Default playlist directory is set to: \"playlists/\"", properties.get("playlistDirectory").equals("playlists/"), true);
		
		assertEquals("Default auto export to YouTube is set to false.", properties.get("autoExportYouTube").equals("false"), true);
		assertEquals("Default auto export to SoundCloud is set to false.", properties.get("autoExportSoundCloud").equals("false"), true);
	}
}
