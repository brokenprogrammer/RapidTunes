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

package me.oskarmendel.song;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import me.oskarmendel.song.Song;
import me.oskarmendel.song.YouTubeSong;


/**
 * 
 * @author Jesper Bergström
 * @version 0.00.00
 * @name YouTubeSongTest.java
 */
public class YouTubeSongTest {
	
	Song song;
	
	@Before
	public void setUp() throws Exception {
		song = new YouTubeSong();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link me.oskarmendel.song.YouTubeSong#getTitle()}.
	 */
	@Test
	public void testGetTitle() {
		song.setTitle("The Middle");
		assertEquals("Title of the song is 'The Middle'", "The Middle", song.getTitle());
		song.setTitle("Sweetness");
		assertEquals("Title of the song is 'Sweetness'", "Sweetness", song.getTitle());
	}

	/**
	 * Test method for {@link me.oskarmendel.song.YouTubeSong#setTitle()}.
	 */
	@Test
	public void testSetTitle() {
		song.setTitle("The Middle");
		assertEquals("Title of the song is 'The Middle'", "The Middle", song.getTitle());
		song.setTitle("Sweetness");
		assertEquals("Title of the song is 'Sweetness'", "Sweetness", song.getTitle());
	}

	/**
	 * Test method for {@link me.oskarmendel.song.YouTubeSong#getArtist()}.
	 */
	@Test
	public void testGetArtist() {
		song.setArtist("Jimmy Eat World");
		assertEquals("Artist of the song is 'Jimmy Eat World'", "Jimmy Eat World", song.getArtist());
		song.setArtist("Nirvana");
		assertEquals("Artist of the song is 'Nirvana'", "Nirvana", song.getArtist());
	}

	/**
	 * Test method for {@link me.oskarmendel.song.YouTubeSong#setArtist()}.
	 */
	@Test
	public void testSetArtist() {
		song.setArtist("Jimmy Eat World");
		assertEquals("Artist of the song is 'Jimmy Eat World'", "Jimmy Eat World", song.getArtist());
		song.setArtist("Nirvana");
		assertEquals("Artist of the song is 'Nirvana'", "Nirvana", song.getArtist());
	}

	/**
	 * Test method for {@link me.oskarmendel.song.YouTubeSong#getAlbum()}.
	 */
	@Test
	public void testGetAlbum() {
		song.setAlbum("Bleed American");
		assertEquals("Album of the song is 'Bleed American'", "Bleed American", song.getAlbum());
		song.setAlbum("Futures");
		assertEquals("Album of the song is 'Futures'", "Futures", song.getAlbum());
	}

	/**
	 * Test method for {@link me.oskarmendel.song.YouTubeSong#setAlbum()}.
	 */
	@Test
	public void testSetAlbum() {
		song.setAlbum("Bleed American");
		assertEquals("Album of the song is 'Bleed American'", "Bleed American", song.getAlbum());
		song.setAlbum("Futures");
		assertEquals("Album of the song is 'Futures'", "Futures", song.getAlbum());
	}

	/**
	 * Test method for {@link me.oskarmendel.song.YouTubeSong#getLength()}.
	 */
	@Test
	public void testGetLength() {
		song.setLength("120");
		assertEquals("Length of the song is '120'", "120", song.getLength());
		song.setLength("1000");
		assertEquals("Length of the song is '1000'", "1000", song.getLength());
	}

	/**
	 * Test method for {@link me.oskarmendel.song.YouTubeSong#setLength()}.
	 */
	@Test
	public void testSetLength() {
		song.setLength("120");
		assertEquals("Length of the song is '120'", "120", song.getLength());
		song.setLength("1000");
		assertEquals("Length of the song is '1000'", "1000", song.getLength());
	}
	
	/**
	 * Test method for {@link me.oskarmendel.song.YouTubeSong#getPath()}.
	 */
	@Test
	public void testGetPath() {
		song.setPath("C:\\King.mp3");
		assertEquals("Path of the song is 'C:\\King.mp3'", "C:\\King.mp3", song.getPath());
		song.setPath("C:\\Users\\Jesper\\Music\\King.mp3");
		assertEquals("Path of the song is 'C:\\Users\\Jesper\\Music\\King.mp3'", "C:\\Users\\Jesper\\Music\\King.mp3", song.getPath());
	}
	
	/**
	 * Test method for {@link me.oskarmendel.song.YouTubeSong#setPath()}.
	 */
	@Test
	public void testSetPath() {
		song.setPath("C:\\King.mp3");
		assertEquals("Path of the song is 'C:\\King.mp3'", "C:\\King.mp3", song.getPath());
		song.setPath("C:\\Users\\Jesper\\Music\\King.mp3");
		assertEquals("Path of the song is 'C:\\Users\\Jesper\\Music\\King.mp3'", "C:\\Users\\Jesper\\Music\\King.mp3", song.getPath());
	}
}
