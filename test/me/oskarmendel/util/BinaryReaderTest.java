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
			
			reader.close();
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
			reader.close();
		} catch (FileNotFoundException e) {
			assertThat("Expected message is equal.", e.getMessage(), notNullValue());
		} catch(IOException e) {
			e.printStackTrace();
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
			
			reader.close();
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

			assertThat("Read bytes is matching actual read bytes.", reader.getPosition(), is(4L));
			
			reader.close();
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
		
		// Test reading a value too large for an unsigned it.
		try {
			reader = new BinaryReader(flacFile);
			assertEquals("First byte in a flac file reads 'f'", 'f', reader.readUnsignedInt(8));
			assertEquals("First byte in a flac file reads 'L'", 'L', reader.readUnsignedInt(8));
			assertEquals("First byte in a flac file reads 'a'", 'a', reader.readUnsignedInt(8));
			assertEquals("First byte in a flac file reads 'C'", 'C', reader.readUnsignedInt(8));
			
			assertEquals("Read bytes is matching actual read bytes.", reader.getPosition(), 4);
			
			reader.readUnsignedInt(35);
			
			fail("Exception should be catched.");
			reader.close();
		} catch (IllegalArgumentException ar) {
			assertThat("Expected message is equal.", ar.getMessage(), 
					is("Specified number of bits (35) does not fit in an unsigned integer."));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Test Reading until EOF.
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
			
			reader.close();
		} catch (IllegalStateException e) {
			assertThat("Expected message is equal.", e.getMessage(), 
					is("Reached EOF."));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryReader#readSignedInt(int)}
	 */
	@Test
	public final void testReadSignedInt() {
		File flacFile = new File("./demo/Jimmy Pengiun - Untitled Star.flac");
		BinaryReader reader = null;
		
		try {
			reader = new BinaryReader(flacFile);
			assertEquals("First byte in a flac file reads 'f'", 'f', reader.readSignedInt(8));
			assertEquals("First byte in a flac file reads 'L'", 'L', reader.readSignedInt(8));
			assertEquals("First byte in a flac file reads 'a'", 'a', reader.readSignedInt(8));
			assertEquals("First byte in a flac file reads 'C'", 'C', reader.readSignedInt(8));
			
			assertEquals("Read bytes is matching actual read bytes.", reader.getPosition(), 4);
			
			int i = 0;
			while (i == 0) {
				i = reader.readSignedInt(8);
			}
			
			assertThat("First unsigned value after 'fLaC' tag is 32", i, is(34));
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryReader#readByte()}
	 */
	@Test
	public final void testReadByte() {
		File flacFile = new File("./demo/Jimmy Pengiun - Untitled Star.flac");
		BinaryReader reader = null;
		
		try {
			reader = new BinaryReader(flacFile);
			
			assertEquals("First byte in a flac file reads 'f'", 'f', reader.readByte());
			assertEquals("First byte in a flac file reads 'L'", 'L', reader.readByte());
			assertEquals("First byte in a flac file reads 'a'", 'a', reader.readByte());
			assertEquals("First byte in a flac file reads 'C'", 'C', reader.readByte());
			
			assertEquals("Read bytes is matching actual read bytes.", reader.getPosition(), 4);
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryReader#close()}
	 */
	@Test
	public final void testClose() {
		File flacFile = new File("./demo/Jimmy Pengiun - Untitled Star.flac");
		BinaryReader reader = null;
		
		try {
			reader = new BinaryReader(flacFile);
			
			assertEquals("First byte in a flac file reads 'f'", 'f', reader.readByte());
			assertEquals("First byte in a flac file reads 'L'", 'L', reader.readByte());
			assertEquals("First byte in a flac file reads 'a'", 'a', reader.readByte());
			assertEquals("First byte in a flac file reads 'C'", 'C', reader.readByte());
			
			assertEquals("Read bytes is matching actual read bytes.", reader.getPosition(), 4);
			
			reader.close();
			assertThat("Position is invalidated.", reader.getPosition(), is(-1L));
			assertThat("Bit position is invalidated.", reader.getBitPosition(), is(-1));
			assertThat("Length is invalidated.", reader.getLength(), is(-1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryReader#seekTo(long)}
	 */
	@Test
	public final void testSeekTo() {
		File flacFile = new File("./demo/Jimmy Pengiun - Untitled Star.flac");
		BinaryReader reader = null;
		
		try {
			reader = new BinaryReader(flacFile);
			
			reader.seekTo(1);
			assertEquals("Read bytes is matching actual read bytes.", reader.getPosition(), 1);
			
			reader.seekTo(2);
			assertEquals("Read bytes is matching actual read bytes.", reader.getPosition(), 2);
			
			reader.seekTo(3);
			assertEquals("Read bytes is matching actual read bytes.", reader.getPosition(), 3);
			
			reader.seekTo(4);
			assertEquals("Read bytes is matching actual read bytes.", reader.getPosition(), 4);
			
			reader.seekTo(50000);
			assertEquals("Read bytes is matching actual read bytes.", reader.getPosition(), 50000);
			
			reader.seekTo(5758978);
			assertEquals("Read bytes is matching actual read bytes.", reader.getPosition(), 5758978);
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryReader#getBitPosition()}
	 */
	@Test
	public final void testGetBitPosition() {
		File flacFile = new File("./demo/Jimmy Pengiun - Untitled Star.flac");
		BinaryReader reader;
		
		try {
			reader = new BinaryReader(flacFile);
			
			int i = 0;
			
			i = reader.readUnsignedInt(1);
			assertEquals("Current bit position: 1", reader.getBitPosition(), 1);
			
			i = reader.readUnsignedInt(1);
			assertEquals("Current bit position: 2", reader.getBitPosition(), 2);
			
			i = reader.readUnsignedInt(1);
			assertEquals("Current bit position: 3", reader.getBitPosition(), 3);
			
			i = reader.readUnsignedInt(1);
			assertEquals("Current bit position: 4", reader.getBitPosition(), 4);
			
			i = reader.readUnsignedInt(2);
			assertEquals("Current bit position: 6", reader.getBitPosition(), 6);
			
			i = reader.readUnsignedInt(3);
			assertEquals("Current bit position: 1", reader.getBitPosition(), 1);
			
			i = reader.readUnsignedInt(6);
			assertEquals("Current bit position: 7", reader.getBitPosition(), 7);
			
			i = reader.readUnsignedInt(13);
			assertEquals("Current bit position: 4", reader.getBitPosition(), 4);
			
			reader.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}
