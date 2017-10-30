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

import me.oskarmendel.song.LocalSong;
import me.oskarmendel.song.Song;


/**
 * 
 * @author Jesper Bergstr�m
 * @version 0.00.00
 * @name LocalSongTest.java
 */
public class LocalSongTest {
	
	Song song;
	
	@Before
	public void setUp() throws Exception {
		song = new LocalSong();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link me.oskarmendel.song.LocalSong#getTitle()}.
	 */
	@Test
	public void testGetTitle() {
		song.setTitle("The Middle");
		assertEquals("Title of the song is 'The Middle'", "The Middle", song.getTitle());
		song.setTitle("Sweetness");
		assertEquals("Title of the song is 'Sweetness'", "Sweetness", song.getTitle());
	}

	/**
	 * Test method for {@link me.oskarmendel.song.LocalSong#setTitle()}.
	 */
	@Test
	public void testSetTitle() {
		song.setTitle("The Middle");
		assertEquals("Title of the song is 'The Middle'", "The Middle", song.getTitle());
		song.setTitle("Sweetness");
		assertEquals("Title of the song is 'Sweetness'", "Sweetness", song.getTitle());
	}

	/**
	 * Test method for {@link me.oskarmendel.song.LocalSong#getArtist()}.
	 */
	@Test
	public void testGetArtist() {
		song.setArtist("Jimmy Eat World");
		assertEquals("Artist of the song is 'Jimmy Eat World'", "Jimmy Eat World", song.getArtist());
		song.setArtist("Nirvana");
		assertEquals("Artist of the song is 'Nirvana'", "Nirvana", song.getArtist());
	}

	/**
	 * Test method for {@link me.oskarmendel.song.LocalSong#setArtist()}.
	 */
	@Test
	public void testSetArtist() {
		song.setArtist("Jimmy Eat World");
		assertEquals("Artist of the song is 'Jimmy Eat World'", "Jimmy Eat World", song.getArtist());
		song.setArtist("Nirvana");
		assertEquals("Artist of the song is 'Nirvana'", "Nirvana", song.getArtist());
	}

	/**
	 * Test method for {@link me.oskarmendel.song.LocalSong#getAlbum()}.
	 */
	@Test
	public void testGetAlbum() {
		song.setAlbum("Bleed American");
		assertEquals("Album of the song is 'Bleed American'", "Bleed American", song.getAlbum());
		song.setAlbum("Futures");
		assertEquals("Album of the song is 'Futures'", "Futures", song.getAlbum());
	}

	/**
	 * Test method for {@link me.oskarmendel.song.LocalSong#setAlbum()}.
	 */
	@Test
	public void testSetAlbum() {
		song.setAlbum("Bleed American");
		assertEquals("Album of the song is 'Bleed American'", "Bleed American", song.getAlbum());
		song.setAlbum("Futures");
		assertEquals("Album of the song is 'Futures'", "Futures", song.getAlbum());
	}

	/**
	 * Test method for {@link me.oskarmendel.song.LocalSong#getLength()}.
	 */
	@Test
	public void testGetLength() {
		song.setLength(120);
		assertEquals("Length of the song is '120'", 120, song.getLength());
		song.setLength(1000);
		assertEquals("Length of the song is '1000'", 1000, song.getLength());
	}

	/**
	 * Test method for {@link me.oskarmendel.song.LocalSong#setLength()}.
	 */
	@Test
	public void testSetLength() {
		song.setLength(120);
		assertEquals("Length of the song is '120'", 120, song.getLength());
		song.setLength(1000);
		assertEquals("Length of the song is '1000'", 1000, song.getLength());
	}
	
	/**
	 * Test method for {@link me.oskarmendel.song.LocalSong#getPath()}.
	 */
	@Test
	public void testGetPath() {
		song.setPath("C:\\King.mp3");
		assertEquals("Path of the song is 'C:\\King.mp3'", "C:\\King.mp3", song.getPath());
		song.setPath("C:\\Users\\Jesper\\Music\\King.mp3");
		assertEquals("Path of the song is 'C:\\Users\\Jesper\\Music\\King.mp3'", "C:\\Users\\Jesper\\Music\\King.mp3", song.getPath());
	}
	
	/**
	 * Test method for {@link me.oskarmendel.song.LocalSong#setPath()}.
	 */
	@Test
	public void testSetPath() {
		song.setPath("C:\\King.mp3");
		assertEquals("Path of the song is 'C:\\King.mp3'", "C:\\King.mp3", song.getPath());
		song.setPath("C:\\Users\\Jesper\\Music\\King.mp3");
		assertEquals("Path of the song is 'C:\\Users\\Jesper\\Music\\King.mp3'", "C:\\Users\\Jesper\\Music\\King.mp3", song.getPath());
	}
	
	/**
	 * Test method for {@link me.oskarmendel.song.LocalSong#equals()}.
	 */
	@Test
	public void testEquals_Reflexive() {
		// Testing for the reflexive relation.
		LocalSong reflexiveSong1 = createDummySong("SongX", 999);
		LocalSong reflexiveSong2 = createDummySong("SongX", 999);
		LocalSong reflexiveSong3 = createDummySong("SongY", 123);
		Song reflexiveYouTubeSong = createDummyYouTubeSong("SongX", 999);
		
		assertEquals("reflexiveSong1 is equal to reflexiveSong1 according to the reflexive property.", reflexiveSong1.equals(reflexiveSong1), true);
		assertEquals("reflexiveSong1 is equal to reflexiveSong2 according to the reflexive property.", reflexiveSong1.equals(reflexiveSong2), true);
		
		// Testing reflexive hashcodes.
		assertEquals("Hashcode of reflexiveSong1 is equal to itself.", reflexiveSong1.hashCode() == reflexiveSong1.hashCode(), true);
		assertEquals("Hashcode of reflexiveSong1 and reflexiveSong2 is equal.", reflexiveSong1.hashCode() == reflexiveSong2.hashCode(), true);
		assertEquals("Hashcode of reflexiveSong1 and reflexiveSong3 is not equal.", reflexiveSong1.hashCode() == reflexiveSong3.hashCode(), false);
		
		// Testing that songs is never equal to null.
		assertEquals("reflexiveSong1 is never equals to null.", reflexiveSong1.equals(null), false);
		assertEquals("reflexiveSong2 is never equals to null.", reflexiveSong2.equals(null), false);
		
		assertEquals("reflexiveSong1 is not equal to reflexiveSong3.", reflexiveSong1.equals(reflexiveSong3), false);
		assertEquals("reflexiveSong2 is not equal to reflexiveSong3.", reflexiveSong2.equals(reflexiveSong3), false);
		
		assertEquals("reflexiveSong1 cannot be equal to a YouTubeSong.", reflexiveSong1.equals(reflexiveYouTubeSong), false);
		assertEquals("reflexiveSong2 cannot be equal to a YouTubeSong.", reflexiveSong2.equals(reflexiveYouTubeSong), false);
		assertEquals("reflexiveSong3 cannot be equal to a YouTubeSong.", reflexiveSong3.equals(reflexiveYouTubeSong), false);
	}
	
	/**
	 * Test method for {@link me.oskarmendel.song.LocalSong#equals()}.
	 */
	@Test
	public void testEquals_Symmetric() {
		// Testing for symmetric relation.
		LocalSong symmetricSong1 = createDummySong("SongX", "ArtistX", "AlbumX", 12345, "C:/some/dir", "LocalIcon");
		LocalSong symmetricSong2 = createDummySong("SongX", "ArtistX", "AlbumX", 12345, "C:/some/dir", "LocalIcon");
		LocalSong symmetricSong3 = createDummySong("SongY", "ArtistY", "AlbumY", 54321, "C:/some/other/dir", "FunnyIcon");
		LocalSong symmetricSong4 = createDummySong("SongY", "ArtistY", "AlbumY", 54321, "C:/some/other/dir", "FunnyIcon");
		LocalSong symmetricSong5 = createDummySong("SongZ", "ArtistZ", "AlbumZ", 10101, "C:/some/completely/other/dir", "RandomIcon");
		
		assertEquals("symmetricSong1 is equal to symmetricSong2.", symmetricSong1.equals(symmetricSong2), true);
		assertEquals("symmetricSong2 is equal to symmetricSong1 according to the symmetric property.", symmetricSong2.equals(symmetricSong1), true);
		assertEquals("symmetricSong3 is equal to symmetricSong4.", symmetricSong3.equals(symmetricSong4), true);
		assertEquals("symmetricSong4 is equal to symmetricSong3 according to the symmetric property.", symmetricSong4.equals(symmetricSong3), true);
		
		assertEquals("symmetricSong1 is not equal to symmetricSong5.", symmetricSong1.equals(symmetricSong5), false);
		assertEquals("symmetricSong2 is not equal to symmetricSong5.", symmetricSong2.equals(symmetricSong5), false);
		assertEquals("symmetricSong3 is not equal to symmetricSong5.", symmetricSong3.equals(symmetricSong5), false);
		assertEquals("symmetricSong4 is not equal to symmetricSong5.", symmetricSong4.equals(symmetricSong5), false);
		
		// Testing symmetric hashcodes.
		assertEquals("Hashcode of symmetricSong1 is equal to itself.", symmetricSong1.hashCode() == symmetricSong1.hashCode(), true);
		assertEquals("Hashcode of symmetricSong1 is equal to symmetricSong2.", symmetricSong1.hashCode() == symmetricSong2.hashCode(), true);
		assertEquals("Hashcode of symmetricSong1 is equal to itself.", symmetricSong3.hashCode() == symmetricSong3.hashCode(), true);
		assertEquals("Hashcode of symmetricSong3 is equal to symmetricSong4.", symmetricSong3.hashCode() == symmetricSong4.hashCode(), true);
		
		assertEquals("Hashcode of symmetricSong1 is not equal to hashcode of symmetricSong5.", symmetricSong1.hashCode() == symmetricSong5.hashCode(), false);
		assertEquals("Hashcode of symmetricSong2 is not equal to hashcode of symmetricSong5.", symmetricSong2.hashCode() == symmetricSong5.hashCode(), false);
		assertEquals("Hashcode of symmetricSong3 is not equal to hashcode of symmetricSong5.", symmetricSong3.hashCode() == symmetricSong5.hashCode(), false);
		assertEquals("Hashcode of symmetricSong4 is not equal to hashcode of symmetricSong5.", symmetricSong4.hashCode() == symmetricSong5.hashCode(), false);
		
		// Testing that songs is never equal to null.
		assertEquals("symmetricSong1 is never equal to null", symmetricSong1.equals(null), false);
		assertEquals("symmetricSong2 is never equal to null", symmetricSong2.equals(null), false);
		assertEquals("symmetricSong3 is never equal to null", symmetricSong3.equals(null), false);
		assertEquals("symmetricSong4 is never equal to null", symmetricSong4.equals(null), false);
		assertEquals("symmetricSong5 is never equal to null", symmetricSong5.equals(null), false);
	}
	
	/**
	 * Test method for {@link me.oskarmendel.song.LocalSong#equals()}.
	 */
	@Test
	public void testEquals_Transitive() {
		// Testing for the transitive relation.
		LocalSong transitiveSong1 = createDummySong("SongX", "ArtistX", "AlbumX", 12345, "C:/some/dir", "LocalIcon");
		LocalSong transitiveSong2 = createDummySong("SongX", "ArtistX", "AlbumX", 12345, "C:/some/dir", "LocalIcon");
		LocalSong transitiveSong3 = createDummySong("SongX", "ArtistX", "AlbumX", 12345, "C:/some/dir", "LocalIcon");
		LocalSong transitiveSong4 = createDummySong("SongY", "ArtistY", "AlbumY", 54321, "C:/some/other/dir", "FunnyIcon");
		
		assertEquals("transitiveSong1 is equal to itself.", transitiveSong1.equals(transitiveSong1), true);
		assertEquals("transitiveSong1 is equal to transitiveSong2.", transitiveSong1.equals(transitiveSong2), true);
		assertEquals("transitiveSong2 is equal to transitiveSong3.", transitiveSong2.equals(transitiveSong3), true);
		assertEquals("transitiveSong1 is equal to transitiveSong3 according to the transitive property.", transitiveSong1.equals(transitiveSong3), true);
		assertEquals("transitiveSong1 is not equal to transitiveSong4.", transitiveSong1.equals(transitiveSong4), false);
		assertEquals("transitiveSong2 is not equal to transitiveSong4.", transitiveSong2.equals(transitiveSong4), false);
		assertEquals("transitiveSong3 is not equal to transitiveSong4.", transitiveSong3.equals(transitiveSong4), false);
		
		//Testing transitive hashcodes.
		assertEquals("Hashcode of transitiveSong1 is equal to itself.", transitiveSong1.hashCode() == transitiveSong1.hashCode(), true);
		assertEquals("Hashcode of transitiveSong1 is equal to transitiveSong2.", transitiveSong1.hashCode() == transitiveSong2.hashCode(), true);
		assertEquals("Hashcode of transitiveSong2 is equal to transitiveSong3.", transitiveSong2.hashCode() == transitiveSong3.hashCode(), true);
		assertEquals("Hashcode of transitiveSong1 is equal to transitiveSong3.", transitiveSong1.hashCode() == transitiveSong3.hashCode(), true);
		
		assertEquals("Hashcode of transitiveSong1 is not equal to transitiveSong4.", transitiveSong1.hashCode() == transitiveSong4.hashCode(), false);
		assertEquals("Hashcode of transitiveSong2 is not equal to transitiveSong4.", transitiveSong2.hashCode() == transitiveSong4.hashCode(), false);
		assertEquals("Hashcode of transitiveSong3 is not equal to transitiveSong4.", transitiveSong3.hashCode() == transitiveSong4.hashCode(), false);

		// Testing that songs is never equal to null.
		assertEquals("transitiveSong1 is never equals to null.", transitiveSong1.equals(null), false);
		assertEquals("transitiveSong2 is never equals to null.", transitiveSong2.equals(null), false);
		assertEquals("transitiveSong3 is never equals to null.", transitiveSong3.equals(null), false);
		assertEquals("transitiveSong4 is never equals to null.", transitiveSong4.equals(null), false);
	}
	
	/**
	 * Helper testing method that creates a dummy LocalSong with the 
	 * specified Title and length.
	 * 
	 * @param title - Title of the LocalSong to create.
	 * @param length - Length of the LocalSong to create.
	 * 
	 * @return - LocalSong object created with specified values.
	 */
	private LocalSong createDummySong(String title, long length) {
		LocalSong song = new LocalSong();
		
		song.setTitle(title);
		song.setLength(length);
		
		return song;
	}
	
	/**
	 * Helper testing method that creates a dummy LocalSong with the
	 * specified Title, Artist, Album, Length, Path and Graphic.
	 * 
	 * @param title - Title of the LocalSong to create.
	 * @param artist - Artist of the LocalSong to create.
	 * @param album - Album of the LocalSong to create.
	 * @param length - Length of the LocalSong to create.
	 * @param path - Path of the LocalSong to create.
	 * @param graphic - Graphic of the LocalSong to create.
	 * 
	 * @return - LocalSong object created with the specified value.
	 */
	private LocalSong createDummySong(String title, String artist, String album, long length, String path, String graphic) {
		LocalSong song = new LocalSong();
		
		song.setTitle(title);
		song.setArtist(artist);
		song.setAlbum(album);
		song.setLength(length);
		song.setPath(path);
		song.setGraphic(graphic);
		
		song.setSongFormat(LocalSongFormat.MP3);
		song.setSampleRate(12345);
		song.setNumChannels(8);
		song.setBitsPerSample(54321);
		
		return song;
	}
	
	/**
	 * Helper testing method that creates a dummy YouTubeSong with the
	 * specified Title and Length returned as a Song object.
	 * 
	 * @param title - Title of the Song object.
	 * @param length - Length of the Song object.
	 * 
	 * @return - A YouTube song object with specified values returned as a Song object.
	 */
	private Song createDummyYouTubeSong(String title, long length) {
		YouTubeSong song = new YouTubeSong();
		
		song.setTitle(title);
		song.setLength(length);
		
		return song;
	}
}