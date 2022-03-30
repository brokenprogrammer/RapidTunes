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

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;

/**
 * Immutable class used to represent a Hotkey.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Hotkey.java
 */
public class Hotkey {
	
	final private Keybind keybind;
	
	/**
	 * Constructs a new Hotkey using the specified String, if multiple keys are detected
	 * in the String a KeyCombination is detected otherwise a single keybind is created.
	 * 
	 * @param keys - String containing key or keys to use to construct this Hotkey.
	 */
	public Hotkey(String keys) {
		if (keys.contains("+")) {
			this.keybind = new KeybindCombination(KeyCombination.keyCombination(keys));
		} else {
			this.keybind = new KeybindSingle(KeyCode.valueOf(keys));
		}
	}
	
	/**
	 * Constructs a new Hotkey to hold a single key using the specified KeyCode.
	 * 
	 * @param key - KeyCode of the single key to bind this hotkey to.
	 */
	public Hotkey(KeyCode key) {
		this.keybind = new KeybindSingle(key);
	}
	
	/**
	 * Constructs a new Hotkey to hold a key combination using the specified KeyCombination.
	 * 
	 * @param keyCombination - KeyCombination of the key combination to bind this hotkey to.
	 */
	public Hotkey(KeyCombination keyCombination) {
		this.keybind = new KeybindCombination(keyCombination);
	}
	
	/**
	 * Getter for the single key bind of this Hotkey, throws an IllegalStateException 
	 * if called when the Hotkey contains a KeyCombination.
	 * 
	 * @return - KeybindSingle object of this Hotkey containing a single key bind.
	 */
	public KeybindSingle getSingleKeybind() {
		if (!(this.keybind instanceof KeybindSingle)) {
			throw new IllegalStateException("Hotkey is not a single key hotkey.");
		}
		
		return (KeybindSingle)this.keybind;
	}
	
	/**
	 * Getter for the key combination bind of this Hotkey, throws an IllegalStateException
	 * if called when the Hotkey contains a single key.
	 * 
	 * @return - KeybindCombination object of this Hotkey containing a key combination bind.
	 */
	public KeybindCombination getCombinationKeybind() {
		if (!(this.keybind instanceof KeybindCombination)) {
			throw new IllegalStateException("Hotkey is not a key combination hotkey.");
		}
		
		return (KeybindCombination)this.keybind;
	}
	
	/**
	 * Check if the Keybind is a single key bind or a combination of
	 * multiple keys.
	 * 
	 * @return True if the Keybind is a single key; False otherwise.
	 */
	public boolean isSingleKey() {
		return this.keybind.isSingleKey();
	}
}
