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

package me.oskarmendel.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javafx.collections.ObservableList;
import me.oskarmendel.song.LocalSong;
import me.oskarmendel.song.Song;
import me.oskarmendel.song.YouTubeSong;

/**
 * Tests for the SongQueueModel.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SongQueueModelTest.java
 */
public class SongQueueModelTest {
	
	private SongQueueModel songQueueModel;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		songQueueModel = new SongQueueModel();
		songQueueModel.setSongQueue(generateRandomList());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test method for {@link me.oskarmendel.SongQueueModel#getSongQueue()}.
	 */
	@Test
	public final void testGetSongQueue() {
		ObservableList<Song> songList = songQueueModel.getSongQueue();
		
		assertEquals("SongList is not empty.", songList.size() > 0, true);
		assertEquals("SongList has 10 elements.", songList.size() == 10, true);
	}

	/**
	 * Test method for {@link me.oskarmendel.SongQueueModel#setSongQueue()}.
	 */
	@Test
	public final void testSetSongQueue() {
		ObservableList<Song> songList = songQueueModel.getSongQueue();
		
		assertEquals("SongList is not empty.", songList.size() > 0, true);
		assertEquals("SongList has 10 elements.", songList.size() == 10, true);
		
		ArrayList<Song> emptySongList = new ArrayList<Song>();
		songQueueModel.setSongQueue(emptySongList);
		
		assertEquals("SongList is not empty.", songList.size(), 0);
		assertEquals("SongList has 10 elements.", songList.isEmpty(), true);
	}
	
	/**
	 * Test method for {@link me.oskarmendel.SongQueueModel#addSongs()}.
	 */
	@Test
	public final void testAddSongs() {
		ObservableList<Song> songList = songQueueModel.getSongQueue();
		
		assertEquals("SongList is not empty.", songList.size() > 0, true);
		assertEquals("SongList has 10 elements.", songList.size() == 10, true);
		
		songQueueModel.addSongs(generateRandomList());
		
		songList = songQueueModel.getSongQueue();
		
		assertEquals("SongList is not empty.", songList.size() > 0, true);
		assertEquals("SongList is not empty.", songList.isEmpty(), false);
		assertEquals("SongList has 20 elements.", songList.size() == 20, true);
	}
	
	/**
	 * Test method for {@link me.oskarmendel.SongQueueModel#addSong()}.
	 */
	@Test
	public final void testAddSong() {
		ObservableList<Song> songList = songQueueModel.getSongQueue();
		
		assertEquals("SongList is not empty.", songList.size() > 0, true);
		assertEquals("SongList has 10 elements.", songList.size() == 10, true);
		
		songQueueModel.addSong(generateLocalSong("NewSong1"));
		assertEquals("SongList has 11 elements.", songList.size() == 11, true);
		
		songQueueModel.addSong(generateLocalSong("NewSong2"));
		assertEquals("SongList has 12 elements.", songList.size() == 12, true);
		
		songQueueModel.addSong(generateLocalSong("NewSong3"));
		assertEquals("SongList has 13 elements.", songList.size() == 13, true);
		
		songQueueModel.addSong(generateYouTubeSong("NewSong4"));
		assertEquals("SongList has 14 elements.", songList.size() == 14, true);
		
		songQueueModel.addSong(generateYouTubeSong("NewSong5"));
		assertEquals("SongList has 15 elements.", songList.size() == 15, true);
		
		songQueueModel.addSong(generateYouTubeSong("NewSong6"));
		assertEquals("SongList has 16 elements.", songList.size() == 16, true);
	}
	
	/**
	 * Test method for {@link me.oskarmendel.SongQueueModel#getNext()}.
	 */
	@Test
	public final void testGetNext() {
		Song prev;
		Song song = songQueueModel.getNext();
		
		assertEquals("The retrieved song was placed at the end of the list.", song, songQueueModel.getSongQueue().get(songQueueModel.getSongQueue().size()-1));
		prev = song;
		song = songQueueModel.getNext();
		
		assertEquals("The previous song is not equal to the recently retrieved song.", prev.equals(song), false);
		assertEquals("The previous song is not at the end of the list.", prev.equals(songQueueModel.getSongQueue().get(songQueueModel.getSongQueue().size()-1)), false);
		assertEquals("The previous song is second to last element in the queue.", prev.equals(songQueueModel.getSongQueue().get(songQueueModel.getSongQueue().size()-2)), true);
		
		assertEquals("The retrieved song was placed at the end of the list.", song, songQueueModel.getSongQueue().get(songQueueModel.getSongQueue().size()-1));
		prev = song;
		song = songQueueModel.getNext();
		
		assertEquals("The previous song is not equal to the recently retrieved song.", prev.equals(song), false);
		assertEquals("The previous song is not at the end of the list.", prev.equals(songQueueModel.getSongQueue().get(songQueueModel.getSongQueue().size()-1)), false);
		assertEquals("The previous song is second to last element in the queue.", prev.equals(songQueueModel.getSongQueue().get(songQueueModel.getSongQueue().size()-2)), true);
		
		assertEquals("The retrieved song was placed at the end of the list.", song, songQueueModel.getSongQueue().get(songQueueModel.getSongQueue().size()-1));
		prev = song;
		song = songQueueModel.getNext();
		
		assertEquals("The previous song is not equal to the recently retrieved song.", prev.equals(song), false);
		assertEquals("The previous song is not at the end of the list.", prev.equals(songQueueModel.getSongQueue().get(songQueueModel.getSongQueue().size()-1)), false);
		assertEquals("The previous song is second to last element in the queue.", prev.equals(songQueueModel.getSongQueue().get(songQueueModel.getSongQueue().size()-2)), true);
		
		assertEquals("The retrieved song was placed at the end of the list.", song, songQueueModel.getSongQueue().get(songQueueModel.getSongQueue().size()-1));
	}

	/**
	 * Test method for {@link me.oskarmendel.SongQueueModel#getPrevious()}.
	 */
	@Test
	public final void testGetPrevious() {
		Song prev;
		Song song = songQueueModel.getPrevious();
		
		assertEquals("The retrieved song was placed at the front of the list.", song, songQueueModel.getSongQueue().get(0));
		prev = song;
		song = songQueueModel.getPrevious();
		
		assertEquals("The previous song is not equal to the recently retrieved song.", prev.equals(song), false);
		assertEquals("The previous song is not at the front of the list.", prev.equals(songQueueModel.getSongQueue().get(0)), false);
		assertEquals("The previous song is at the second position of the list.", prev.equals(songQueueModel.getSongQueue().get(1)), true);
		
		assertEquals("The retrieved song was placed at the front of the list.", song, songQueueModel.getSongQueue().get(0));
		prev = song;
		song = songQueueModel.getPrevious();
		
		assertEquals("The previous song is not equal to the recently retrieved song.", prev.equals(song), false);
		assertEquals("The previous song is not at the front of the list.", prev.equals(songQueueModel.getSongQueue().get(0)), false);
		assertEquals("The previous song is at the second position of the list.", prev.equals(songQueueModel.getSongQueue().get(1)), true);
		
		assertEquals("The retrieved song was placed at the front of the list.", song, songQueueModel.getSongQueue().get(0));
		prev = song;
		song = songQueueModel.getPrevious();
		
		assertEquals("The previous song is not equal to the recently retrieved song.", prev.equals(song), false);
		assertEquals("The previous song is not at the front of the list.", prev.equals(songQueueModel.getSongQueue().get(0)), false);
		assertEquals("The previous song is at the second position of the list.", prev.equals(songQueueModel.getSongQueue().get(1)), true);
		
	}
	
	/**
	 * Generates a list of ten songs then shuffles the list to
	 * simulate a new list.
	 * 
	 * @return - List of ten songs in random order.
	 */
	private ArrayList<Song> generateRandomList() {
		ArrayList<Song> songList = new ArrayList<Song>();
		
		songList.add(generateLocalSong("Song1"));
		songList.add(generateLocalSong("Song2"));
		songList.add(generateLocalSong("Song3"));
		songList.add(generateLocalSong("Song4"));
		songList.add(generateLocalSong("Song5"));
		songList.add(generateYouTubeSong("Song6"));
		songList.add(generateYouTubeSong("Song7"));
		songList.add(generateYouTubeSong("Song8"));
		songList.add(generateYouTubeSong("Song9"));
		songList.add(generateYouTubeSong("Song10"));
		
		Collections.shuffle(songList);
		
		return songList;
	}
	
	/**
	 * Creates a LocalSong object with the specified Title.
	 * 
	 * @param title - Title for the LocalSong.
	 * 
	 * @return - LocalSong object with specified Title.
	 */
	private LocalSong generateLocalSong(String title) {
		LocalSong song = new LocalSong();
		song.setTitle(title);
		return song;
	}
	
	/**
	 * Creates a YouTubeSong object with the specified Title.
	 * 
	 * @param title - Title for the YouTubeSong.
	 * 
	 * @return - YouTubeSong object with specified Title.
	 */
	private YouTubeSong generateYouTubeSong(String title) {
		YouTubeSong song = new YouTubeSong();
		song.setTitle(title);
		
		return song;
	}
}
