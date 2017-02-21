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
	private final String[] format = { ".mp3", ".flac", ".wav" };

	public LocalSearch() {

	}
	
	/**
	 * Method for searching all sound files in a directory.
	 * 
	 * @param keyword
	 * @param path
	 * @return list
	 */
	public ArrayList<Song> search(String keyword, String path) {

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

						Song s = new Song();

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

		if (song.getName().toLowerCase().contains(keyword.toLowerCase()))
			return true;

		else
			return false;
	}
}
