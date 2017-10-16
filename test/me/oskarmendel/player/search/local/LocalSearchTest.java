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

package me.oskarmendel.player.search.local;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import me.oskarmendel.song.Song;

/**
 * 
 * @author Oskar
 * @version 0.00.00
 * @name LocalSearchTest.java
 */
public class LocalSearchTest {

	LocalSearch search;
	
	@Before
	public void setUp() throws Exception {
		search = new LocalSearch();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link me.oskarmendel.player.search.local.LocalSearch#LocalSearch()}.
	 */
	@Test
	public final void testLocalSearch() {
		//Add test later if parameters would be added to the constructor.
	}

	/**
	 * Test method for {@link me.oskarmendel.player.search.local.LocalSearch#search()}.
	 */
	@Test
	public final void testSearch() {
		List<Song> songList = new ArrayList<Song>();
		
		//Find the local demo song called "Brad Sucks - Total Breakdown" in the demo folder.
		songList = search.search("Brad Sucks", "./demo");
		assertEquals("The local song 'Brad Sucks' was searched for", "Total Breakdown", songList.get(0).getTitle());
		assertEquals("Only one song matched the search", 1, songList.size());
		
		//Find the local demo songs in the demo folder.
		//TODO: Should't have to clear list when making new search.
		songList.clear();
		songList = search.search("", "./demo"); //Searching for all songs in that directory.
		
		assertEquals("Two song matched the search", 2, songList.size());
		assertEquals("The second local song is 'Jimmy Pengium'", "Untitled Star", songList.get(1).getTitle());
	}

}
