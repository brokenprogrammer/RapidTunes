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

import me.oskarmendel.entities.LocalSong;
import me.oskarmendel.entities.Song;
import me.oskarmendel.entities.YouTubeSong;
import me.oskarmendel.player.localplayer.LocalPlayer;
import me.oskarmendel.player.youtubeplayer.YouTubePlayer;

/**
 * Handles the playing of songs within the application. 
 * Also determines which player to use depending on file format.
 * 
 * //TODO: 1. Limit search to embeddable videos ?
 * TODO: WHAT IF NO SOURCE? Initialize source to something..
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SongPlayerHandler.java
 */
public class SongPlayerHandler {
	
	/**
	 * Enumeration describing which source the {@link SongPlayerHandler} is
	 * currently using.
	 */
	public enum Source {
		NONE,
		LOCAL,
		YOUTUBE,
		SPOTIFY,
		SOUNDCLOUD
	}
	
	private Song currentSong;
	private Source currentSource;
	
	private LocalPlayer localPlayer;
	private YouTubePlayer youtubePlayer;
	
	public SongPlayerHandler() {
		this.localPlayer = new LocalPlayer();
		this.youtubePlayer = new YouTubePlayer();
		
		this.currentSource = Source.NONE;
	}
	
	/**
	 * Play the current song within the MediaPlayer if it is initialized.
	 * 
	 * @throws - 
	 */
	public void play() {
		switch(this.currentSource) {
		case LOCAL:
			this.localPlayer.play();
			break;
		case YOUTUBE:
			this.youtubePlayer.play();
			break;
		case SPOTIFY:
			break;
		case SOUNDCLOUD:
			break;
		default:
			break;
		}
	}
	
	/**
	 * Pauses the current song if the player is is playing.
	 */
	public void pause() {
		switch(this.currentSource) {
		case NONE:
			break;
		case LOCAL:
			this.localPlayer.pause();
			break;
		case YOUTUBE:
			this.youtubePlayer.pause();
			break;
		case SPOTIFY:
			break;
		case SOUNDCLOUD:
			break;
		}
	}
	
	/**
	 * Stops this player completley disposing media used by this player.
	 */
	public void stop ( ) {
		switch(this.currentSource) {
		case NONE:
			break;
		case LOCAL:
			this.localPlayer.stop();
			break;
		case YOUTUBE:
			this.youtubePlayer.stop();
			break;
		case SPOTIFY:
			break;
		case SOUNDCLOUD:
			break;
		}
	}
	
	/**
	 * Sets the currently playing song object.
	 * 
	 * @param s - Song object.
	 */
	public void setSong(Song s) {
		this.currentSong = s;
		
		// Pause currently used player.
		switch(this.currentSource) {
		case NONE:
			break;
		case LOCAL:
			this.localPlayer.pause();
			break;
		case YOUTUBE:
			this.youtubePlayer.pause();
			break;
		case SPOTIFY:
			break;
		case SOUNDCLOUD:
			break;
		}
		
		// Set song and source for player to be used.
		if (s instanceof LocalSong) {
			this.currentSource = Source.LOCAL;
			System.out.println("Setting localsong");
			this.localPlayer.setSong(this.currentSong);
			
		} else if (s instanceof YouTubeSong) {
			this.currentSource = Source.YOUTUBE;
			System.out.println("Setting youtube song");
			this.youtubePlayer.setSong(this.currentSong);
		}
	}
	
	/**
	 * Seeks the player to a new target time in seconds within the song or media. 
	 * 
	 * @param seekTime - Requested playback time in seconds.
	 */
	public void seek(int seekTime) {
		switch(this.currentSource) {
		case NONE:
			break;
		case LOCAL:
			this.localPlayer.seek(seekTime);
			break;
		case YOUTUBE:
			this.youtubePlayer.seek(seekTime);
			break;
		case SPOTIFY:
			break;
		case SOUNDCLOUD:
			break;
		}
	}
	
	/**
	 * Setter for the volume of the currently used SongPlayer.
	 * 
	 * @param volume - Volume value to set to the currently used SongPlayer.
	 */
	public void setVolume(int volume) {
		if (volume < 0 || volume > 100) {
			throw new IllegalArgumentException("Invalid volume value, specify a volume between 0-100.");
		}
		
		switch(this.currentSource) {
		case LOCAL:
			this.localPlayer.setVolume(volume);
			break;
		case YOUTUBE:
			this.youtubePlayer.setVolume(volume);
			break;
		case SPOTIFY:
			break;
		case SOUNDCLOUD:
			break;
		default:
			break;
		}
	}
	
	/**
	 * Getter for the volume of the currently used SongPlayer.
	 * 
	 * @return - Volume value of the currently used SongPlayer.
	 */
	public int getVolume() {
		switch(this.currentSource) {
		case LOCAL:
			return this.localPlayer.getVolume();
		case YOUTUBE:
			return this.youtubePlayer.getVolume();
		case SPOTIFY:
			return 0;
		case SOUNDCLOUD:
			return 0;
		default:
			throw new IllegalStateException(); //TODO: Right exception to throw?
		}
	}
	
	/**
	 * Returns the object of the currently played song.
	 * 
	 * @return currently playing song.
	 */
	public Song getSong() {
		return this.currentSong;
	}
	
	/**
	 * Getter for the current source the SongPlayer is using.
	 * 
	 * @return - Current source being used for playing media.
	 */
	public Source getCurrentSource() {
		return this.currentSource;
	}
}
