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
	 * Default constructor for the {@link SettingsModel} that initializes all the different {@link Settings}
	 * by loading them from the file system using {@link SettingsManager}.
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

	/**
	 * Getter for the GeneralSettings ObjectProperty object.
	 * 
	 * @return - The generalSettings ObjectProperty.
	 */
	public ObjectProperty<GeneralSettings> getGeneralSettingsProperty() {
		return this.generalSettings;
	}
	
	/**
	 * Returns a GeneralSettings object for the currently used 
	 * GeneralSettings contained within the generalSettings ObjectProperty.
	 * 
	 * @return - The GeneralSettings that is currently in use.
	 */
	public GeneralSettings getGeneralSettings() {
		return this.generalSettings.get();
	}
	
	/**
	 * Setter for the GeneralSettings to be currently used.
	 * 
	 * @param settings - GeneralSettings object to set to active.
	 */
	public void setGeneralSettings(GeneralSettings settings) {
		this.generalSettings.set(settings);
	}

	/**
	 * Getter for the SongSettings ObjectProperty object.
	 * 
	 * @return - The songSettings ObjectProperty.
	 */
	public ObjectProperty<SongSettings> getSongSettingsProperty() {
		return this.songSettings;
	}
	
	/**
	 * Returns a SongSettings object for the currently used 
	 * SongSettings contained within the songSettings ObjectProperty.
	 * 
	 * @return - The SongSettings that is currently in use.
	 */
	public SongSettings getSongSettings() {
		return this.songSettings.get();
	}
	
	/**
	 * Setter for the SongSettings to be currently used.
	 * 
	 * @param settings - SongSettings object to set to active.
	 */
	public void setSongSettings(SongSettings settings) {
		this.songSettings.set(settings);
	}

	/**
	 * Getter for the PlaylistSettings ObjectProperty object.
	 * 
	 * @return - The playlistSettings ObjectProperty.
	 */
	public ObjectProperty<PlaylistSettings> getPlaylistSettingsProperty() {
		return this.playlistSettings;
	}
	
	/**
	 * Returns a PlaylistSettings object for the currently used 
	 * PlaylistSettings contained within the playlistSettings ObjectProperty.
	 * 
	 * @return - The PlaylistSettings that is currently in use.
	 */
	public PlaylistSettings getPlaylistSettings() {
		return this.playlistSettings.get();
	}
	
	/**
	 * Setter for the PlaylistSettings to be currently used.
	 * 
	 * @param settings - PlaylistSettings object to set to active.
	 */
	public void setPlaylistSettings(PlaylistSettings settings) {
		this.playlistSettings.set(settings);
	}

	/**
	 * Getter for the SourceSettings ObjectProperty object.
	 * 
	 * @return - The sourceSettings ObjectProperty.
	 */
	public ObjectProperty<SourceSettings> getSourceSettingsProperty() {
		return this.sourceSettings;
	}

	/**
	 * Returns a SourceSettings object for the currently used 
	 * SourceSettings contained within the sourceSettings ObjectProperty.
	 * 
	 * @return - The SourceSettings that is currently in use.
	 */
	public SourceSettings getSourceSettings() {
		return this.sourceSettings.get();
	}
	
	/**
	 * Setter for the SourceSettings to be currently used.
	 * 
	 * @param settings - SourceSettings object to set to active.
	 */
	public void setSourceSettings(SourceSettings settings) {
		this.sourceSettings.set(settings);
	}
	
	/**
	 * Getter for the HotkeySettings ObjectProperty object.
	 * 
	 * @return - The hotkeySettings ObjectProperty.
	 */
	public ObjectProperty<HotkeySettings> getHotkeySettingsProperty() {
		return this.hotkeySettings;
	}
	
	/**
	 * Returns a HotkeySettings object for the currently used 
	 * HotkeySettings contained within the hotkeySettings ObjectProperty.
	 * 
	 * @return - The HotkeySettings that is currently in use.
	 */
	public HotkeySettings getHotkeySettings() {
		return this.hotkeySettings.get();
	}
	
	/**
	 * Setter for the HotkeySettings to be currently used.
	 * 
	 * @param settings - HotkeySettings object to set to active.
	 */
	public void setHotkeySettings(HotkeySettings settings) {
		this.hotkeySettings.set(settings);
	}

	/**
	 * Getter for the AccountSettings ObjectProperty object.
	 * 
	 * @return - The accountSettings ObjectProperty.
	 */
	public ObjectProperty<AccountSettings> getAccountSettingsProperty() {
		return this.accountSettings;
	}
	
	/**
	 * Returns a AccountSettings object for the currently used 
	 * AccountSettings contained within the accountSettings ObjectProperty.
	 * 
	 * @return - The AccountSettings that is currently in use.
	 */
	public AccountSettings getAccountSettings() {
		return this.accountSettings.get();
	}
	
	/**
	 * Setter for the AccountSettings to be currently used.
	 * 
	 * @param settings - AccountSettings object to set to active.
	 */
	public void setAccountSettings(AccountSettings settings) {
		this.accountSettings.set(settings);
	}
}
