package me.oskarmendel.player.search.local;

import java.io.File;
import java.util.ArrayList;

import me.oskarmendel.entities.Song;

/**
 * Class for searching a local directory for sound files.
 * 
 * @author Jesper
 * @version 0.00.00
 * @name LocalSearch.java
 */
public class LocalSearch {

	ArrayList<Song> list = new ArrayList<Song>();

	public LocalSearch() {

	}

	public ArrayList<Song> search(String keyword, File directory) {

		// if file is directory
		if (directory.isDirectory()) {

			File[] filesList = directory.listFiles();

			for (int i = 0; i < filesList.length; i++) {
				search(keyword, filesList[i]);
			}
		}
		// if file is .mp3, .flac or .wav and if the keyword matches
		else if (keywordMatch(keyword, directory)) {

			if (directory.getName().endsWith(".mp3") || 
				directory.getName().endsWith(".flac")||
				directory.getName().endsWith(".wav")) {

				Song s = new Song();

				s.setTitle(directory.getName());

				list.add(s);
			}
		}

		return list;
	}

	// Checks if keyword matches the song
	private static boolean keywordMatch(String keyword, File song) {

		if (song.getName().toLowerCase().contains(keyword.toLowerCase()))
			return true;

		else
			return false;
	}
}
