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

package me.oskarmendel.player.youtubeplayer;

import java.io.File;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import me.oskarmendel.player.Player;
import me.oskarmendel.song.Song;
import me.oskarmendel.song.YouTubeSong;

/**
 * YoutubePlayer that controls the playing of YouTube content.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name YouTubePlayer.java
 */
public class YouTubePlayer extends Player{
	
	/**
	 * Enumeration describing the different statuses of {@link YoutubePlayer}}.
	 */
	public enum Status {
		READY,
		PAUSED,
		PLAYING,
		STOPPED
	};
	
	private WebView browserPlayer;
	private Status status;
	
	/**
	 * Default constructor for the YouTubePlayer initializing all 
	 * fields to default values.
	 */
	public YouTubePlayer() {
		super();
		
		browserPlayer = new WebView();
		File localHtml = new File("res/view/player/YouTube.html");
		
		this.browserPlayer.getEngine().load(localHtml.toURI().toString());
		this.status = Status.READY;
		
		//TODO: Set volume, Set current time.. 
	}
	
	/**
	 * Starts playing the song or media. If the song was previously 
	 * paused the song will resume the playback at where it was paused.
	 */
	@Override
	public void play() {
		if (this.getStatus() == Status.READY || this.getStatus() == Status.PAUSED) {
			if (this.browserPlayer.getEngine().getDocument() != null) {
				this.browserPlayer.getEngine().executeScript("startVideo()");
				
				this.status = Status.PLAYING;
				startTimer();
			} else {
				// TODO: Throw exception trying to play non initialized web player.
			}
		}
	}

	/**
	 * Pauses the playing of the song or media.
	 */
	@Override
	public void pause() {
		if (this.getStatus() == Status.PLAYING) {
			if (this.browserPlayer.getEngine().getDocument() != null) {
				this.browserPlayer.getEngine().executeScript("pauseVideo()");
				
				this.status = Status.PAUSED;
				stopTimer();
			} else {
				// TODO: Throw exception trying to play non initialized web player.
			}
		}
	}
	
	/**
	 * Stops this player completley disposing media used by this player.
	 */
	@Override
	public void stop() {
		// Disposes the browser player.
		this.browserPlayer.getEngine().load(null);
		
		this.status = Status.STOPPED;
		stopTimer();
	}
	
	/**
	 * Sets the currently playing song to the specified Song object.
	 * 
	 * @param song - Requested Song to play.
	 */
	@Override
	public void setSong(Song song) {
		YouTubeSong youtubeSong = (YouTubeSong)song;
		this.browserPlayer.getEngine().executeScript("setSong('" + youtubeSong.getPath() + "')");
		
		this.currentTime.set(Duration.ZERO);
		
		this.status = Status.PLAYING;
		startTimer();
	}

	@Override
	public void seek(long seekTime) {
		stopTimer();
		this.browserPlayer.getEngine().executeScript("seek(" + seekTime + ")");
		this.currentTime.set(Duration.seconds(seekTime));
		startTimer();
	}

	@Override
	public ReadOnlyObjectProperty<Duration> getCurrentTime() {
		return this.currentTime;
	}

	@Override
	public void setVolume(int volume) {
		this.browserPlayer.getEngine().executeScript("setVolume(" + volume + ")");
	}

	@Override
	public int getVolume() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Getter for the current status of the YouTubePlayer.
	 * 
	 * @return - Current status of the YouTubePlayer.
	 */
	public Status getStatus() {
		return this.status;
	}
}
