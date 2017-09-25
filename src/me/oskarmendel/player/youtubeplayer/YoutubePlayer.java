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

import javafx.scene.web.WebView;
import me.oskarmendel.entities.Song;
import me.oskarmendel.player.Player;

/**
 * YoutubePlayer that controls the playing of YouTube content.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name YoutubePlayer.java
 */
public class YoutubePlayer extends Player{
	
	/**
	 * Enumeration describing the different statuses of {@link YoutubePlayer}}.
	 */
	public enum Status {
		PAUSED,
		PLAYING
	};
	
	private WebView browserPlayer;
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSong(Song song) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void seek(long seekTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getCurrentTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVolume(int volume) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getVolume() {
		// TODO Auto-generated method stub
		return 0;
	}

}
