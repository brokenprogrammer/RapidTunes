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

import java.util.Properties;

/**
 * GeneralSettings class that stores and controls settings handling
 * RapidTunes.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name GeneralSettings.java
 */
public class GeneralSettings extends Settings {
	
	private static final String PATH = "settings/general.properties";
	private static final String DEFAULT_THEME = "";
	private static final String DEFAULT_LANGUAGE = "";
	private static final boolean DEFAULT_NOTIFICATIONS = true;
	
	private String theme;
	private String language;
	private boolean notifications;
	//TODO: More UI settings goes here.
	
	/**
	 * 
	 */
	public GeneralSettings() {
		super();
	}
	
	/**
	 * 
	 * @param properties
	 */
	public GeneralSettings(Properties properties) {
		super();
		
	}

	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @return the notifications
	 */
	public boolean isNotifications() {
		return notifications;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @param notifications the notifications to set
	 */
	public void setNotifications(boolean notifications) {
		this.notifications = notifications;
	}

	@Override
	public String getPath() {
		return GeneralSettings.PATH;
	}

	@Override
	public Properties getDefaultProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("theme", DEFAULT_THEME);
		properties.setProperty("language", DEFAULT_LANGUAGE);
		properties.setProperty("notifications", String.valueOf(DEFAULT_NOTIFICATIONS));
		
		return properties;
	}
}
