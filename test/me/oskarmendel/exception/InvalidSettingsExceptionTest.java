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

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import me.oskarmendel.settings.GeneralSettings;

/**
 * Tests for the InvalidSettingsException.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name InvalidSettingsExceptionTest.java
 */
public class InvalidSettingsExceptionTest {

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
	 * Test method for {@link me.oskarmendel.exception.InvalidSettingsException#InvalidSettingsException()}.
	 */
	@Test(expected = InvalidSettingsException.class)
	public final void testInvalidSettingsException() throws InvalidSettingsException {
		InvalidSettingsException settingsException = new InvalidSettingsException();
		
		assertEquals("The InvalidSettingsException should be initialized with null as message.", settingsException.getMessage(), null);
		
		throw new InvalidSettingsException();
	}

	/**
	 * Test method for {@link me.oskarmendel.exception.InvalidSettingsException#InvalidSettingsException(java.lang.String)}.
	 */
	@Test
	public final void testInvalidSettingsExceptionString() {
		try {
			throw new InvalidSettingsException("InvalidSettingsException was thrown.");
		} catch(InvalidSettingsException e) {
			assertEquals("Specified detail message is in the thrown exception.", e.getMessage(), "InvalidSettingsException was thrown.");
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.exception.InvalidSettingsException#InvalidSettingsException(me.oskarmendel.settings.Settings)}.
	 */
	@Test
	public final void testInvalidSettingsExceptionSettings() {
		try {
			throw new InvalidSettingsException(new GeneralSettings());
		} catch(InvalidSettingsException e) {
			assertEquals("Detail message is generated using Settings data.", e.getMessage(), "Invalid settings with location: settings/general.properties");
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.exception.InvalidSettingsException#InvalidSettingsException(java.lang.String, me.oskarmendel.settings.Settings)}.
	 */
	@Test
	public final void testInvalidSettingsExceptionStringSettings() {
		try {
			throw new InvalidSettingsException("Illegal settings", new GeneralSettings());
		} catch(InvalidSettingsException e) {
			assertEquals("Detail message is generated using Settings data.", e.getMessage(), "Illegal settings, with settings location: settings/general.properties");
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.exception.InvalidSettingsException#InvalidSettingsException(java.lang.String, java.lang.Throwable)}.
	 */
	@Test
	public final void testInvalidSettingsExceptionStringThrowable() {
		IOException cause = new IOException();
		try {
			throw new InvalidSettingsException("Invalid Settings specified cause.", cause);
		} catch(InvalidSettingsException e) {
			assertEquals("Specified detail message is added.", e.getMessage(), "Invalid Settings specified cause.");
			assertEquals("Specified cause is added.", e.getCause(), cause);
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.exception.InvalidSettingsException#InvalidSettingsException(java.lang.Throwable)}.
	 */
	@Test
	public final void testInvalidSettingsExceptionThrowable() {
		IOException cause = new IOException();
		try {
			throw new InvalidSettingsException(cause);
		} catch(InvalidSettingsException e) {
			assertEquals("Specified cause is added.", e.getCause(), cause);
		}
	}
}
