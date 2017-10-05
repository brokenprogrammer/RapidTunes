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
 * TODO: REMOVE COMMENTS.
 * //TODO: 1. Volume?
 * //TODO: 2. Limit search to embeddable videos ?
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
	
//	private WebView browserPlayer;
//	private MediaPlayer playerFX;
//	private Media currentSongFX;
	
	// TODO: Place this JS script at a better location?
//	String js = "var x = document.getElementsByClassName(\"ytp-play-button\");"
//			+   "x[0].click();";
	
	public SongPlayerHandler() {
		//currentSongFX = new Media("");
		//playerFX = new MediaPlayer(currentSongFX);
//		browserPlayer = new WebView();
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
//		if (currentSong instanceof LocalSong) {
//			if (this.playerFX != null && this.playerFX.getMedia() != null) {
//				playerFX.play();
//			} else {
//				//Throw exception, trying to play non existing song.
//			}
//		} else if (currentSong instanceof YouTubeSong) {
//			//this.browserPlayer.getEngine().load("https://www.youtube.com/embed/" + this.currentSong.getPath() + "?autoplay=1");
//			if (this.browserPlayer.getEngine().getDocument() != null) {
//				this.browserPlayer.getEngine().executeScript(js);
//			} else {
//				// Throw exception trying to play non initialized web player.
//			}
//		}
		
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
//		if (this.currentSong instanceof LocalSong) {
//			if (this.playerFX.getStatus() == MediaPlayer.Status.PLAYING) {
//				this.playerFX.pause();
//			}
//		} else if (this.currentSong instanceof YouTubeSong) {
//			if (this.browserPlayer.getEngine().getDocument() != null) {
//				this.browserPlayer.getEngine().executeScript(js);
//			}
//		}
		
		switch(this.currentSource) {
		case NONE:
			break;
		case LOCAL:
			this.localPlayer.pause();
			System.out.println("Paused");
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
		
		// Disposes and handles old player.
//		if (this.playerFX != null) {
//			this.playerFX.stop();
//			this.playerFX.dispose();
//		}
		
//		// Disposes the browser player.
//		this.browserPlayer.getEngine().load(null);
//		
//		// If this is a local song object we initiate the media and mediaplayer accordingly
//		if (s instanceof LocalSong) {
//			LocalSong localSong = (LocalSong)s;
//			if (localSong.getSongFormat() == LocalSongFormat.FLAC) {
//				System.out.println("Flac song is being handled.");
//				System.out.println("Sample Rate: " + localSong.getSampleRate());
//				System.out.println("Num Channels: " + localSong.getNumChannels());
//				System.out.println("Bits per sample: " + localSong.getBitsPerSample());
//				
//				//TODO: Own player class for the individual players..
//				AudioFormat format = new AudioFormat(localSong.getSampleRate(), localSong.getBitsPerSample(), 
//						localSong.getNumChannels(), true, false);
//				
//				DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
//				//SourceDataLine line = (SourceDataLine)AudioSystem.getLine(info);
//				
//				//line.open(format);
//				//line.start();
//				
//			} else {
//				this.currentSongFX = new Media(currentSong.getPath());
//				this.playerFX = new MediaPlayer(this.currentSongFX);
//			}
//			
//		} else if (s instanceof YouTubeSong) { // If the song is a Youtube song object we initiate the web player.
//			this.browserPlayer.getEngine().load("https://www.youtube.com/embed/" + this.currentSong.getPath() + "?autoplay=1");
//		}
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
