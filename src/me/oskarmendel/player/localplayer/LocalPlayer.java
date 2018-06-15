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

package me.oskarmendel.player.localplayer;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import me.oskarmendel.player.Player;
import me.oskarmendel.song.LocalSong;
import me.oskarmendel.song.LocalSongFormat;
import me.oskarmendel.song.Song;

/**
 * LocalPlayer that controls the playing of local content.
 * TODO: Separate player for FLAC, handle the separate player in 
 * 	play / pause etc..
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name LocalPlayer.java
 */
public class LocalPlayer extends Player {
	
	private MediaPlayer fxPlayer;
	private FlacPlayer flacPlayer;
	private Media currentMedia;
	
	/**
	 * Default constructor for the LocalPlayer initializing all 
	 * fields to default values.
	 */
	public LocalPlayer() {
		this.flacPlayer = new FlacPlayer();
		this.status = Status.READY;
	}
	
	/**
	 * Starts playing the song or media. If the song was previously 
	 * paused the song will resume the playback at where it was paused.
	 */
	@Override
	public void play() {
		if (this.getStatus() == Status.READY || this.getStatus() == Status.PAUSED) {
			if (this.fxPlayer != null && this.fxPlayer.getMedia() != null) {
				fxPlayer.play();
				
				this.status = Status.PLAYING;
			} else {
				//TODO: Throw exception, trying to play non existing song.
			}
		}
	}

	/**
	 * Pauses the playing of the song or media.
	 */
	@Override
	public void pause() {
		if (this.getStatus() == Status.PLAYING) {
			if (this.fxPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
				this.fxPlayer.pause();
				
				this.status = Status.PAUSED;
			}
		}
	}
	
	/**
	 * Stops this player completley disposing media used by this player.
	 */
	@Override
	public void stop() {
		// Disposes and handles old player.
		if (this.fxPlayer != null) {
			this.fxPlayer.stop();
			this.fxPlayer.dispose();
		}
		
		this.status = Status.STOPPED;
	}

	/**
	 * Sets the currently playing song to the specified Song object.
	 * 
	 * @param song - Requested Song to play.
	 */
	@Override
	public void setSong(Song song) {
		LocalSong localSong = (LocalSong)song;
		
		// Disposes and handles old player.
		if (this.fxPlayer != null) {
			this.fxPlayer.stop();
			this.fxPlayer.dispose();
		}
		
		if (localSong.getSongFormat() == LocalSongFormat.FLAC) {
			this.flacPlayer.setSong(localSong);
		} else {
			this.currentMedia = new Media(localSong.getPath());
			this.fxPlayer = new MediaPlayer(this.currentMedia);
			this.fxPlayer.play();
		}
		
		this.status = Status.PLAYING;
	}

	/**
	 * Seeks the player to a new target time in seconds within the song or media. 
	 * 
	 * @param seekTime - Requested playback time in seconds.
	 */
	@Override
	public void seek(long seekTime) {
		if (this.fxPlayer != null) {
			//TODO: Check that seekTime is in range.
			this.fxPlayer.seek(Duration.seconds(seekTime));
		} else {
			// TODO: Create own RT exception type for this.
		}
	}

	/**
	 * Getter for the current playback time in seconds for the 
	 * currently playing song or media.
	 * 
	 * @return - Current playback time of the currently playing media or song in seconds.
	 */
	@Override 
	public ReadOnlyObjectProperty<Duration> getCurrentTime() {
		if (this.fxPlayer != null) {
			return this.fxPlayer.currentTimeProperty();
		} else if (this.flacPlayer != null) {
			//TODO: Add some enum state for which player is being used.
			return this.currentTime;
		} else {
			// TODO: Create own RT exception type for this.
			throw new IllegalStateException("fxPlayer not initialized.");
		}
	}

	/**
	 * Setter for the volume value of this player. Accepts a value between
	 * 0 - 100.
	 * 
	 * @param volume - Target volume to set the player to.
	 */
	@Override
	public void setVolume(int volume) {
		if (volume < 0 || volume > 100) {
			throw new IllegalArgumentException("Invalid volume value, specify a volume between 0-100.");
		}
		
		if (this.fxPlayer != null) {
			this.volume = volume;
			this.fxPlayer.setVolume(this.volume * 0.01);
		} else {
			// Throw error trying to use non existing media player.
		}
	}

	/**
	 * Getter for the volume value of this player. 
	 * 
	 * @return - Current volume of this player.
	 */
	@Override
	public int getVolume() {
		return this.volume;
	}
}
