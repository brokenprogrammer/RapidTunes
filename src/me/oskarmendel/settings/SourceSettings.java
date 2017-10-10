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
 * SourceSettings class that stores and controls settings handling
 * different music sources.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SourceSettings.java
 */
public class SourceSettings extends Settings {

	private static final String PATH = "settings/source.properties";
	
	/**
	 * 
	 */
	public SourceSettings() {
		super();
	}
	
	/**
	 * 
	 * @param properties
	 */
	public SourceSettings(Properties properties) {
		super();
	}
	
	@Override
	public String getPath() {
		return SourceSettings.PATH;
	}

	@Override
	public Properties toProperties() {
		Properties properties = new Properties();
		
		return properties;
	}
	
	@Override
	public Properties getDefaultProperties() {
		Properties properties = new Properties();
		
		return properties;
	}
}