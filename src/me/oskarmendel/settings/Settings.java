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
 * Abstract Settings class that defines abstract behavior shared 
 * between different types of Settings.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Settings.java
 */
public abstract class Settings {
	
	/**
	 * Default constructor for the Abstract Settings object managing 
	 * all the common parts of constructing a Settings child object.
	 */
	public Settings() { }
	
	/**
	 * Returns the path for the settings file.
	 * This path is used when saving / loading setting files.
	 * 
	 * @return - Path of the Settings file.
	 */
	public abstract String getPath();
	
	/**
	 * Returns a Settings object converted into a Properties object.
	 * 
	 * @return - Properties object with all the Settings defined by the target Settings object.
	 */
	public abstract Properties toProperties();
	
	/**
	 * Returns the default Properties object defined by the target Settings object.
	 * 
	 * @return - Properties object with all the default properties.
	 */
	public abstract Properties getDefaultProperties();
}
