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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import me.oskarmendel.entities.Song;

/**
 * Model that manages the currently played song for the application.
 * This allows mulitple controllers have access to the currently played song.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name CurrentlyPlayingModel.java
 */
public class CurrentlyPlayingModel {
	
	private ObjectProperty<Song> currentSong = new SimpleObjectProperty<>();
	private final StringProperty songTitle = new SimpleStringProperty();
	private final StringProperty songArtist = new SimpleStringProperty();
	
	/**
	 * Gets the ObjectProperty current song.
	 * @return An ObjectProperty for the current song.
	 */
	public ObjectProperty<Song> getCurrentSong() {
		return currentSong;
	}
	
	/**
	 * Sets the currently playing song.
	 * 
	 * @param s - Song object to be played.
	 */
	public void setCurrentSong(Song s) {
		currentSong.set(s);
		songTitle.set(s.getTitle());
		songArtist.set(s.getArtist());
	}
	
	/**
	 * Returns a StringProperty for the current song title.
	 * 
	 * @return StringProperty of current song title.
	 */
	public StringProperty getCurrentSongTitle() {
		return songTitle;
	}
	
	/**
	 * Returns a StringProperty for the current song artist.
	 * 
	 * @return StringProperty of current song artist.
	 */
	public StringProperty getCurrentSongArtist() {
		return songArtist;
	}
}
