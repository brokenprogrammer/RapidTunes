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

package me.oskarmendel;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import me.oskarmendel.settings.AccountSettings;
import me.oskarmendel.settings.GeneralSettings;
import me.oskarmendel.settings.HotkeySettings;
import me.oskarmendel.settings.PlaylistSettings;
import me.oskarmendel.settings.SongSettings;
import me.oskarmendel.settings.SourceSettings;

/**
 * Tests for the SettingsManager.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SettingsManagerTest.java
 */
public class SettingsManagerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//TODO: Delete all settings files if exists.
		deleteSettings();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		//TODO: Delete all settings files if exists.
		deleteSettings();
	}

	/**
	 * Test method for {@link me.oskarmendel.SettingsManager#getInstance()}.
	 */
	@Test
	public final void testGetInstance() {
		SettingsManager settingsManager = SettingsManager.getInstance();
		
		assertThat("SettingsManager is initializied.", settingsManager, is(notNullValue()));
	}

	/**
	 * Test method for {@link me.oskarmendel.SettingsManager#saveSettings(me.oskarmendel.settings.Settings)}.
	 */
	@Test
	public final void testSaveSettings() {
		SettingsManager settingsManager = SettingsManager.getInstance();
		
		GeneralSettings generalSettings = new GeneralSettings();
		SongSettings songSettings = new SongSettings();
		PlaylistSettings playlistSettings = new PlaylistSettings();
		
		generalSettings = (GeneralSettings) settingsManager.loadSettings(generalSettings);
		songSettings = (SongSettings) settingsManager.loadSettings(songSettings);
		playlistSettings = (PlaylistSettings) settingsManager.loadSettings(playlistSettings);
		
		assertTrue("Current theme is: \"/css/Default.css\"", generalSettings.getTheme().equals("/css/Default.css"));
		
		assertTrue("Current crossfade is toggled to false.", !songSettings.isCrossfade());
		assertTrue("Current crossfade seconds is set to 6.", songSettings.getCrossfadeSeconds() == 6);
		
		assertTrue("Current playlist directory is set to \"playlists/\".", playlistSettings.getPlaylistDirectory().equals("playlists/"));
		assertTrue("Currently auto export to YouTube is set to false.", !playlistSettings.isAutoExportYouTube());
		
		// Change then save the settings within GeneralSettings.
		generalSettings.setTheme("TestingTheme");
		generalSettings.setLanguage("TestingLanguage");
		settingsManager.saveSettings(generalSettings);
		
		// Change then save the settings within SongSettings.
		songSettings.setCrossfade(true);
		songSettings.setCrossfadeSeconds(12);
		settingsManager.saveSettings(songSettings);
		
		// Change then save the settings within PlaylistSettings.
		playlistSettings.setPlaylistDirectory("long/testing/path");
		playlistSettings.setAutoExportYouTube(true);
		settingsManager.saveSettings(playlistSettings);
		
		// Reset the GeneralSettings then load saved GeneralSettings.
		generalSettings = new GeneralSettings();
		generalSettings = (GeneralSettings) settingsManager.loadSettings(generalSettings);
		
		// Reset the SongSettings then load saved SongSettings.
		songSettings = new SongSettings();
		songSettings = (SongSettings) settingsManager.loadSettings(new SongSettings());
		
		// Reset the PlaylistSettings then load saved PlaylistSettings.
		playlistSettings = new PlaylistSettings();
		playlistSettings = (PlaylistSettings) settingsManager.loadSettings(playlistSettings);
		
		assertTrue("New theme was set to \"TestingTheme\"", generalSettings.getTheme().equals("TestingTheme"));
		assertTrue("New language was set to \"TestingLanguage\"", generalSettings.getLanguage().equals("TestingLanguage"));
		
		assertTrue("New crossfade is toggled to true.", songSettings.isCrossfade());
		assertTrue("New crossfade seconds is set to 12.", songSettings.getCrossfadeSeconds() == 12);
		
		assertTrue("New playlist directory is set to \"long/testing/path\"", playlistSettings.getPlaylistDirectory().equals("long/testing/path"));
		assertTrue("New auto export to YouTube is set to true", playlistSettings.isAutoExportYouTube());
	}

	/**
	 * Test method for {@link me.oskarmendel.SettingsManager#createSettings(java.io.File, me.oskarmendel.settings.Settings)}.
	 */
	@Test
	public final void testLoadCreateSettings() {
		// Delete settings folder with all settings.
		deleteSettings();
		
		SettingsManager settingsManager = SettingsManager.getInstance();
		File settingsDir = new File("settings");
		
		GeneralSettings generalSettings = new GeneralSettings();
		SongSettings songSettings = new SongSettings();
		PlaylistSettings playlistSettings = new PlaylistSettings();
		SourceSettings sourceSettings = new SourceSettings();
		HotkeySettings hotkeySettings = new HotkeySettings();
		AccountSettings accountSettings = new AccountSettings();
		
		File generalSettingsFile = new File(generalSettings.getPath());
		File songSettingsFile = new File(songSettings.getPath());
		File playlistSettingsFile = new File(playlistSettings.getPath());
		File sourceSettingsFile = new File(sourceSettings.getPath());
		File hotkeySettingsFile = new File(hotkeySettings.getPath());
		File accountSettingsFile = new File(accountSettings.getPath());
		
		assertFalse("GeneralSettings file doesn't exist.", generalSettingsFile.exists());
		assertFalse("SongSettings file doesn't exist.", songSettingsFile.exists());
		assertFalse("PlaylistSettings file doesn't exist.", playlistSettingsFile.exists());
		assertFalse("SourceSettings file doesn't exist.", sourceSettingsFile.exists());
		assertFalse("HotkeySettings file doesn't exist.", hotkeySettingsFile.exists());
		assertFalse("AccountSettings file doesn't exist.", accountSettingsFile.exists());
			
		generalSettings = (GeneralSettings) settingsManager.loadSettings(generalSettings);
		songSettings = (SongSettings) settingsManager.loadSettings(songSettings);
		playlistSettings = (PlaylistSettings) settingsManager.loadSettings(playlistSettings);
		sourceSettings = (SourceSettings) settingsManager.loadSettings(sourceSettings);
		hotkeySettings = (HotkeySettings) settingsManager.loadSettings(hotkeySettings);
		accountSettings = (AccountSettings) settingsManager.loadSettings(accountSettings);
		
		assertTrue("Settings directory has been created.", settingsDir.exists());
		
		assertTrue("GeneralSettings file now exist.", generalSettingsFile.exists());
		assertTrue("SongSettings file now exist.", songSettingsFile.exists());
		assertTrue("PlaylistSettings file now exist.", playlistSettingsFile.exists());
		assertTrue("SourceSettings file now exist.", sourceSettingsFile.exists());
		assertTrue("HotkeySettings file now exist.", hotkeySettingsFile.exists());
		assertTrue("AccountSettings file now exist.", accountSettingsFile.exists());
		
	}
	
	/**
	 * Test method for {@link me.oskarmendel.SettingsManager#loadSettings(me.oskarmendel.settings.Settings)}.
	 */
	@Test
	public final void testLoadSettings() {
		SettingsManager settingsManager = SettingsManager.getInstance();
		
		GeneralSettings generalSettings = new GeneralSettings();
		
		generalSettings = (GeneralSettings) settingsManager.loadSettings(generalSettings);
		
		assertTrue("Current theme is: \"/css/Default.css\"", generalSettings.getTheme().equals("/css/Default.css"));
		
		generalSettings.setTheme("TestingTheme");
		generalSettings.setLanguage("TestingLanguage");
		settingsManager.saveSettings(generalSettings);
		
		generalSettings = new GeneralSettings();
		generalSettings = (GeneralSettings) settingsManager.loadSettings(generalSettings);
		
		assertTrue("New theme was set to \"TestingTheme\"", generalSettings.getTheme().equals("TestingTheme"));
		assertTrue("New language was set to \"TestingLanguage\"", generalSettings.getLanguage().equals("TestingLanguage"));
	}
	
	/**
	 * Helper method for this testing class that removes all the already
	 * existing settings.
	 */
	private void deleteSettings() {
		File settingsDir = new File("settings");
		
		if (settingsDir.exists()) {
			if (settingsDir.isDirectory()) {
				
				for (File file : settingsDir.listFiles()) {
					if (file.isFile()) {
						file.delete();
					}
				}
				
				settingsDir.delete();
			}
		}
	}
}
