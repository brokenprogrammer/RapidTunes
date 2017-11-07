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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import me.oskarmendel.settings.GeneralSettings;

/**
 * GeneralSettingsChangeListener class that defines behavior for when the GeneralSettings
 * in RapidTunes changes.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name GeneralSettingsChangeListener.java
 */
public class GeneralSettingsChangeListener implements ChangeListener<GeneralSettings>{
	
	private Scene mainScene;

	public GeneralSettingsChangeListener(Scene mainScene) {
		this.mainScene = mainScene;
	}
	
	@Override
	public void changed(ObservableValue<? extends GeneralSettings> observable, GeneralSettings oldValue,
			GeneralSettings newValue) {
		
		// If new theme was set then replace the old theme with the new one.
		if (!newValue.getTheme().equals(oldValue.getTheme())) {
			this.mainScene.getStylesheets().clear();
			this.mainScene.getStylesheets().add(newValue.getTheme());
		}
		
		// If new language was set then replace the old language with the new one.
		if (newValue.getLanguage() != oldValue.getLanguage()) {
			//TODO: Change locale on all views.
		}
		
		// If notifications was toggled on/off then toggle to the new value.
		if (newValue.isNotifications() != oldValue.isNotifications()) {
			//TODO: Implement notifications.
			if (newValue.isNotifications()) {
				//TODO: Turn on notifications.
			} else {
				//TODO: Turn off notifications.
			}
		}
	}
}
