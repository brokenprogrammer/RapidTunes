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

package me.oskarmendel.view.menu.settings;

import javafx.fxml.FXML;
import me.oskarmendel.model.SettingsModel;
import me.oskarmendel.settings.HotkeySettings;

/**
 * Controller class for the Hotkeys tab in the Settings Menu.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name HotkeysTabController.java
 */
public class HotkeysTabController implements SettingsMenuTab {

	private SettingsModel settingsModel;
	
	@FXML
	public void initialize() {
		
	}
	
	/**
	 * Applies the Settings within the Tab by modifying the current settings
	 * within the SettingsModel contained within the SettingsTab.
	 */
	@Override
	public void apply() {
		HotkeySettings hotkeySettings = new HotkeySettings();
		
		this.settingsModel.getHotkeySettingsProperty().set(hotkeySettings);
	}
	
	/**
	 * Initializes the SettingsModel which contains the applications settings.
	 * 
	 * @param settingsModel - settingsModel object to share data with.
	 */
	@Override
	public void initSettingsModel(SettingsModel settingsModel) {
		if (this.settingsModel != null) {
			throw new IllegalStateException("Model can only be initialized once");
		}
		
		this.settingsModel = settingsModel;
	}
}
