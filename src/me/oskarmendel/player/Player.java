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

import me.oskarmendel.entities.Song;

/**
 * Abstract Player class that holds common data and methods for all music players.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Player.java
 */
public abstract class Player {
	
	protected Song song;
	
	protected long currentTime;
	
	protected int volume;
	
	/**
	 * Starts playing the song or media. If the song was previously 
	 * paused the song will resume the playback at where it was paused.
	 */
	public abstract void play();
	
	/**
	 * Pauses the playing of the song or media.
	 */
	public abstract void pause();
	
	/**
	 * Sets the currently playing song to the specified Song object.
	 * 
	 * @param song - Requested Song to play.
	 */
	public abstract void setSong(Song song);
	
	/**
	 * Seeks the player to a new target time within the song or media. 
	 * 
	 * @param seekTime - Requested playback time.
	 */
	public abstract void seek(long seekTime);
	
	/**
	 * Getter for the current playback time for the currently playing song
	 * or media.
	 * 
	 * @return - Current playback time of the currently playing media or song.
	 */
	public abstract long getCurrentTime();
	
	/**
	 * Setter for the volume value of this player. Accepts a value between
	 * 0 - 100.
	 * 
	 * @param volume - Target volume to set the player to.
	 */
	public abstract void setVolume(int volume);
	
	/**
	 * Getter for the volume value of this player. 
	 * 
	 * @return - Current volume of this player.
	 */
	public abstract int getVolume();
}
