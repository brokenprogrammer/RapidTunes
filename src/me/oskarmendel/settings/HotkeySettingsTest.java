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

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import me.oskarmendel.settings.hotkeys.Hotkey;

/**
 * Tests for the HotkeySettings.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name HotkeySettingsTest.java
 */
public class HotkeySettingsTest {

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
	 * Test method for {@link me.oskarmendel.settings.HotkeySettings#HotkeySettings(java.util.Properties)}.
	 */
	@Test
	public final void testHotkeySettingsProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("newPlaylist", "Ctrl+N");
		properties.setProperty("importPlaylist", "Shift+Ctrl+N");
		
		properties.setProperty("undo", "Ctrl+Z");
		properties.setProperty("redo", "Ctrl+Y");
		properties.setProperty("cut", "Ctrl+X");
		properties.setProperty("copy", "Ctrl+C");
		properties.setProperty("paste", "Ctrl+V");
		properties.setProperty("delete", "DELETE");
		properties.setProperty("markAll", "Ctrl+A");
		properties.setProperty("search", "Ctrl+L");
		properties.setProperty("settings", "Ctrl+P");
		
		properties.setProperty("next", "Ctrl+Right");
		properties.setProperty("previous", "Ctrl+Left");
		properties.setProperty("fastForward", "Shift+Right");
		properties.setProperty("rewind", "Shift+Left");
		properties.setProperty("shuffle", "Ctrl+S");
		properties.setProperty("repeat", "Ctrl+R");
		properties.setProperty("volumeUp", "Ctrl+Up");
		properties.setProperty("volumeDown", "Ctrl+Down");
		
		properties.setProperty("rapidtunesHelp", "F1");
		
		HotkeySettings settings = new HotkeySettings(properties);
		
		assertEquals(settings.getNewPlaylist().getCombinationKeybind().getKeyCombination().getName(), "Ctrl+N");
		assertEquals(settings.getImportPlaylist().getCombinationKeybind().getKeyCombination().getName(), "Shift+Ctrl+N");
		
		assertEquals(settings.getUndo().getCombinationKeybind().getKeyCombination().getName(), "Ctrl+Z");
		assertEquals(settings.getRedo().getCombinationKeybind().getKeyCombination().getName(), "Ctrl+Y");
		assertEquals(settings.getCut().getCombinationKeybind().getKeyCombination().getName(), "Ctrl+X");
		assertEquals(settings.getCopy().getCombinationKeybind().getKeyCombination().getName(), "Ctrl+C");
		assertEquals(settings.getPaste().getCombinationKeybind().getKeyCombination().getName(), "Ctrl+V");
		
		assertEquals(settings.getDelete().isSingleKey(), true);
		assertEquals(settings.getDelete().getSingleKeybind().getKey(), KeyCode.DELETE);
		
		assertEquals(settings.getMarkAll().getCombinationKeybind().getKeyCombination().getName(), "Ctrl+A");
		assertEquals(settings.getSearch().getCombinationKeybind().getKeyCombination().getName(), "Ctrl+L");
		assertEquals(settings.getSettings().getCombinationKeybind().getKeyCombination().getName(), "Ctrl+P");
		
		assertEquals(settings.getNext().getCombinationKeybind().getKeyCombination().getName(), "Ctrl+Right");
		assertEquals(settings.getPrevious().getCombinationKeybind().getKeyCombination().getName(), "Ctrl+Left");
		assertEquals(settings.getFastForward().getCombinationKeybind().getKeyCombination().getName(), "Shift+Right");
		assertEquals(settings.getRewind().getCombinationKeybind().getKeyCombination().getName(), "Shift+Left");
		assertEquals(settings.getShuffle().getCombinationKeybind().getKeyCombination().getName(), "Ctrl+S");
		assertEquals(settings.getRepeat().getCombinationKeybind().getKeyCombination().getName(), "Ctrl+R");
		assertEquals(settings.getVolumeUp().getCombinationKeybind().getKeyCombination().getName(), "Ctrl+Up");
		assertEquals(settings.getVolumeDown().getCombinationKeybind().getKeyCombination().getName(), "Ctrl+Down");
		
		assertEquals(settings.getRapidtunesHelp().isSingleKey(), true);
		assertEquals(settings.getRapidtunesHelp().getSingleKeybind().getKey(), KeyCode.F1);
	}
	
