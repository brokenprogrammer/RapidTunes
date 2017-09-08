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

package me.oskarmendel.util.song;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import me.oskarmendel.entities.Song;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FileStripperTest.java
 */
public class FileStripperTest {
	
	FileStripper fileStripper;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		fileStripper = new FileStripper();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.FileStripper#stripMp3Song(java.io.File)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testStripMp3Song() {
		File fileeBrad = new File("./demo/Brad Sucks - Total Breakdown.mp3");
		Song songBrad = fileStripper.stripMp3Song(fileeBrad);
		
		assertEquals("Title of the song is 'Total Breakdown'", "Total Breakdown", songBrad.getTitle());
		assertEquals("Artist of the song is 'Brad Sucks'", "Brad Sucks", songBrad.getArtist());
		assertEquals("Album of the song is 'Out of It'", "Out of It", songBrad.getAlbum());
		assertEquals("Length of the song in seconds is '139'", "139", songBrad.getLength());
		
		//Force exception
		fileStripper.stripMp3Song(new File(""));
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.FileStripper#stripFlacSong(java.io.File)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testStripFlacSong() {
		File fileeJimmy = new File("./demo/Jimmy Pengiun - Untitled Star.flac");
		Song songJimmy = fileStripper.stripFlacSong(fileeJimmy);
		
		assertEquals("Title of the song is 'Untitled Star'", "Untitled Star", songJimmy.getTitle());
		assertEquals("Artist of the song is 'Jimmy Penguin'", "Jimmy Pengiun", songJimmy.getArtist());
		assertEquals("Album of the song is 'The S27 Double Bluff'", "The S27 Double Bluff", songJimmy.getAlbum());
		assertEquals("Length of the song in seconds is 'unknown'", "", songJimmy.getLength()); //Length for flac songs is not yet parsed.
		
		//Force exception
		fileStripper.stripFlacSong(new File(""));
	}
}
