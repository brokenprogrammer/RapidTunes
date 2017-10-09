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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import me.oskarmendel.settings.GeneralSettings;
import me.oskarmendel.settings.Settings;

/**
 * Singleton that manages the loading and changes of settings.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SettingsManager.java
 */
public class SettingsManager {
	
	private static final Logger LOGGER = Logger.getLogger(SettingsManager.class.getName());
	
	private static SettingsManager INSTANCE;
	
	/**
	 * 
	 */
	private SettingsManager() {	}
	
	/**
	 * Returns the singleton instance of SettingsManager creating it if 
	 * necessary.
	 * 
	 * @return The singleton instance of SettingsManager.
	 */
	public static SettingsManager getInstance() {
		if (INSTANCE == null)
			INSTANCE = new SettingsManager();
		return INSTANCE;
	}
	
	/**
	 * 
	 * @param settings
	 */
	public void saveSettings(Settings settings) {
		//TODO: Use logger..
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public Settings loadSettings(Settings settings) {
		//TODO: Use logger..
		
		//TODO: Check which type of Settings object to use.
		Properties properties = new Properties();
		InputStream input = null;
		
		
		try {
			//TODO: Use file object instead, check if exists. If not create the file & dir.
			input = new FileInputStream(settings.getPath());
			
			// Load properties from file.
			properties.load(input);
			
			// Populate settings depending on settings type.
			if (settings instanceof GeneralSettings) {
				settings = new GeneralSettings(properties);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return settings;
	}
}
