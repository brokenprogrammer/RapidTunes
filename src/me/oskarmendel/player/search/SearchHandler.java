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

import java.util.ArrayList;
import java.util.List;

import me.oskarmendel.player.search.local.LocalSearchStrategy;
import me.oskarmendel.song.Song;

/**
 * Class that handles all searches. Uses a Singleton.
 * 
 * @author Jesper Bergström
 * @version 0.00.00
 * @name SearchHandler.java
 */
public class SearchHandler {

	private static SearchHandler INSTANCE = null;
	
	private LocalSearchStrategy localSearchStrategy;
	private YouTubeSearchStrategy youtubeSearchStrategy;
	
	private SearchHandler() {
		this.localSearchStrategy = new LocalSearchStrategy();
		this.youtubeSearchStrategy = new YouTubeSearchStrategy();
	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static SearchHandler getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new SearchHandler();
		}

		return INSTANCE;
	}

	/**
	 * Method that calls all search methods and adds them to one list of songs.
	 * 
	 * @param searchWord
	 * @return List of songs
	 */
	public List<Song> search(String searchWord, String path) {

		List<Song> songList = new ArrayList<Song>();

		// TODO: Check if source is checked in settings, if true perform search
		// from source.
		
		if (true) {
			localSearchStrategy.setPath(path);
			songList.addAll(localSearchStrategy.search(searchWord));
		}
		
		if (true) {
			songList.addAll(youtubeSearchStrategy.search(searchWord));
		}
		
		return songList;
	}
}
