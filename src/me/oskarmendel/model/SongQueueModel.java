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

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.oskarmendel.song.Song;

/**
 * Model that manages the current queue of Songs for the application.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SongQueueModel.java
 */
public class SongQueueModel {
	
	private final ObservableList<Song> songQueue = FXCollections.observableArrayList();
	
	/**
	 * Getter for the ObservableList containing the queue of Songs.
	 * 
	 * @return - ObservableList of Song objects for the queue of Songs.
	 */
	public ObservableList<Song> getSongQueue() {
		return this.songQueue;
	}
	
	/**
	 * Clears the Song Queue and adds all the elements in the specified
	 * list of Songs to the queue.
	 * 
	 * @param songList - List of Songs to add to the Song Queue.
	 */
	public void setSongQueue(List<Song> songList) {
		songQueue.setAll(songList);
	}
	
	/**
	 * Adds all the elements within the specified Song List at the end
	 * of the Song Queue.
	 * 
	 * @param songList - List of songs to add at the end of the Song Queue.
	 */
	public void addSongs(List<Song> songList) {
		songQueue.addAll(songList);
	}
	
	/**
	 * Retrieves the next Song in the Queue and places it in the 
	 * end of the queue.
	 * 
	 * @return - Next Song in the Queue.
	 */
	public Song getNext() {
		Song song = songQueue.get(0);
		
		// Places the retrieved song at the end of the list.
		songQueue.remove(0);
		songQueue.add(song);
		
		return song;
	}
	
	/**
	 * Retrieves the last Song in the Queue and places it in the 
	 * front of the queue.
	 * 
	 * @return - Previous Song in the Queue.
	 */
	public Song getPrevious() {
		Song song = songQueue.get(songQueue.size()-1);
		
		songQueue.remove(songQueue.size()-1);
		songQueue.add(0, song);
		
		return song;
	}
}
