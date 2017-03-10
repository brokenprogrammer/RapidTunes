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
import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name BinaryUtilTest.java
 */
public class BinaryUtilTest {

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
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#readBytes(java.io.InputStream, byte[])}.
	 */
	@Test
	public final void testReadBytesInputStreamByteArray() {
		//Testing to read a file using the demo Flac file.
		File flacFile = new File("./demo/Jimmy Pengiun - Untitled Star.flac");
		try {
			InputStream input =  new BufferedInputStream(new FileInputStream(flacFile));
			byte[] STREAMTAG = new byte[4];
			
			BinaryUtil.readBytes(input, STREAMTAG);
			
			assertEquals("First byte in a flac file reads 'f'", 'f', STREAMTAG[0]);
			assertEquals("Second byte in a flac file reads 'L'", 'L', STREAMTAG[1]);
			assertEquals("Third byte in a flac file reads 'a'", 'a', STREAMTAG[2]);
			assertEquals("Fourth byte in a flac file reads 'C'", 'C', STREAMTAG[3]);
			
		} catch (IOException e) {
			fail("Exception: " + e);
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#readBytes(java.io.InputStream, byte[], int, int)}.
	 */
	@Test(expected = AssertionError.class)
	public final void testReadBytesInputStreamByteArrayIntInt() {
		//Testing to read a file using the demo Flac file.
		File flacFile = new File("./demo/Jimmy Pengiun - Untitled Star.flac");
		long fileSize = flacFile.length();
		try {
			InputStream input =  new BufferedInputStream(new FileInputStream(flacFile));
			byte[] STREAMTAG = new byte[4];
			
			BinaryUtil.readBytes(input, STREAMTAG, 0, STREAMTAG.length);
			
			assertEquals("First byte in a flac file reads 'f'", 'f', STREAMTAG[0]);
			assertEquals("Second byte in a flac file reads 'L'", 'L', STREAMTAG[1]);
			assertEquals("Third byte in a flac file reads 'a'", 'a', STREAMTAG[2]);
			assertEquals("Fourth byte in a flac file reads 'C'", 'C', STREAMTAG[3]);
			
			//Go to the last byte in the file.
			for(long x = 4; x < fileSize-1; x++) {
				input.read();
			}
			
			//Force EOF Exception which in it self gives an AssertionError
			BinaryUtil.readBytes(input, STREAMTAG, 0, STREAMTAG.length);
		} catch (IOException e) {
			fail("Exception: " + e);
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#intToByte(int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testIntToByte() {
		int b1 = 10;
		int b2 = 127;
		int b3 = 200;
		int b4 = 255;
		int b5 = 128;
		int b6 = 4;
		
		assertEquals("Converting int '10' to byte.", BinaryUtil.intToByte(b1), 10);
		assertEquals("Converting int '127' to byte.", BinaryUtil.intToByte(b2), 127);
		assertEquals("Converting int '200' to byte.", BinaryUtil.intToByte(b3), (byte)200);
		assertEquals("Converting int '255' to byte.", BinaryUtil.intToByte(b4), (byte)255);
		assertEquals("Converting int '128' to byte.", BinaryUtil.intToByte(b5), (byte)(1 << 7));
		assertEquals("Converting int '4' to byte.", BinaryUtil.intToByte(b6), (1 << 2));
		
		//Force IllegalArgumentException
		BinaryUtil.intToByte(257);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#byteToInt(byte)}.
	 */
	@Test
	public final void testByteToInt() {
		byte b1 = 0x0F;
		byte b2 = (byte)0xFF;
		byte b3 = Byte.parseByte(BinaryUtil.createByteString(2, 6), 2);
		byte b4 = Byte.parseByte(BinaryUtil.createByteString(4, 2), 2);
		
		
		assertEquals("Converting byte representation of '15' to int.", BinaryUtil.byteToInt(b1), 15);
		assertEquals("Converting byte representation of '255' to int.", BinaryUtil.byteToInt(b2), 255);
		assertEquals("Converting byte representation of '3' to int.", BinaryUtil.byteToInt(b3), 3);
		assertEquals("Converting byte representation of '60' to int.", BinaryUtil.byteToInt(b4), 60);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#getBitAt(byte, int)}.
	 */
	@Test
	public final void testGetBitAt() {
		byte b1 = 0x0F;
		byte b2 = (byte)0xFF;
		byte b3 = Byte.parseByte(BinaryUtil.createByteString(2, 6), 2);
		byte b4 = Byte.parseByte(BinaryUtil.createByteString(4, 2), 2);
		
		assertEquals("Getting bit at position '1' of '15'.", BinaryUtil.getBitAt(b1, 0), 1);
		assertEquals("Getting bit at position '2' of '15'.", BinaryUtil.getBitAt(b1, 1), 1);
		assertEquals("Getting bit at position '3' of '15'.", BinaryUtil.getBitAt(b1, 2), 1);
		assertEquals("Getting bit at position '4' of '15'.", BinaryUtil.getBitAt(b1, 3), 1);
		assertEquals("Getting bit at position '5' of '15'.", BinaryUtil.getBitAt(b1, 4), 0);
		
		assertEquals("Getting bit at position '5' of '255'.", BinaryUtil.getBitAt(b2, 4), 1);
		assertEquals("Getting bit at position '2' of '255'.", BinaryUtil.getBitAt(b2, 1), 1);
		assertEquals("Getting bit at position '8' of '255'.", BinaryUtil.getBitAt(b2, 7), 1);
		
		assertEquals("Getting bit at position '8' of '3'.",BinaryUtil.getBitAt(b3, 7), 0);
		assertEquals("Getting bit at position '1' of '3'.",BinaryUtil.getBitAt(b3, 0), 1);
		assertEquals("Getting bit at position '2' of '3'.",BinaryUtil.getBitAt(b3, 1), 1);
		
		assertEquals("Getting bit at position '3' of '60'.",BinaryUtil.getBitAt(b4, 2), 1);
		assertEquals("Getting bit at position '5' of '60'.",BinaryUtil.getBitAt(b4, 4), 1);
		assertEquals("Getting bit at position '7' of '60'.",BinaryUtil.getBitAt(b4, 6), 0);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#getBitAtBE(byte, int)}.
	 */
	@Test
	public final void testGetBitAtBE() {
		byte b1 = 0x0F;
		byte b2 = (byte)0xFF;
		byte b3 = Byte.parseByte(BinaryUtil.createByteString(2, 6), 2);
		byte b4 = Byte.parseByte(BinaryUtil.createByteString(4, 2), 2);
		
		assertEquals("Getting bit at position '1' of '15'.", BinaryUtil.getBitAtBE(b1, 0), 0);
		assertEquals("Getting bit at position '2' of '15'.", BinaryUtil.getBitAtBE(b1, 1), 0);
		assertEquals("Getting bit at position '3' of '15'.", BinaryUtil.getBitAtBE(b1, 2), 0);
		assertEquals("Getting bit at position '4' of '15'.", BinaryUtil.getBitAtBE(b1, 3), 0);
		assertEquals("Getting bit at position '5' of '15'.", BinaryUtil.getBitAtBE(b1, 4), 1);
		
		assertEquals("Getting bit at position '5' of '255'.", BinaryUtil.getBitAtBE(b2, 4), 1);
		assertEquals("Getting bit at position '2' of '255'.", BinaryUtil.getBitAtBE(b2, 1), 1);
		assertEquals("Getting bit at position '8' of '255'.", BinaryUtil.getBitAtBE(b2, 7), 1);
		
		assertEquals("Getting bit at position '8' of '3'.",BinaryUtil.getBitAtBE(b3, 7), 1);
		assertEquals("Getting bit at position '1' of '3'.",BinaryUtil.getBitAtBE(b3, 0), 0);
		assertEquals("Getting bit at position '2' of '3'.",BinaryUtil.getBitAtBE(b3, 1), 0);
		
		assertEquals("Getting bit at position '3' of '60'.",BinaryUtil.getBitAtBE(b4, 2), 1);
		assertEquals("Getting bit at position '5' of '60'.",BinaryUtil.getBitAtBE(b4, 4), 1);
		assertEquals("Getting bit at position '7' of '60'.",BinaryUtil.getBitAtBE(b4, 6), 0);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#getTopNibble(byte)}.
	 */
	@Test
	public final void testGetTopNibbleByte() {
		byte b1 = 0x0F;
		byte b2 = (byte)0xFF;
		byte b3 = Byte.parseByte(BinaryUtil.createByteString(2, 6), 2);
		byte b4 = Byte.parseByte(BinaryUtil.createByteString(4, 2), 2);
		
		assertEquals("Getting the top nibble of '15'.", BinaryUtil.getTopNibble(b1), 0);
		assertEquals("Getting the top nibble of '255'.", BinaryUtil.getTopNibble(b2), 15);
		assertEquals("Getting the top nibble of '3'.", BinaryUtil.getTopNibble(b3), 0);
		assertEquals("Getting the top nibble of '60'.", BinaryUtil.getTopNibble(b4), 3);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#getBottomNibble(byte)}.
	 * TODO Implement.
	 */
	@Test
	public final void testGetBottomNibbleByte() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#getTopNibble(int)}.
	 * TODO Implement.
	 */
	@Test
	public final void testGetTopNibbleInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#getBottomNibble(int)}.
	 * TODO Implement.
	 */
	@Test
	public final void testGetBottomNibbleInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToInt(int, int)}.
	 * TODO Implement.
	 */
	@Test
	public final void testAddBytesToIntIntInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToInt(int, int, int)}.
	 * TODO Implement.
	 */
	@Test
	public final void testAddBytesToIntIntIntInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToInt(int, int, int, int)}.
	 * TODO Implement.
	 */
	@Test
	public final void testAddBytesToIntIntIntIntInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToIntBE(int, int)}.
	 * TODO Implement.
	 */
	@Test
	public final void testAddBytesToIntBEIntInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToIntBE(int, int, int)}.
	 * TODO Implement.
	 */
	@Test
	public final void testAddBytesToIntBEIntIntInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToIntBE(int, int, int, int)}.
	 * TODO Implement.
	 */
	@Test
	public final void testAddBytesToIntBEIntIntIntInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToInt4(byte[])}.
	 * TODO Implement.
	 */
	@Test
	public final void testAddBytesToInt4ByteArray() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToInt4(byte[], int)}.
	 * TODO Implement.
	 */
	@Test
	public final void testAddBytesToInt4ByteArrayInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToInt4BE(byte[])}.
	 * TODO Implement.
	 */
	@Test
	public final void testAddBytesToInt4BEByteArray() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToInt4BE(byte[], int)}.
	 * TODO Implement.
	 */
	@Test
	public final void testAddBytesToInt4BEByteArrayInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#createByteString(int, int)}.
	 * TODO Implement.
	 */
	@Test
	public final void testCreateByteString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#getBytesToString(byte[], int, int)}.
	 * TODO Implement.
	 */
	@Test
	public final void testGetBytesToString() {
		fail("Not yet implemented");
	}

}
