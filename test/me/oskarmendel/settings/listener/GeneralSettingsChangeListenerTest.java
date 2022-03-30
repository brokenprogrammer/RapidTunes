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

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import me.oskarmendel.settings.GeneralSettings;
import me.oskarmendel.settings.locale.Locale;

/**
 * Tests for the GeneralSettingsChangeListener.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name GeneralSettingsChangeListenerTest.java
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Scene.class)
public class GeneralSettingsChangeListenerTest {
	
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
	 * Test method for {@link me.oskarmendel.settings.listener.GeneralSettingsChangeListener#GeneralSettingsChangeListener(javafx.scene.Scene)}.
	 */
	@Test
	public final void testGeneralSettingsChangeListener() {
		Scene mockedScene = Mockito.mock(Scene.class);
		
		GeneralSettingsChangeListener generalSettingsListener = new GeneralSettingsChangeListener(mockedScene);
		
		assertEquals("Scene is set when constructor is called.", generalSettingsListener.getScene(), mockedScene);
	}

	/**
	 * Test method for {@link me.oskarmendel.settings.listener.GeneralSettingsChangeListener#changed(javafx.beans.value.ObservableValue, me.oskarmendel.settings.GeneralSettings, me.oskarmendel.settings.GeneralSettings)}.
	 */
	@Test
	public final void testChanged() {
		final Scene mockedScene = PowerMockito.mock(Scene.class);
		GeneralSettingsChangeListener generalSettingsListener = new GeneralSettingsChangeListener(mockedScene);
		final ObservableList<String> stylesheets = FXCollections.observableArrayList("/css/Default.css");
		
		// Setting up generalSettings object to bind the listener to.
		final ObjectProperty<GeneralSettings> generalSettings = new SimpleObjectProperty<>();
		generalSettings.set(createGeneralSettings());
		generalSettings.addListener(generalSettingsListener);
		
		PowerMockito.when(mockedScene.getStylesheets()).thenReturn(stylesheets);
		assertEquals("The current stylesheet is set to Default.css.", mockedScene.getStylesheets().get(0), "/css/Default.css");
		
		assertEquals("Current theme is Default.css.", generalSettings.get().getTheme(), "/css/Default.css");
		assertEquals("Current language is RU.", generalSettings.get().getLanguage(), Locale.RU);
		assertEquals("Notifications is toggled on.", generalSettings.get().isNotifications(), true);
		
		generalSettings.set(createGeneralSettings("/css/Dark.css", "SV", false));
		
		assertEquals("Current theme is Dark.css.", generalSettings.get().getTheme(), "/css/Dark.css");
		assertEquals("Current language is SV.", generalSettings.get().getLanguage(), Locale.SV);
		assertEquals("Notifications is toggled off.", generalSettings.get().isNotifications(), false);
		
		assertEquals("Theme was changed through ChangeListener.", mockedScene.getStylesheets().get(0), "/css/Dark.css");
		
		Mockito.verify(mockedScene, Mockito.times(4)).getStylesheets();
	}

	/**
	 * Helper method to create a dummy GeneralSettings object.
	 * 
	 * @return - GeneralSettings object with default theme, RU language and notifications on.
	 */
	private GeneralSettings createGeneralSettings() {
		GeneralSettings settings = new GeneralSettings();
		
		settings.setTheme("/css/Default.css");
		settings.setLanguage("ru");
		settings.setNotifications(true);
		
		return settings;
	}
	
	/**
	 * Helper method to create a dummy GeneralSettings object with specified
	 * values.
	 * 
	 * @param theme - Theme stylesheet to set.
	 * @param lang - Language locale to set.
	 * @param notification - Notifications to toggle on/off.
	 * 
	 * @return - GeneralSettings object with the specified theme, language and notifications.
	 */
	private GeneralSettings createGeneralSettings(String theme, String lang, boolean notification) {
		GeneralSettings settings = new GeneralSettings();
		
		settings.setTheme(theme);
		settings.setLanguage(lang);
		settings.setNotifications(notification);
		
		return settings;
	}
}
