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

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import me.oskarmendel.view.menu.settings.locale.Locale;

/**
 * Tests for the GeneralSettings.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name GeneralSettingsTest.java
 */
public class GeneralSettingsTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link me.oskarmendel.settings.GeneralSettings#GeneralSettings(java.util.Properties)}.
	 */
	@Test
	public final void testGeneralSettingsProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("theme", "testingTheme");
		properties.setProperty("language", "testingLanguage");
		properties.setProperty("notifications", "true");
		
		GeneralSettings settings = new GeneralSettings(properties);
		
		assertEquals("Theme is \"testingTheme\".", settings.getTheme().equals("testingTheme"), true);
		assertEquals("Language is \"EN\".", settings.getLanguage(), Locale.EN);
		assertEquals("Notifications is set to true.", settings.isNotifications(), true);
	}
	
	/**
	 * Test method for {@link me.oskarmendel.settings.GeneralSettings#toProperties()}.
	 */
	@Test
	public final void testToProperties() {
		GeneralSettings settings = new GeneralSettings();
		
		settings.setTheme("myTestingTheme");
		settings.setLanguage("ru");
		settings.setNotifications(true);
		
		Properties properties = settings.toProperties();
		
		assertEquals("Theme is set to \"myTestingTheme\".", properties.get("theme").equals("myTestingTheme"), true);
		assertEquals("Language is set to \"RU\".", properties.get("language"), "RU");
		assertEquals("Notifications is set to true.", properties.get("notifications").equals("true"), true);
	}

	/**
	 * Test method for {@link me.oskarmendel.settings.GeneralSettings#getDefaultProperties()}.
	 */
	@Test
	public final void testGetDefaultProperties() {
		GeneralSettings settings = new GeneralSettings();
		
		Properties properties = settings.getDefaultProperties();
		
		assertEquals("Default theme is: \"/css/Default.css\".", properties.get("theme").equals("/css/Default.css"), true);
		assertEquals("Default language is: \"en\"\".", properties.get("language").equals("EN"), true);
		assertEquals("Default notifications is set to true.", properties.get("notifications").equals("true"), true);
	}
}
