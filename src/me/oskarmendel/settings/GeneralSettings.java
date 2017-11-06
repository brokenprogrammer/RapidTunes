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

import me.oskarmendel.view.RapidTunesController;
import me.oskarmendel.view.menu.settings.locale.Locale;

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
	
	private static final String DEFAULT_THEME = RapidTunesController.DEFAULT_STYLING;
	private static final Locale DEFAULT_LANGUAGE = Locale.EN;
	private static final boolean DEFAULT_NOTIFICATIONS = true;
	
	private String theme;				// Path of the CSS theme to be used for styling of the application.
	private Locale language;			// Language to be used by the application.
	private boolean notifications;		// Boolean toggle for turning notifications on / off.
	//TODO: More UI settings goes here.
	
	/**
	 * Default constructor for the GeneralSettings that simply calls
	 * the parent constructor and leaves all members uninitialized.
	 */
	public GeneralSettings() {
		super();
	}
	
	/**
	 * Constructor that initializes all members using the specified Properties
	 * object.
	 * 
	 * @param properties - Properties object to retrieve data from.
	 */
	public GeneralSettings(Properties properties) {
		super();
		
		this.theme = properties.getProperty("theme");
		this.language = Locale.getLocale(properties.getProperty("language"));
		this.notifications = Boolean.valueOf(properties.getProperty("notifications"));
	}

	/**
	 * Getter for the theme of this GeneralSettings.
	 * 
	 * @return - Theme of this GeneralSettings.
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * Getter for the language of this GeneralSettings.
	 * 
	 * @return - Language of this GeneralSettings.
	 */
	public Locale getLanguage() {
		return language;
	}

	/**
	 * Getter for the notifications of this GeneralSettings.
	 * 
	 * @return - Notifications value of this GeneralSettings.
	 */
	public boolean isNotifications() {
		return notifications;
	}

	/**
	 * Setter for the theme of this GeneralSettings.
	 * 
	 * @param theme - Theme value to set.
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * Setter for the language of this GeneralSettings.
	 * 
	 * @param language - Language value to set.
	 */
	public void setLanguage(String language) {
		this.language = Locale.getLocale(language);
	}
	
	/**
	 * Setter for the language of this GeneralSettings.
	 * 
	 * @param locale - Locale value to set.
	 */
	public void setLanguage(Locale locale) {
		this.language = locale;
	}

	/**
	 * Setter for the notifications of this GeneralSettings.
	 * 
	 * @param notifications - Notifications value to set.
	 */
	public void setNotifications(boolean notifications) {
		this.notifications = notifications;
	}

	/**
	 * Returns the path for the settings file.
	 * This path is used when saving / loading setting files.
	 * 
	 * @return - Path of the Settings file.
	 */
	@Override
	public String getPath() {
		return GeneralSettings.PATH;
	}

	/**
	 * Returns a Settings object converted into a Properties object.
	 * 
	 * @return - Properties object with all the Settings defined by the target Settings object.
	 */
	@Override
	public Properties toProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("theme", this.theme);
		properties.setProperty("language", this.language.toString());
		properties.setProperty("notifications", String.valueOf(this.notifications));
		
		return properties;
	}
	
	/**
	 * Returns the default Properties object defined by the target Settings object.
	 * 
	 * @return - Properties object with all the default properties.
	 */
	@Override
	public Properties getDefaultProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("theme", DEFAULT_THEME);
		properties.setProperty("language", DEFAULT_LANGUAGE.name());
		properties.setProperty("notifications", String.valueOf(DEFAULT_NOTIFICATIONS));
		
		return properties;
	}
}
