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

import javafx.scene.input.KeyCombination;

/**
 * Immutable KeybindCombination class represents a Keybind bound to a multiple key.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name KeybindCombination.java
 */
public class KeybindCombination implements Keybind {
	
	private final KeyCombination keyCombination;
	
	/**
	 * Constructs a new KeybindCombination object using the specified
	 * KeyCombination.
	 * 
	 * @param keyCombination - KeyCombination value to initialize this KeybindCombination to.
	 */
	public KeybindCombination(KeyCombination keyCombination) {
		this.keyCombination = keyCombination;
	}
	
	/**
	 * Getter for the KeyCombination of this KeybindCombination.
	 * 
	 * @return - KeyCombination of this KeybindCombination.
	 */
	public KeyCombination getKeyCombination() {
		return this.keyCombination;
	}
	
	/**
	 * Check if the Keybind is a single key bind or a combination of
	 * multiple keys.
	 * 
	 * @return True if the Keybind is a single key; False otherwise.
	 */
	@Override
	public boolean isSingleKey() {
		return false;
	}
}
