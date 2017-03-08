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

package me.oskarmendel.util.song.flac;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FlacParserTest.java
 */
public class FlacParserTest {
	
	File flacFile;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		flacFile = new File("./demo/Jimmy Pengiun - Untitled Star.flac");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacParser#parseFlacFile(java.io.File, me.oskarmendel.util.song.flac.FlacFile)}.
	 */
	@Test
	public final void testParseFlacFile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacParser#isFlacFile(java.io.File)}.
	 */
	@Test
	public final void testIsFlacFile() {
		try {
			assertEquals("The specified file is of the type 'flac'", true, FlacParser.isFlacFile(flacFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
