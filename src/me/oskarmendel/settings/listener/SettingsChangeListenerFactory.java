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

import javafx.scene.Scene;

/**
 * SettingsChangeListenerFactory class that provides Factory methods creating all
 * the different types of Settings ChangeListeners.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SettingsChangeListenerFactory.java
 */
public class SettingsChangeListenerFactory {
	
	/**
	 * Factory method to create a new GeneralSettings ChangeListener.
	 * 
	 * @param mainScene - Scene to make the ChangeListener modify upon change.
	 * 
	 * @return - New GeneralSettingsChangeListener object.
	 */
	public GeneralSettingsChangeListener getGeneralSettingsChangeListener(Scene mainScene) {
		return new GeneralSettingsChangeListener(mainScene);
	}
	
	/**
	 * Factory method to create a new SongSettings ChangeListener.
	 * 
	 * @return - New SongSettingsChangeListener object.
	 */
	public SongSettingsChangeListener getSongSettingsChangeListener() {
		return new SongSettingsChangeListener();
	}
	
	/**
	 * Factory method to create a new PlaylistSettings ChangeListener.
	 * 
	 * @return - new PlaylistSettingsChangeListener object.
	 */
	public PlaylistSettingsChangeListener getPlaylistSettingsChangeListener() {
		return new PlaylistSettingsChangeListener();
	}
	
	/**
	 * Factory method to create a new SourceSettings ChangeListener.
	 * 
	 * @return - New SourceSettingsChangeListener object.
	 */
	public SourceSettingsChangeListener getSourceSettingsChangeListener() {
		return new SourceSettingsChangeListener();
	}
	
	/**
	 * Factory method to create a new HotkeySettings ChangeListener.
	 * 
	 * @return - New HotkeySettingsChangeListener object.
	 */
	public HotkeySettingsChangeListener getHotkeySettingsChangeListener() {
		return new HotkeySettingsChangeListener();
	}
	
	/**
	 * Factory method to create a new AccountSettings ChangeListener.
	 * 
	 * @return - New AccountSettingsChangeListener object.
	 */
	public AccountSettingsChangeListener getAccountSettingsChangeListener() {
		return new AccountSettingsChangeListener();
	}
}
