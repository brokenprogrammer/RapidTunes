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

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebView;
import me.oskarmendel.entities.LocalSong;
import me.oskarmendel.entities.LocalSongFormat;
import me.oskarmendel.entities.Song;
import me.oskarmendel.entities.YouTubeSong;

/**
 * Handles the playing of songs within the application. 
 * Also determines which player to use depending on file format.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SongPlayerHandler.java
 */
public class SongPlayerHandler {
	
	private Song currentSong;
	
	private WebView browserPlayer;
	private MediaPlayer playerFX;
	private Media currentSongFX;
	
	// TODO: Place this JS script at a better location?
	String js = "var x = document.getElementsByClassName(\"ytp-play-button\");"
			+   "x[0].click();";
	
	public SongPlayerHandler() {
		//currentSongFX = new Media("");
		//playerFX = new MediaPlayer(currentSongFX);
		browserPlayer = new WebView();
	}
	
	/**
	 * Play the current song within the MediaPlayer if it is initialized.
	 * 
	 * @throws - 
	 */
	public void play() {
		if (currentSong instanceof LocalSong) {
			if (this.playerFX != null && this.playerFX.getMedia() != null) {
				playerFX.play();
			} else {
				//Throw exception, trying to play non existing song.
			}
		} else if (currentSong instanceof YouTubeSong) {
			//this.browserPlayer.getEngine().load("https://www.youtube.com/embed/" + this.currentSong.getPath() + "?autoplay=1");
			if (this.browserPlayer.getEngine().getDocument() != null) {
				this.browserPlayer.getEngine().executeScript(js);
			} else {
				// Throw exception trying to play non initialized web player.
			}
			
			//TODO: 1. Volume?
			//TODO: 2. Limit search to embeddable videos ?
		}
	}
	
	/**
	 * Pauses the current song if the player is is playing.
	 */
	public void pause() {
		if (this.currentSong instanceof LocalSong) {
			if (this.playerFX.getStatus() == MediaPlayer.Status.PLAYING) {
				this.playerFX.pause();
			}
		} else if (this.currentSong instanceof YouTubeSong) {
			if (this.browserPlayer.getEngine().getDocument() != null) {
				this.browserPlayer.getEngine().executeScript(js);
			}
		}
	}
	
	/**
	 * Sets the currently playing song object.
	 * 
	 * @param s - Song object.
	 */
	public void setSong(Song s) {
		this.currentSong = s;
		
		// Disposes and handles old player.
		if (this.playerFX != null) {
			this.playerFX.stop();
			this.playerFX.dispose();
		}
		
		// Disposes the browser player.
		this.browserPlayer.getEngine().load(null);
		
		// If this is a local song object we initiate the media and mediaplayer accordingly
		if (s instanceof LocalSong) {
			LocalSong localSong = (LocalSong)s;
			if (localSong.getSongFormat() == LocalSongFormat.FLAC) {
				System.out.println("Flac song is being handled.");
				System.out.println("Sample Rate: " + localSong.getSampleRate());
				System.out.println("Num Channels: " + localSong.getNumChannels());
				System.out.println("Bits per sample: " + localSong.getBitsPerSample());
				
				//TODO: Own player class for the individual players..
				AudioFormat format = new AudioFormat(localSong.getSampleRate(), localSong.getBitsPerSample(), 
						localSong.getNumChannels(), true, false);
				
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
				//SourceDataLine line = (SourceDataLine)AudioSystem.getLine(info);
				
				//line.open(format);
				//line.start();
				
			} else {
				this.currentSongFX = new Media(currentSong.getPath());
				this.playerFX = new MediaPlayer(this.currentSongFX);
			}
			
		} else if (s instanceof YouTubeSong) { // If the song is a Youtube song object we initiate the web player.
			this.browserPlayer.getEngine().load("https://www.youtube.com/embed/" + this.currentSong.getPath() + "?autoplay=1");
		}
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
