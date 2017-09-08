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
	@Test(expected = IllegalArgumentException.class)
	public final void testParseFlacFile() {
		FlacFile f = new FlacFile();
		
		try {
			FlacParser.parseFlacFile(flacFile, f);
			
			assertEquals("Flac file title is 'Untitled Star'", "Untitled Star", f.getTitle());
			assertEquals("Flac file artist is 'Jimmy Penguin'", "Jimmy Pengiun", f.getArtist());
			assertEquals("Flac file album is 'The S27 Double Bluff'", "The S27 Double Bluff", f.getAlbum());
			
			assertEquals("flac file length in seconds is 'unknown'", "", ""); //Length for flac songs is not yet parsed.
			
			assertEquals("Flac file minimum block size is 4096", 4096, f.getMinimumBlockSize());
			assertEquals("Flac file maximum block size is 4096", 4096, f.getMaximumBlockSize());
			assertEquals("Flac file minimum frame size is 2330", 2330, f.getMinimumFrameSize());
			assertEquals("Flac file maximum frame size is 10805", 10805, f.getMaximumFrameSize());
			
			assertEquals("Flac file sample rate is 44100", 44100, f.getSampleRate());
			assertEquals("Flac file number of channels is 2", 2, f.getNumChannels());
			assertEquals("Flac file bits per sample is 16", 16, f.getBitsPerSample());
			assertEquals("Flac file number of samples is 3657265", 3657265, f.getNumSamples());
			
			//Force exception
			FlacParser.parseFlacFile(new File(""), f);
		} catch (IOException e) {
			fail(e.toString());
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacParser#isFlacFile(java.io.File)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testIsFlacFile() {
		try {
			assertEquals("The specified file is of the type 'flac'", true, FlacParser.isFlacFile(flacFile));
			assertEquals("The specified file is not the type 'flac'", false, FlacParser.isFlacFile(new File("./demo/Brad Sucks - Total Breakdown.mp3")));
			
			//Force Exception
			assertEquals("When the file does not exist an exception is thrown", false, FlacParser.isFlacFile(new File("")));
		} catch (IOException e) {
			fail(e.toString());
		}
	}

}
