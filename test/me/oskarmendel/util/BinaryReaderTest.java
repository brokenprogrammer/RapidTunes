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

package me.oskarmendel.util;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name BinaryReaderTest.java
 */
public class BinaryReaderTest {

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
	 * Test method for {@link me.oskarmendel.util.BinaryReader#BinaryReader(java.io.File)}.
	 */
	@Test
	public final void testBinaryReaderFile() {
		File flacFile = new File("./demo/Jimmy Pengiun - Untitled Star.flac");
		BinaryReader reader;
		
		try {
			reader = new BinaryReader(flacFile);
			assertEquals("First four bytes in a flac file reads 'fLaC'", 
					reader.readUnsignedInt(32) == 0x664C6143, true);
			
		} catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	/**
	 * Test method for exceptions in {@link me.oskarmendel.util.BinaryReader#BinaryReader(java.io.File)}.
	 */
	@Test
	public final void testBinaryReaderFileExceptions() {
		File nonExistingFile = new File("path/to/nonexisting/file.java");
		BinaryReader reader;
		
		try {
			reader = new BinaryReader(nonExistingFile);
			fail("Expected an FileNotFoundException to be thrown.");
			
		} catch (FileNotFoundException e) {
			assertThat("Expected message is equal.", e.getMessage(), notNullValue());
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryReader#BinaryReader(java.io.InputStream)}.
	 */
	@Test
	public final void testBinaryReaderInputStream() {
		File flacFile = new File("./demo/Jimmy Pengiun - Untitled Star.flac");
		BinaryReader reader;
		
		try {
			InputStream input = new BufferedInputStream(new FileInputStream(flacFile));
			reader = new BinaryReader(input);
			
			assertEquals("First four bytes in a flac file reads 'fLaC'", 
					reader.readUnsignedInt(32) == 0x664C6143, true);
			
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryReader#readUnsignedInt(int)}
	 */
	@Test
	public final void testReadUnsignedInt() {
		File flacFile = new File("./demo/Jimmy Pengiun - Untitled Star.flac");
		BinaryReader reader;
		
		try {
			reader = new BinaryReader(flacFile);
			assertEquals("First byte in a flac file reads 'f'", 'f', reader.readUnsignedInt(8));
			assertEquals("First byte in a flac file reads 'L'", 'L', reader.readUnsignedInt(8));
			assertEquals("First byte in a flac file reads 'a'", 'a', reader.readUnsignedInt(8));
			assertEquals("First byte in a flac file reads 'C'", 'C', reader.readUnsignedInt(8));

			assertThat("Read bytes is matching actual read bytes.", reader.getPosition(), is(4));
			
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	/**
	 * Test method for exceptions in {@link me.oskarmendel.util.BinaryReader#readUnsignedInt(int)}
	 */
	@Test
	public final void testReadUnsignedIntExceptions() {
		File flacFile = new File("./demo/Jimmy Pengiun - Untitled Star.flac");
		BinaryReader reader = null;
		
		try {
			reader = new BinaryReader(flacFile);
			assertEquals("First byte in a flac file reads 'f'", 'f', reader.readUnsignedInt(8));
			assertEquals("First byte in a flac file reads 'L'", 'L', reader.readUnsignedInt(8));
			assertEquals("First byte in a flac file reads 'a'", 'a', reader.readUnsignedInt(8));
			assertEquals("First byte in a flac file reads 'C'", 'C', reader.readUnsignedInt(8));
			
			assertEquals("Read bytes is matching actual read bytes.", reader.getPosition(), 4);
			
			reader.readUnsignedInt(35);
			fail("Exception should be catched.");
			
		} catch (IllegalArgumentException ar) {
			assertThat("Expected message is equal.", ar.getMessage(), 
					is("Specified number of bits (35) does not fit in an unsigned integer."));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			if (reader != null) {
				reader.seekTo(reader.getLength());
				
				int i = 0;
				while (i != -1) {
					i = reader.readUnsignedInt(8);
				}
				
				fail("Exception should be catched.");
			} else {
				fail("BinaryReader is not initialized.");
			}
			
		} catch (IllegalStateException e) {
			assertThat("Expected message is equal.", e.getMessage(), 
					is("Reached EOF."));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void testReadSignedInt() {
		fail("Not yet implemented");
	}

	@Test
	public final void testReadByte() {
		fail("Not yet implemented");
	}

	@Test
	public final void testClose() {
		fail("Not yet implemented");
	}

	@Test
	public final void testSeekTo() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetPosition() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetBitPosition() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetLength() {
		fail("Not yet implemented");
	}

}
