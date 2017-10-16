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

package me.oskarmendel.player.search;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import me.oskarmendel.song.Song;

/**
 * Testing the SearchHandler class.
 * 
 * @author Jesper Bergstr�m
 * @version 0.00.00
 * @name SearchHandlerTest.java
 */
public class SearchHandlerTest {

	SearchHandler sh;

	@Before
	public void setUp() throws Exception {
		sh = SearchHandler.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link me.oskarmendel.player.search.SearchHandler#search()}.
	 */
	@Test
	public final void testSearch() {

		//List<Song> songList = new ArrayList<Song>();

		//songList.addAll(sh.search("brad", "./demo"));

		//assertEquals("'brad' was searched for", "Total Breakdown", songList.get(0).getTitle());
		//assertEquals("first object is a 'LocalSong'", "class me.oskarmendel.entities.LocalSong",
		//		songList.get(0).getClass().toString());
		//assertEquals("second object is a 'YouTubeSong'", "class me.oskarmendel.entities.YouTubeSong",
				//songList.get(1).getClass().toString());
	}

}
