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

package me.oskarmendel.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import me.oskarmendel.SettingsManager;
import me.oskarmendel.settings.AccountSettings;
import me.oskarmendel.settings.GeneralSettings;
import me.oskarmendel.settings.HotkeySettings;
import me.oskarmendel.settings.PlaylistSettings;
import me.oskarmendel.settings.SongSettings;
import me.oskarmendel.settings.SourceSettings;

/**
 * Model that manages the currently used settings for the application.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SettingsModel.java
 */
public class SettingsModel {
	
	private SettingsManager settingsManager;
	
	private final ObjectProperty<GeneralSettings> generalSettings = new SimpleObjectProperty<>();
	private final ObjectProperty<SongSettings> songSettings = new SimpleObjectProperty<>();
	private final ObjectProperty<PlaylistSettings> playlistSettings = new SimpleObjectProperty<>();
	private final ObjectProperty<SourceSettings> sourceSettings = new SimpleObjectProperty<>();
	private final ObjectProperty<HotkeySettings> hotkeySettings = new SimpleObjectProperty<>();
	private final ObjectProperty<AccountSettings> accountSettings = new SimpleObjectProperty<>();
	
	/**
	 * 
	 */
	public SettingsModel() {
		settingsManager = SettingsManager.getInstance();
		
		generalSettings.set((GeneralSettings) settingsManager.loadSettings(new GeneralSettings()));
		songSettings.set((SongSettings) settingsManager.loadSettings(new SongSettings()));
		playlistSettings.set((PlaylistSettings) settingsManager.loadSettings(new PlaylistSettings()));
		sourceSettings.set((SourceSettings) settingsManager.loadSettings(new SourceSettings()));
		hotkeySettings.set((HotkeySettings) settingsManager.loadSettings(new HotkeySettings()));
		accountSettings.set((AccountSettings) settingsManager.loadSettings(new AccountSettings()));
	}
}
