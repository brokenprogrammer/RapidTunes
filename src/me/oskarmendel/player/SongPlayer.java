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

package me.oskarmendel.player;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import me.oskarmendel.entities.songs.Song;

/**
 * Handles the playing of songs within the application. 
 * Also determines which player to use depending on file format.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SongPlayer.java
 */
public class SongPlayer {
	
	private Song currentSong;
	
	private MediaPlayer playerFX;
	private Media currentSongFX;
	
	public SongPlayer() {
		//currentSongFX = new Media("");
		//playerFX = new MediaPlayer(currentSongFX);
	}
	
	/**
	 * Play the current song within the MediaPlayer if it is initialized.
	 * 
	 * @throws - 
	 */
	public void play() {
		if (this.playerFX != null && this.playerFX.getMedia() != null) {
			playerFX.play();
		} else {
			//Throw exception, trying to play non existing song.
		}
	}
	
	/**
	 * Pauses the current song if the player is is playing.
	 */
	public void pause() {
		if (this.playerFX.getStatus() == MediaPlayer.Status.PLAYING) {
			this.playerFX.pause();
		}
	}
	
	/**
	 * Sets the currently playing song object.
	 * 
	 * @param s - Song object.
	 */
	public void setSong(Song s) {
		this.currentSong = s;
		
		//Disposes and handles old player.
		if (this.playerFX != null) {
			this.playerFX.stop();
			this.playerFX.dispose();
		}
		
		
		this.currentSongFX = new Media(currentSong.getPath());
		this.playerFX = new MediaPlayer(this.currentSongFX);
	}
	
	/**
	 * Returns the object of the currently played song.
	 * 
	 * @return currently playing song.
	 */
	public Song getSong() {
		return currentSong;
	}
}
