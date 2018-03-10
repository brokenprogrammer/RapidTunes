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

/**
 * Immutable KeybindSingle class represents a Keybind bound to a single key.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name KeybindSingle.java
 */
public class KeybindSingle implements Keybind {
	
	private final KeyCode key;
	
	/**
	 * Constructs a new KeybindSingle with the specified KeyCode.
	 * 
	 * @param key - KeyCode for this KeybindSingle to represent.
	 */
	public KeybindSingle(KeyCode key) {
		this.key = key;
	}
	
	/**
	 * Getter for the KeyCode of this KeybindSingle.
	 * 
	 * @return - KeyCode of this KeybindSingle.
	 */
	public KeyCode getKey() {
		return this.key;
	}
	
	/**
	 * Check if the Keybind is a single key bind or a combination of
	 * multiple keys.
	 * 
	 * @return True if the Keybind is a single key; False otherwise.
	 */
	@Override
	public boolean isSingleKey() {
		return true;
	}
}
