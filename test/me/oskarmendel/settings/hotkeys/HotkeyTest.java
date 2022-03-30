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

package me.oskarmendel.settings.hotkeys;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;

/**
 * Tests for the Hotkey.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name HotkeyTest.java
 */
public class HotkeyTest {

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
	 * Test method for {@link me.oskarmendel.settings.hotkeys.Hotkey#Hotkey(javafx.scene.input.KeyCode)}.
	 */
	@Test
	public final void testHotkeyKeyCode() {
		Hotkey singleKey = new Hotkey(KeyCode.A);
		
		assertEquals(singleKey.isSingleKey(), true);
		assertEquals(singleKey.getSingleKeybind().getKey(), KeyCode.A);
	}

	/**
	 * Test method for {@link me.oskarmendel.settings.hotkeys.Hotkey#Hotkey(javafx.scene.input.KeyCombination)}.
	 */
	@Test
	public final void testHotkeyKeyCombination() {
		Hotkey multipleKeys = new Hotkey(KeyCombination.valueOf("Ctrl+C"));
		
		assertEquals(multipleKeys.isSingleKey(), false);
		assertEquals(multipleKeys.getCombinationKeybind().getKeyCombination().getName().equals("Ctrl+C"), true);
	}

	/**
	 * Test method for {@link me.oskarmendel.settings.hotkeys.Hotkey#getSingleKeybind()}.
	 */
	@Test(expected = IllegalStateException.class)
	public final void testGetSingleKeybind() {
		Hotkey singleKey = new Hotkey(KeyCode.D);
		
		assertEquals(singleKey.isSingleKey(), true);
		assertEquals(singleKey.getSingleKeybind().getKey(), KeyCode.D);
		
		// Throws IllegalStateException since its a single key bound.
		singleKey.getCombinationKeybind();
	}

	/**
	 * Test method for {@link me.oskarmendel.settings.hotkeys.Hotkey#getCombinationKeybind()}.
	 */
	@Test(expected = IllegalStateException.class)
	public final void testGetCombinationKeybind() {
		Hotkey multipleKeys = new Hotkey(KeyCombination.valueOf("Ctrl+P"));
		
		assertEquals(multipleKeys.isSingleKey(), false);
		assertEquals(multipleKeys.getCombinationKeybind().getKeyCombination().getName().equals("Ctrl+P"), true);
		
		// Throws IllegalStateException since its multiple keys bound.
		multipleKeys.getSingleKeybind();
	}

	/**
	 * Test method for {@link me.oskarmendel.settings.hotkeys.Hotkey#isSingleKey()}.
	 */
	@Test
	public final void testIsSingleKey() {
		Hotkey singleKey = new Hotkey(KeyCode.G);
		
		assertEquals(singleKey.isSingleKey(), true);
	}
	
	/**
	 * Test method for {@link me.oskarmendel.settings.hotkeys.Hotkey#isSingleKey()}.
	 */
	@Test
	public final void testIsNotSingleKey() {
		Hotkey multipleKeys = new Hotkey(KeyCombination.keyCombination("Ctrl+V"));
		
		assertEquals(multipleKeys.isSingleKey(), false);
	}
}
