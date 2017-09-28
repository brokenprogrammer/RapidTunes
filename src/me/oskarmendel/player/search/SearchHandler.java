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

import com.google.api.services.youtube.model.Video;

import me.oskarmendel.entities.Song;
import me.oskarmendel.entities.YouTubeSong;
import me.oskarmendel.player.search.local.LocalSearch;

/**
 * Class that handles all searches. Uses a Singleton.
 * 
 * @author Jesper Bergström
 * @version 0.00.00
 * @name SearchHandler.java
 */
public class SearchHandler {

	private static SearchHandler INSTANCE = null;

	private SearchHandler() {

	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static SearchHandler getInstance() {

		if (INSTANCE == null)
			INSTANCE = new SearchHandler();

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

		songList.addAll(searchLocal(searchWord, path));
		songList.addAll(searchYouTube(searchWord));

		return songList;
	}

	/**
	 * Search method that runs a youtube search and converts the Video objects
	 * to YouTubeSong objects
	 * 
	 * @param searchWord
	 * @return List of songs
	 */
	private List<Song> searchYouTube(String searchWord) {

		List<Song> songList = new ArrayList<Song>();

		YouTubeSearch ys = new YouTubeSearch();

		List<Video> ytList = ys.search(searchWord); // List of videos from yt

		// Convert all videos to YouTubeSong and add them to songList
		for (int i = 0; i < ytList.size(); i++) {

			YouTubeSong s = new YouTubeSong();

			s.setTitle(ytList.get(i).getSnippet().getTitle());
			s.setArtist(ytList.get(i).getSnippet().getChannelTitle());
			s.setLength(ytList.get(i).getContentDetails().getDuration());
			s.setPath(ytList.get(i).getId());
			songList.add(s);
		}

		return songList;
	}

	/**
	 * Search method that runs a local search.
	 * 
	 * @param searchWord
	 * @param path
	 * @return List of songs
	 */
	private List<Song> searchLocal(String searchWord, String path) {

		LocalSearch ls = new LocalSearch();

		List<Song> localList = ls.search(searchWord, path); // List of songs
															// from local

		return localList;
	}
}
