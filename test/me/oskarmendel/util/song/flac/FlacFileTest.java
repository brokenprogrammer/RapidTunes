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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FlacFileTest.java
 */
public class FlacFileTest {
	FlacFile flacFile;
	FlacFile f;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		flacFile = new FlacFile("./demo/Jimmy Pengiun - Untitled Star.flac");
		f = new FlacFile();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#getMinimumBlockSize()}.
	 */
	@Test
	public final void testGetMinimumBlockSize() {
		assertEquals("Flac file minimum block size is 4096", 4096, flacFile.getMinimumBlockSize());
		flacFile.setMinimumBlockSize(1337);
		assertEquals("Flac file minimum block size is 1337", 1337, flacFile.getMinimumBlockSize());
		
		f.setMinimumBlockSize(420);
		assertEquals("Flac file minimum block size is 420", 420, f.getMinimumBlockSize());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#setMinimumBlockSize(int)}.
	 */
	@Test
	public final void testSetMinimumBlockSize() {
		assertEquals("Flac file minimum block size is 4096", 4096, flacFile.getMinimumBlockSize());
		flacFile.setMinimumBlockSize(1337);
		assertEquals("Flac file minimum block size is 1337", 1337, flacFile.getMinimumBlockSize());
		
		f.setMinimumBlockSize(420);
		assertEquals("Flac file minimum block size is 420", 420, f.getMinimumBlockSize());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#getMaximumBlockSize()}.
	 */
	@Test
	public final void testGetMaximumBlockSize() {
		assertEquals("Flac file maximum block size is 4096", 4096, flacFile.getMaximumBlockSize());
		flacFile.setMaximumBlockSize(1337);
		assertEquals("Flac file maximum block size is 1337", 1337, flacFile.getMaximumBlockSize());
		
		f.setMaximumBlockSize(420);
		assertEquals("Flac file maximum block size is 420", 420, f.getMaximumBlockSize());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#setMaximumBlockSize(int)}.
	 */
	@Test
	public final void testSetMaximumBlockSize() {
		assertEquals("Flac file maximum block size is 4096", 4096, flacFile.getMaximumBlockSize());
		flacFile.setMaximumBlockSize(1337);
		assertEquals("Flac file maximum block size is 1337", 1337, flacFile.getMaximumBlockSize());
		
		f.setMaximumBlockSize(420);
		assertEquals("Flac file maximum block size is 420", 420, f.getMaximumBlockSize());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#getMinimumFrameSize()}.
	 */
	@Test
	public final void testGetMinimumFrameSize() {
		assertEquals("Flac file minimum frame size is 2330", 2330, flacFile.getMinimumFrameSize());
		flacFile.setMinimumFrameSize(1337);
		assertEquals("Flac file minimum frame size is 1337", 1337, flacFile.getMinimumFrameSize());
		
		f.setMinimumFrameSize(420);
		assertEquals("Flac file minimum frame size is 420", 420, f.getMinimumFrameSize());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#setMinimumFrameSize(int)}.
	 */
	@Test
	public final void testSetMinimumFrameSize() {
		assertEquals("Flac file minimum frame size is 2330", 2330, flacFile.getMinimumFrameSize());
		flacFile.setMinimumFrameSize(1337);
		assertEquals("Flac file minimum frame size is 1337", 1337, flacFile.getMinimumFrameSize());
		
		f.setMinimumFrameSize(420);
		assertEquals("Flac file minimum frame size is 420", 420, f.getMinimumFrameSize());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#getMaximumFrameSize()}.
	 */
	@Test
	public final void testGetMaximumFrameSize() {
		assertEquals("Flac file maximum frame size is 10805", 10805, flacFile.getMaximumFrameSize());
		flacFile.setMaximumFrameSize(1337);
		assertEquals("Flac file maximum frame size is 1337", 1337, flacFile.getMaximumFrameSize());
		
		f.setMaximumFrameSize(420);
		assertEquals("Flac file maximum frame size is 420", 420, f.getMaximumFrameSize());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#setMaximumFrameSize(int)}.
	 */
	@Test
	public final void testSetMaximumFrameSize() {
		assertEquals("Flac file maximum frame size is 10805", 10805, flacFile.getMaximumFrameSize());
		flacFile.setMaximumFrameSize(1337);
		assertEquals("Flac file maximum frame size is 1337", 1337, flacFile.getMaximumFrameSize());
		
		f.setMaximumFrameSize(420);
		assertEquals("Flac file maximum frame size is 420", 420, f.getMaximumFrameSize());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#getSampleRate()}.
	 */
	@Test
	public final void testGetSampleRate() {
		assertEquals("Flac file sample rate is 44100", 44100, flacFile.getSampleRate());
		flacFile.setSampleRate(1337);
		assertEquals("Flac file sample rate is 1337", 1337, flacFile.getSampleRate());
		
		f.setSampleRate(420);
		assertEquals("Flac file sample rate is 420", 420, f.getSampleRate());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#setSampleRate(int)}.
	 */
	@Test
	public final void testSetSampleRate() {
		assertEquals("Flac file sample rate is 44100", 44100, flacFile.getSampleRate());
		flacFile.setSampleRate(1337);
		assertEquals("Flac file sample rate is 1337", 1337, flacFile.getSampleRate());
		
		f.setSampleRate(420);
		assertEquals("Flac file sample rate is 420", 420, f.getSampleRate());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#getNumChannels()}.
	 */
	@Test
	public final void testGetNumChannels() {
		assertEquals("Flac file number of channels is 2", 2, flacFile.getNumChannels());
		flacFile.setNumChannels(1337);
		assertEquals("Flac file number of channels is 1337", 1337, flacFile.getNumChannels());
		
		f.setNumChannels(420);
		assertEquals("Flac file number of channels is 420", 420, f.getNumChannels());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#setNumChannels(int)}.
	 */
	@Test
	public final void testSetNumChannels() {
		assertEquals("Flac file number of channels is 2", 2, flacFile.getNumChannels());
		flacFile.setNumChannels(1337);
		assertEquals("Flac file number of channels is 1337", 1337, flacFile.getNumChannels());
		
		f.setNumChannels(420);
		assertEquals("Flac file number of channels is 420", 420, f.getNumChannels());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#getBisPerSample()}.
	 */
	@Test
	public final void testGetBisPerSample() {
		assertEquals("Flac file bits per sample is 16", 16, flacFile.getBitsPerSample());
		flacFile.setBitsPerSample(1337);
		assertEquals("Flac file bits per sample is 1337", 1337, flacFile.getBitsPerSample());
		
		f.setBitsPerSample(420);
		assertEquals("Flac file bits per sample is 420", 420, f.getBitsPerSample());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#setBisPerSample(int)}.
	 */
	@Test
	public final void testSetBisPerSample() {
		assertEquals("Flac file bits per sample is 16", 16, flacFile.getBitsPerSample());
		flacFile.setBitsPerSample(1337);
		assertEquals("Flac file bits per sample is 1337", 1337, flacFile.getBitsPerSample());
		
		f.setBitsPerSample(420);
		assertEquals("Flac file bits per sample is 420", 420, f.getBitsPerSample());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#getNumSamples()}.
	 */
	@Test
	public final void testGetNumSamples() {
		assertEquals("Flac file number of samples is 3657265", 3657265, flacFile.getNumSamples());
		flacFile.setNumSamples(1337);
		assertEquals("Flac file number of samples is 1337", 1337, flacFile.getNumSamples());
		
		f.setNumSamples(420);
		assertEquals("Flac file number of samples is 420", 420, f.getNumSamples());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#setNumSamples(int)}.
	 */
	@Test
	public final void testSetNumSamples() {
		assertEquals("Flac file number of samples is 3657265", 3657265, flacFile.getNumSamples());
		flacFile.setNumSamples(1337);
		assertEquals("Flac file number of samples is 1337", 1337, flacFile.getNumSamples());
		
		f.setNumSamples(420);
		assertEquals("Flac file number of samples is 420", 420, f.getNumSamples());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#getVendor()}.
	 */
	@Test
	public final void testGetVendor() {
		assertEquals("Flac file vendor is reference libFLAC 1.2.1 20070917", "reference libFLAC 1.2.1 20070917", flacFile.getVendor());
		flacFile.setVendor("Oskar Mendel");
		assertEquals("Flac file vendor is 1337", "Oskar Mendel", flacFile.getVendor());
		
		f.setVendor("I am tired, it is 6 AM");
		assertEquals("Flac file vendor is a message about how tired i am", "I am tired, it is 6 AM", f.getVendor());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#setVendor(java.lang.String)}.
	 */
	@Test
	public final void testSetVendor() {
		assertEquals("Flac file vendor is reference libFLAC 1.2.1 20070917", "reference libFLAC 1.2.1 20070917", flacFile.getVendor());
		flacFile.setVendor("Oskar Mendel");
		assertEquals("Flac file vendor is 1337", "Oskar Mendel", flacFile.getVendor());
		
		f.setVendor("I am tired, it is 6 AM");
		assertEquals("Flac file vendor is a message about how tired i am", "I am tired, it is 6 AM", f.getVendor());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#getArtist()}.
	 */
	@Test
	public final void testGetArtist() {
		assertEquals("Flac file artist is 'Jimmy Penguin'", "Jimmy Pengiun", flacFile.getArtist());
		flacFile.setArtist("Oskar Mendel");
		assertEquals("Flac file artist is 'Oskar Mendel'", "Oskar Mendel", flacFile.getArtist());
		
		f.setArtist("Oskar");
		assertEquals("Flac file artist is 'Oskar'", "Oskar", f.getArtist());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#setArtist(java.lang.String)}.
	 */
	@Test
	public final void testSetArtist() {
		assertEquals("Flac file artist is 'Jimmy Penguin'", "Jimmy Pengiun", flacFile.getArtist());
		flacFile.setArtist("Oskar Mendel");
		assertEquals("Flac file artist is 'Oskar Mendel'", "Oskar Mendel", flacFile.getArtist());
		
		f.setArtist("Oskar");
		assertEquals("Flac file artist is 'Oskar'", "Oskar", f.getArtist());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#getAlbum()}.
	 */
	@Test
	public final void testGetAlbum() {
		assertEquals("Flac file album is 'The S27 Double Bluff'", "The S27 Double Bluff", flacFile.getAlbum());
		flacFile.setAlbum("Oskar Mendel");
		assertEquals("Flac file album is 'Oskar Mendel'", "Oskar Mendel", flacFile.getAlbum());
		
		f.setAlbum("Oskar");
		assertEquals("Flac file album is 'Oskar'", "Oskar", f.getAlbum());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#setAlbum(java.lang.String)}.
	 */
	@Test
	public final void testSetAlbum() {
		assertEquals("Flac file album is 'The S27 Double Bluff'", "The S27 Double Bluff", flacFile.getAlbum());
		flacFile.setAlbum("Oskar Mendel");
		assertEquals("Flac file album is 'Oskar Mendel'", "Oskar Mendel", flacFile.getAlbum());
		
		f.setAlbum("Oskar");
		assertEquals("Flac file album is 'Oskar'", "Oskar", f.getAlbum());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#getTitle()}.
	 */
	@Test
	public final void testGetTitle() {
		assertEquals("Flac file title is 'Untitled Star'", "Untitled Star", flacFile.getTitle());
		flacFile.setTitle("Oskar Mendel");
		assertEquals("Flac file title is 'Oskar Mendel'", "Oskar Mendel", flacFile.getTitle());
		
		f.setTitle("Oskar");
		assertEquals("Flac file title is 'Oskar'", "Oskar", f.getTitle());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#setTitle(java.lang.String)}.
	 */
	@Test
	public final void testSetTitle() {
		assertEquals("Flac file title is 'Untitled Star'", "Untitled Star", flacFile.getTitle());
		flacFile.setTitle("Oskar Mendel");
		assertEquals("Flac file title is 'Oskar Mendel'", "Oskar Mendel", flacFile.getTitle());
		
		f.setTitle("Oskar");
		assertEquals("Flac file title is 'Oskar'", "Oskar", f.getTitle());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#getGenre()}.
	 */
	@Test
	public final void testGetGenre() {
		assertEquals("Flac file genre is not set", null, flacFile.getGenre());
		flacFile.setGenre("Oskar Mendel");
		assertEquals("Flac file genre is 'Oskar Mendel'", "Oskar Mendel", flacFile.getGenre());
		
		f.setGenre("Oskar");
		assertEquals("Flac file genre is 'Oskar'", "Oskar", f.getGenre());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#setGenre(java.lang.String)}.
	 */
	@Test
	public final void testSetGenre() {
		assertEquals("Flac file genre is not set", null, flacFile.getGenre());
		flacFile.setGenre("Oskar Mendel");
		assertEquals("Flac file genre is 'Oskar Mendel'", "Oskar Mendel", flacFile.getGenre());
		
		f.setGenre("Oskar");
		assertEquals("Flac file genre is 'Oskar'", "Oskar", f.getGenre());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#getTrackNumber()}.
	 */
	@Test
	public final void testGetTrackNumber() {
		assertEquals("Flac file track number is 5", "5", flacFile.getTrackNumber());
		flacFile.setTrackNumber("105");
		assertEquals("Flac file track number is 105", "105", flacFile.getTrackNumber());
		
		f.setTrackNumber("-120");
		assertEquals("Flac file genre is -120", "-120", f.getTrackNumber());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#setTrackNumber(java.lang.String)}.
	 */
	@Test
	public final void testSetTrackNumber() {
		assertEquals("Flac file track number is 5", "5", flacFile.getTrackNumber());
		flacFile.setTrackNumber("105");
		assertEquals("Flac file track number is 105", "105", flacFile.getTrackNumber());
		
		f.setTrackNumber("-120");
		assertEquals("Flac file genre is -120", "-120", f.getTrackNumber());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#getDate()}.
	 */
	@Test
	public final void testGetDate() {
		assertEquals("Flac file date is not set", null, flacFile.getDate());
		flacFile.setDate("2017-03-11");
		assertEquals("Flac file date is 2017-03-11", "2017-03-11", flacFile.getDate());
		
		f.setDate("Best Day Ever!");
		assertEquals("Flac file date is Best Day Ever!", "Best Day Ever!", f.getDate());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.song.flac.FlacFile#setDate(java.lang.String)}.
	 */
	@Test
	public final void testSetDate() {
		assertEquals("Flac file date is not set", null, flacFile.getDate());
		flacFile.setDate("2017-03-11");
		assertEquals("Flac file date is 2017-03-11", "2017-03-11", flacFile.getDate());
		
		f.setDate("Best Day Ever!");
		assertEquals("Flac file date is Best Day Ever!", "Best Day Ever!", f.getDate());
	}

}
