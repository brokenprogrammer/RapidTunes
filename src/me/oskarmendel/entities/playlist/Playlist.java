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

package me.oskarmendel.entities.playlist;

import java.util.ArrayList;
import java.util.List;

import me.oskarmendel.entities.songs.Song;

/**
 * Represents a playlist. A playlist is a collection of
 * songs that can be created by the user.
 * 
 * @author Jesper Bergstrom
 * @version 0.00.00
 * @name Playlist.java
 */
public class Playlist {
	
	private ArrayList<Song> songs;
	private String name;
	
	/**
	 * Constructor that initializes a playlist. Sets name from parameter.
	 * 
	 * @param name
	 */
	public Playlist(String name){
		songs = new ArrayList<Song>();
		this.name = name;
	}
	
	
	/**
	 * Constructor that initializes a playlist.
	 */
	public Playlist(){
		songs = new ArrayList<Song>();
	}
	
	/**
	 * Returns a list with all songs in the playlist.
	 * 
	 * @return songs
	 */
	public List<Song> getSongs(){
		return songs;
	}
	
	/**
	 * Returns the name of the playlist.
	 * 
	 * @return name
	 */
	public String getName(){
		return name;
	}
	
	public void loadPlaylist(){
		
	}
	
	public void savePlaylist(){
		
	}
}
