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

package me.oskarmendel.exception;

import me.oskarmendel.settings.Settings;

/**
 * Exception class thrown when an illegal settings is encountered.
 * 
 * @author Oskar Mendel
 * @name InvalidSettingsException.java
 * @version 0.00.00
 */
public class InvalidSettingsException extends Exception {

	/**
	 * Generated UID for serialization.
	 */
	private static final long serialVersionUID = 5270213007809315095L;
	
	/**
	 * Default constructor for the InvalidSettingsException, constructs a new InvalidSettingsException 
	 * with null as its detail message. 
	 */
	public InvalidSettingsException() {
		super();
	}
	
	/**
	 * Constructs a new InvalidSettingsException with the specified detail message.
	 * 
	 * @param message - The detail message.
	 */
	public InvalidSettingsException(String message) {
		super(message);
	}
	
	/**
	 * Constructs a new InvalidSettingsException with the a generated detail message using
	 * the specified Settings object.
	 * 
	 * @param settings - Settings to generate detail message from.
	 */
	public InvalidSettingsException(Settings settings) {
		super("Invalid settings with location: " + settings.getPath());
	}
	
	/**
	 * Constructs a new InvalidSettingsException with the specified detail message and data from
	 * the specified Settings object.
	 * 
	 * @param message - The detail message.
	 * @param player - Settings object to retrieve data from.
	 */
	public InvalidSettingsException(String message, Settings settings) {
		super(message + ", with settings location: " + settings.getPath());
	}
	
	/**
	 * Constructs a new InvalidSettingsException with the specified detail message and cause. 
	 * 
	 * @param message - The detail message.
	 * @param cause - The cause.
	 */
	public InvalidSettingsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Constructs a new InvalidSettingsException with the specified cause and a detail message of 
	 * (cause==null ? null : cause.toString())
	 * 
	 * @param cause - The cause.
	 */
	public InvalidSettingsException(Throwable cause) {
		super(cause);
	}
}