	/**
	 * Test method for {@link me.oskarmendel.settings.HotkeySettings#toProperties()}.
	 */
	@Test
	public final void testToProperties() {
		HotkeySettings settings = new HotkeySettings();
		
		settings.setNewPlaylist(new Hotkey(KeyCombination.keyCombination("Ctrl+N")));
		settings.setImportPlaylist(new Hotkey(KeyCombination.keyCombination("Shift+Ctrl+N")));
		
		settings.setUndo(new Hotkey(KeyCombination.keyCombination("Ctrl+Z")));
		settings.setRedo(new Hotkey(KeyCombination.keyCombination("Ctrl+Y")));
		settings.setCut(new Hotkey(KeyCombination.keyCombination("Ctrl+X")));
		settings.setCopy(new Hotkey(KeyCombination.keyCombination("Ctrl+C")));
		settings.setPaste(new Hotkey(KeyCombination.keyCombination("Ctrl+V")));
		
		settings.setDelete(new Hotkey(KeyCode.DELETE));
		
		settings.setMarkAll(new Hotkey(KeyCombination.keyCombination("Ctrl+A")));
		settings.setSearch(new Hotkey(KeyCombination.keyCombination("Ctrl+L")));
		settings.setSettings(new Hotkey(KeyCombination.keyCombination("Ctrl+P")));
		
		settings.setNext(new Hotkey(KeyCombination.keyCombination("Ctrl+Right")));
		settings.setPrevious(new Hotkey(KeyCombination.keyCombination("Ctrl+Left")));
		settings.setFastForward(new Hotkey(KeyCombination.keyCombination("Shift+Right")));
		settings.setRewind(new Hotkey(KeyCombination.keyCombination("Shift+Left")));
		settings.setShuffle(new Hotkey(KeyCombination.keyCombination("Ctrl+S")));
		settings.setRepeat(new Hotkey(KeyCombination.keyCombination("Ctrl+R")));
		settings.setVolumeUp(new Hotkey(KeyCombination.keyCombination("Ctrl+Up")));
		settings.setVolumeDown(new Hotkey(KeyCombination.keyCombination("Ctrl+Down")));
		
		settings.setRapidtunesHelp(new Hotkey(KeyCode.F1));
		
		Properties properties = settings.toProperties();
		
		assertEquals(properties.get("newPlaylist"), "Ctrl+N");
		assertEquals(properties.get("importPlaylist"), "Shift+Ctrl+N");
		
		assertEquals(properties.get("undo"), "Ctrl+Z");
		assertEquals(properties.get("redo"), "Ctrl+Y");
		assertEquals(properties.get("cut"), "Ctrl+X");
		assertEquals(properties.get("copy"), "Ctrl+C");
		assertEquals(properties.get("paste"), "Ctrl+V");
		assertEquals(properties.get("delete"), "DELETE");
		assertEquals(properties.get("markAll"), "Ctrl+A");
		assertEquals(properties.get("search"), "Ctrl+L");
		assertEquals(properties.get("settings"), "Ctrl+P");
		
		assertEquals(properties.get("next"), "Ctrl+Right");
		assertEquals(properties.get("previous"), "Ctrl+Left");
		assertEquals(properties.get("fastForward"), "Shift+Right");
		assertEquals(properties.get("rewind"), "Shift+Left");
		assertEquals(properties.get("shuffle"), "Ctrl+S");
		assertEquals(properties.get("repeat"), "Ctrl+R");
		assertEquals(properties.get("volumeUp"), "Ctrl+Up");
		assertEquals(properties.get("volumeDown"), "Ctrl+Down");
		
		assertEquals(properties.get("rapidtunesHelp"), "F1");
		
	}

	/**
	 * Test method for {@link me.oskarmendel.settings.HotkeySettings#getDefaultProperties()}.
	 */
	@Test
	public final void testGetDefaultProperties() {
		HotkeySettings settings = new HotkeySettings();
		
		Properties properties = settings.getDefaultProperties();
		
		assertEquals(properties.get("newPlaylist"), "Ctrl+N");
		assertEquals(properties.get("importPlaylist"), "Shift+Ctrl+N");
		
		assertEquals(properties.get("undo"), "Ctrl+Z");
		assertEquals(properties.get("redo"), "Ctrl+Y");
		assertEquals(properties.get("cut"), "Ctrl+X");
		assertEquals(properties.get("copy"), "Ctrl+C");
		assertEquals(properties.get("paste"), "Ctrl+V");
		assertEquals(properties.get("delete"), "DELETE");
		assertEquals(properties.get("markAll"), "Ctrl+A");
		assertEquals(properties.get("search"), "Ctrl+L");
		assertEquals(properties.get("settings"), "Ctrl+P");
		
		assertEquals(properties.get("next"), "Ctrl+Right");
		assertEquals(properties.get("previous"), "Ctrl+Left");
		assertEquals(properties.get("fastForward"), "Shift+Right");
		assertEquals(properties.get("rewind"), "Shift+Left");
		assertEquals(properties.get("shuffle"), "Ctrl+S");
		assertEquals(properties.get("repeat"), "Ctrl+R");
		assertEquals(properties.get("volumeUp"), "Ctrl+Up");
		assertEquals(properties.get("volumeDown"), "Ctrl+Down");
		
		assertEquals(properties.get("rapidtunesHelp"), "F1");
	}
}
