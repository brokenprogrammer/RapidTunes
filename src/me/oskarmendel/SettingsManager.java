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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.oskarmendel.settings.AccountSettings;
import me.oskarmendel.settings.GeneralSettings;
import me.oskarmendel.settings.HotkeySettings;
import me.oskarmendel.settings.PlaylistSettings;
import me.oskarmendel.settings.Settings;
import me.oskarmendel.settings.SongSettings;
import me.oskarmendel.settings.SourceSettings;

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
	 * Private constructor to enforce the Singleton pattern.
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
	 * Saves settings using the path bound to the specified Settings object, if no
	 * such settings file already exists then a new one is created and written to and 
	 * populated with the settings stored within the Settings object.
	 * 
	 * @param settings - Settings object to gather data and populate the settings file with.
	 */
	public void saveSettings(Settings settings) {
		LOGGER.log(Level.FINE, "Saving settings: " + settings.getPath());
		
		File settingsFile = new File(settings.getPath());
		OutputStream output = null;
		
		try {
			if (!settingsFile.exists()) {
				LOGGER.log(Level.FINE, "Settings not found, Creating new file for: " + settings.getPath());
				
				settingsFile.getParentFile().mkdirs();
				settingsFile.createNewFile();
				
				LOGGER.log(Level.FINE, "Created settings for: " + settings.getPath());
			}
			
			output = new FileOutputStream(settings.getPath());
			
			Properties properties = settings.toProperties();
			properties.store(output, null);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Loads settings from the path within the specified Settings object
	 * then attempts to load the settings. If no such settings file exist a new one is created
	 * and populated with default settings.
	 * 
	 * @param settings - Settings object to gather default data from and populate with properties.
	 * 
	 * @return - Populated Settings object.
	 */
	public Settings loadSettings(Settings settings) {
		LOGGER.log(Level.FINE, "Loading settings: " + settings.getPath());
		
		File settingsFile = new File(settings.getPath());
		
		if (!settingsFile.exists()) {
			LOGGER.log(Level.FINE, "Settings not found, Creating settings for: " + settings.getPath());
			return createSettings(settingsFile, settings);
		}
		
		Properties properties = new Properties();
		InputStream input = null;
		
		try {
			LOGGER.log(Level.FINE, "Reading: " + settings.getPath());
			input = new FileInputStream(settings.getPath());
			
			// Load properties from file.
			properties.load(input);
			
			// Populate settings depending on settings type.
			if (settings instanceof GeneralSettings) {
				settings = new GeneralSettings(properties);
			} else if (settings instanceof SongSettings) {
				settings = new SongSettings(properties);
			} else if (settings instanceof PlaylistSettings) {
				settings = new PlaylistSettings(properties);
			} else if (settings instanceof SourceSettings) {
				settings = new SourceSettings(properties);
			} else if (settings instanceof HotkeySettings) {
				settings = new HotkeySettings(properties);
			} else if (settings instanceof AccountSettings) {
				settings = new AccountSettings(properties);
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
	
	/**
	 * Creates a new default Settings file using the specified File and Settings object
	 * and populates it with the default settings for that Settings type.
	 * 
	 * @param settingsFile - File object for the settings file to create.
	 * @param settings - Settings object to use to get default data from.
	 * 
	 * @return - Populated Settings object using default properties.
	 */
	private Settings createSettings(File settingsFile, Settings settings) {
		try {
			// Create the directory and file if they do not exist.
			if (!settingsFile.exists()) {
				settingsFile.getParentFile().mkdirs();
				settingsFile.createNewFile();
				
				LOGGER.log(Level.FINE, "Created settings for: " + settings.getPath());
			}
			
			Properties properties = settings.getDefaultProperties();
			FileOutputStream output = null;
			
			try {
				LOGGER.log(Level.FINE, "Preparing to write default properties to: " + settings.getPath());
				output = new FileOutputStream(settings.getPath());
				
				LOGGER.log(Level.FINE, "Writing default properties to: " + settings.getPath());
				properties.store(output, null);
				
				// Populate settings depending on settings type.
				if (settings instanceof GeneralSettings) {
					settings = new GeneralSettings(properties);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (output != null) {
					output.close();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return settings;
	}
}
