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
	 */
	@Test
	public final void testGetBottomNibbleByte() {
		byte b1 = 0x0F; //0000 1111
		byte b2 = (byte)0xFF; //1111 1111
		byte b3 = Byte.parseByte(BinaryUtil.createByteString(2, 6), 2); //00000011
		byte b4 = Byte.parseByte(BinaryUtil.createByteString(4, 2), 2); //00111100
		
		assertEquals("Getting the bottom nibble of '15'.", BinaryUtil.getBottomNibble(b1), 15);
		assertEquals("Getting the bottom nibble of '255'.", BinaryUtil.getBottomNibble(b2), 15);
		assertEquals("Getting the bottom nibble of '3'.", BinaryUtil.getBottomNibble(b3), 3);
		assertEquals("Getting the bottom nibble of '60'.", BinaryUtil.getBottomNibble(b4), 12);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#getTopNibble(int)}.
	 */
	@Test
	public final void testGetTopNibbleInt() {
		int b1 = 15;
		int b2 = 255;
		int b3 = 3;
		int b4 = 60;
		
		assertEquals("Getting the top nibble of '15'.", BinaryUtil.getTopNibble(b1), 0);
		assertEquals("Getting the top nibble of '255'.", BinaryUtil.getTopNibble(b2), 15);
		assertEquals("Getting the top nibble of '3'.", BinaryUtil.getTopNibble(b3), 0);
		assertEquals("Getting the top nibble of '60'.", BinaryUtil.getTopNibble(b4), 3);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#getBottomNibble(int)}.
	 */
	@Test
	public final void testGetBottomNibbleInt() {
		int b1 = 15;
		int b2 = 255;
		int b3 = 3;
		int b4 = 60;
		
		assertEquals("Getting the top nibble of '15'.", BinaryUtil.getBottomNibble(b1), 15);
		assertEquals("Getting the top nibble of '255'.", BinaryUtil.getBottomNibble(b2), 15);
		assertEquals("Getting the top nibble of '3'.", BinaryUtil.getBottomNibble(b3), 3);
		assertEquals("Getting the top nibble of '60'.", BinaryUtil.getBottomNibble(b4), 12);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToInt(int, int)}.
	 */
	@Test
	public final void testAddBytesToIntIntInt() {
		int b1 = 15;
		int b2 = 255;
		int b3 = 3;
		int b4 = 60;
		
		//1111 1111 0000 1111 = 65295
		assertEquals("Adding bytes of '255' and '15'.", BinaryUtil.addBytesToInt(b1, b2), 65295);
		//0000 0011 1111 1111 = 1023
		assertEquals("Adding bytes of '3' and '255'.", BinaryUtil.addBytesToInt(b2, b3), 1023);
		//0011 1100 0000 0011 = 15363
		assertEquals("Adding bytes of '60' and '3'.", BinaryUtil.addBytesToInt(b3, b4), 15363);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToInt(int, int, int)}.
	 */
	@Test
	public final void testAddBytesToIntIntIntInt() {
		int b1 = 15;
		int b2 = 255;
		int b3 = 3;
		int b4 = 60;
		
		//0000 0011 1111 1111 0000 1111 = 261903
		assertEquals("Adding bytes of '3', '255' and '15'.", BinaryUtil.addBytesToInt(b1, b2, b3), 261903);
		//0011 1100 0000 0011 1111 1111 = 3933183
		assertEquals("Adding bytes of '60', '3' and '255'.", BinaryUtil.addBytesToInt(b2, b3, b4), 3933183);
		//0000 1111 0011 1100 0000 0011 = 998403
		assertEquals("Adding bytes of '15', '60' and '3'.", BinaryUtil.addBytesToInt(b3, b4, b1), 998403);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToInt(int, int, int, int)}.
	 */
	@Test
	public final void testAddBytesToIntIntIntIntInt() {
		int b1 = 15;
		int b2 = 255;
		int b3 = 3;
		int b4 = 60;
		
		// 0011 1100 0000 0011 1111 1111 0000 1111 = 261951
		assertEquals("Adding bytes of '60', '3', '255' and '15'.", BinaryUtil.addBytesToInt(b1, b2, b3, b4), 261951);
		//0000 1111 0011 1100 0000 0011 1111 1111 = 3933183
		assertEquals("Adding bytes of '15', '60', '3' and '255'.", BinaryUtil.addBytesToInt(b2, b3, b4, b1), 3933183);
		//1111 1111 0000 1111 0011 1100 0000 0011 = 998655
		assertEquals("Adding bytes of '255', '15', '60' and '3'.", BinaryUtil.addBytesToInt(b3, b4, b1, b2), 998655);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToIntBE(int, int)}.
	 */
	@Test
	public final void testAddBytesToIntBEIntInt() {
		int b1 = 15;
		int b2 = 255;
		int b3 = 3;
		int b4 = 60;
		
		//0000 1111 1111 1111 = 4095
		assertEquals("Adding bytes of '15' and '255'.", BinaryUtil.addBytesToIntBE(b1, b2), 4095);
		//1111 1111 0000 0011 = 65283
		assertEquals("Adding bytes of '255' and '3'.", BinaryUtil.addBytesToIntBE(b2, b3), 65283);
		//0000 0011 0011 1100 = 828
		assertEquals("Adding bytes of '3' and '60'.", BinaryUtil.addBytesToIntBE(b3, b4), 828);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToIntBE(int, int, int)}.
	 */
	@Test
	public final void testAddBytesToIntBEIntIntInt() {
		int b1 = 15;
		int b2 = 255;
		int b3 = 3;
		int b4 = 60;
		
		//0000 1111 1111 1111 0000 0011 = 1048323
		assertEquals("Adding bytes of '15', '255' and '3'.", BinaryUtil.addBytesToIntBE(b1, b2, b3), 1048323);
		//1111 1111 0000 0011 0011 1100 = 16712508
		assertEquals("Adding bytes of '255', '3' and '60'.", BinaryUtil.addBytesToIntBE(b2, b3, b4), 16712508);
		//0000 0011 0011 1100 0000 1111 = 211983
		assertEquals("Adding bytes of '3', '60' and '15'.", BinaryUtil.addBytesToIntBE(b3, b4, b1), 211983);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToIntBE(int, int, int, int)}.
	 */
	@Test
	public final void testAddBytesToIntBEIntIntIntInt() {
		int b1 = 15;
		int b2 = 255;
		int b3 = 3;
		int b4 = 60;
		
		//0000 1111 1111 1111 0000 0011 0011 1100 = 16712511
		assertEquals("Adding bytes of '15', '255', '3' and '60'.", BinaryUtil.addBytesToIntBE(b1, b2, b3, b4), 16712511);
		
		//1111 1111 0000 0011 0011 1100 0000 1111 = 212223
		assertEquals("Adding bytes of '255', '3', '60' and '15'.", BinaryUtil.addBytesToIntBE(b2, b3, b4, b1), 212223);
		
		//0000 0011 0011 1100 0000 1111 1111 1111 = 3936255
		assertEquals("Adding bytes of '3', '60', '15' and '255'.", BinaryUtil.addBytesToIntBE(b3, b4, b1, b2), 3936255);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToInt4(byte[])}.
	 */
	@Test
	public final void testAddBytesToInt4ByteArray() {
		byte[] b1 = {15, (byte) 255, 3, 60};
		byte[] b2 = {(byte) 255, 3, 60, 15};
		byte[] b3 = {3, 60, 15, (byte) 255};
		
		assertEquals("Adding bytes of '60', '3', '255' and '15'.", BinaryUtil.addBytesToInt4(b1), 261951);
		assertEquals("Adding bytes of '15', '60', '3' and '255'.", BinaryUtil.addBytesToInt4(b2), 3933183);
		assertEquals("Adding bytes of '255', '15', '60' and '3'.", BinaryUtil.addBytesToInt4(b3), 998655);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToInt4(byte[], int)}.
	 */
	@Test
	public final void testAddBytesToInt4ByteArrayInt() {
		byte[] b1 = {15, (byte) 255, 3, 60, 1, 127};
		byte[] b2 = {1, 44, 82, (byte) 255, 3, 60, 15};
		byte[] b3 = {111, 32, (byte) 222, (byte) 210, 3, 60, 15, (byte) 255};
		
		assertEquals("Adding bytes of '60', '3', '255' and '15'.", BinaryUtil.addBytesToInt4(b1, 0), 261951);
		assertEquals("Adding bytes of '15', '60', '3' and '255'.", BinaryUtil.addBytesToInt4(b2, 3), 3933183);
		assertEquals("Adding bytes of '255', '15', '60' and '3'.", BinaryUtil.addBytesToInt4(b3, 4), 998655);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToInt4BE(byte[])}.
	 */
	@Test
	public final void testAddBytesToInt4BEByteArray() {
		byte[] b1 = {15, (byte) 255, 3, 60};
		byte[] b2 = {(byte) 255, 3, 60, 15};
		byte[] b3 = {3, 60, 15, (byte) 255};
		
		assertEquals("Adding bytes of '15', '255', '3' and '60'.", BinaryUtil.addBytesToInt4BE(b1), 16712511);
		assertEquals("Adding bytes of '255', '3', '60' and '15'.", BinaryUtil.addBytesToInt4BE(b2), 212223);
		assertEquals("Adding bytes of '3', '60', '15' and '255'.", BinaryUtil.addBytesToInt4BE(b3), 3936255);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#addBytesToInt4BE(byte[], int)}.
	 */
	@Test
	public final void testAddBytesToInt4BEByteArrayInt() {
		byte[] b1 = {15, (byte) 255, 3, 60, 1, 127};
		byte[] b2 = {1, 44, 82, (byte) 255, 3, 60, 15};
		byte[] b3 = {111, 32, (byte) 222, (byte) 210, 3, 60, 15, (byte) 255};
		
		assertEquals("Adding bytes of '15', '255', '3' and '60'.", BinaryUtil.addBytesToInt4BE(b1, 0), 16712511);
		assertEquals("Adding bytes of '255', '3', '60' and '15'.", BinaryUtil.addBytesToInt4BE(b2, 3), 212223);
		assertEquals("Adding bytes of '3', '60', '15' and '255'.", BinaryUtil.addBytesToInt4BE(b3, 4), 3936255);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#createByteString(int, int)}.
	 */
	@Test
	public final void testCreateByteString() {
		String fifteen = BinaryUtil.createByteString(4, 4);
		byte fifteenByte = Byte.parseByte(fifteen, 2);
		
		String three = BinaryUtil.createByteString(2, 6);
		byte threeByte = Byte.parseByte(three, 2);
		
		String fivehundredfiftyfive = BinaryUtil.createByteString(8, 0);
		int fivehundredfiftyfiveInt = Integer.parseInt(fivehundredfiftyfive, 2);
		
		assertEquals("Created byte string representing '15'", fifteen, "00001111");
		assertEquals("Created byte string is representing '15'", BinaryUtil.byteToInt(fifteenByte), 15);
		
		assertEquals("Created byte string representing '3'", three, "00000011");
		assertEquals("Created byte string is representing '3'", BinaryUtil.byteToInt(threeByte), 3);
		
		assertEquals("Created byte string representing '255'", fivehundredfiftyfive, "11111111");
		assertEquals("Created byte string is representing '255'", fivehundredfiftyfiveInt, 255);
	}

	/**
	 * Test method for {@link me.oskarmendel.util.BinaryUtil#getBytesToString(byte[], int, int)}.
	 */
	@Test
	public final void testGetBytesToString() {
		String helloStr = "Hello";
		byte[] helloBytes = helloStr.getBytes();
		
		String oskarMendelStr = "Oskar Mendel";
		byte[] oskarMendelBytes = oskarMendelStr.getBytes();
		
		assertEquals("String within helloBytes is 'Hello'", BinaryUtil.getBytesToString(helloBytes, 0, helloBytes.length), helloStr);
		assertEquals("String within oskarMendelBytes is 'Oskar Mendel'", 
				BinaryUtil.getBytesToString(oskarMendelBytes, 0, oskarMendelBytes.length), oskarMendelStr);
		assertEquals("String within oskarMendelBytes is 'Mendel'", 
				BinaryUtil.getBytesToString(oskarMendelBytes, 6, 6), "Mendel");
	}

}
