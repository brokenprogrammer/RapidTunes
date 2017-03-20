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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.oskarmendel.entities.LocalSong;
import me.oskarmendel.entities.Song;

/**
 * Class for searching a local directory for sound files.
 * TODO: Add file strip functionality instead of setting title as file name. 
 * 			Make the file stripper check if tags of the file exist else set title as 
 * 				file name and strip extension.
 * 
 * @author Jesper Bergström
 * @version 0.00.00
 * @name LocalSearch.java
 */
public class LocalSearch {

	private ArrayList<Song> list = new ArrayList<Song>();
	private final String[] format = { ".mp3", ".flac", ".wav" };

	public LocalSearch() {

	}
	
	/**
	 * Method for searching all sound files in a directory.
	 * 
	 * @param keyword - Keyword to match a file for. Entire must match for a file to be selected.
	 * @param path - Path on the device to search through.
	 * @return list - Containing all the found songs. 
	 */
	public List<Song> search(String keyword, String path) {

		try {
			File directory = new File(path);

			// if file is directory
			if (directory.isDirectory()) {

				File[] filesList = directory.listFiles();

				for (int i = 0; i < filesList.length; i++) {
					search(keyword, filesList[i].getAbsolutePath());
				}
			}
			// if keyword matches file name
			else if (keywordMatch(keyword, directory)) {

				// check if format is valid for all formats
				for (int i = 0; i < format.length; i++) {

					if (directory.getName().endsWith(format[i])) {

						LocalSong s = new LocalSong();

						s.setTitle(directory.getName());

						list.add(s);
					}
				}
			}

			return list;
		} catch (Exception e) {
			System.out.println("File not found");
			return null;
		}
	}

	/**
	 * Private method, checking if the keyword matches the name of the file.
	 * 
	 * @param keyword
	 * @param song
	 * @return boolean
	 */
	private static boolean keywordMatch(String keyword, File song) {
		if (song.getName().toLowerCase().contains(keyword.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}
}
