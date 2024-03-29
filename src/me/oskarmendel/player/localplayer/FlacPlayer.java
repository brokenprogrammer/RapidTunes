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
import javafx.util.Duration;
import me.oskarmendel.player.Player;
import me.oskarmendel.song.LocalSong;
import me.oskarmendel.song.LocalSongFormat;
import me.oskarmendel.song.Song;

/**
 * FlacPlayer that manages the playing of local flac files.
 * 
 * TODO: There is like a 1 second delay upon pausing the FlacPlayerThread, make it NONE.
 * TODO: Implement stop.
 * TODO: Implement seek
 * TODO: Implement volume.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FlacPlayer.java
 */
public class FlacPlayer extends Player {	
	private LocalSong currentSong;
	private FlacPlayerThread playerThread;
	
	/**
	 * Default constructor for the LocalPlayer initializing all 
	 * fields to default values.
	 */
	public FlacPlayer() {
		this.status = Status.READY;
	}
	
	/**
	 * Starts playing the song or media. If the song was previously 
	 * paused the song will resume the playback at where it was paused.
	 */
	@Override
	public void play() {
		if (this.getStatus() == Status.READY || this.getStatus() == Status.PAUSED) {
			if (this.playerThread.isReady() && this.playerThread.isPlaying()) {
				this.playerThread.run();
				
				this.status = Status.PLAYING;
				startTimer();
			} else if (this.playerThread.isReady() && !this.playerThread.isPlaying()) {
				this.playerThread.play();
				
				this.status = Status.PLAYING;
				startTimer();
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
			this.playerThread.pause();
			
			this.status = Status.PAUSED;
			stopTimer();
		}
	}
	
	/**
	 * Stops this player completley disposing media used by this player.
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
		//stopTimer();
	}

	/**
	 * Sets the currently playing song to the specified Song object.
	 * 
	 * @param song - Requested Song to play.
	 */
	@Override
	public void setSong(Song song) {
		if (!(song instanceof LocalSong)) {
			throw new IllegalArgumentException("Expected LocalSong.");
		}
		
		LocalSong localSong = (LocalSong)song;
		if (localSong.getSongFormat() != LocalSongFormat.FLAC) {
			throw new IllegalArgumentException("Expected LocalSong to have format FLAC.");
		}
		
		this.currentSong = localSong;
		this.currentTime.set(Duration.ZERO);

		this.playerThread = new FlacPlayerThread(this.currentSong);
		this.playerThread.setDaemon(true);
		this.playerThread.start();
		
		this.status = Status.PLAYING;
		startTimer();
	}
	
	/**
	 * Seeks the player to a new target time in seconds within the song or media. 
	 * 
	 * @param seekTime - Requested playback time in seconds.
	 */
	@Override
	public void seek(long seekTime) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Getter for the current playback time in seconds for the 
	 * currently playing song or media.
	 * 
	 * @return - Current playback time of the currently playing media or song in seconds.
	 */
	@Override
	public ReadOnlyObjectProperty<Duration> getCurrentTime() {
		return this.currentTime;
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
		
		this.volume = volume;
		this.playerThread.setGain(this.volume);
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
