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

import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.util.Duration;
import me.oskarmendel.song.Song;

/**
 * Abstract Player class that holds common data and methods for all music players.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Player.java
 */
public abstract class Player implements PlayerInterface {
	
	/**
	 * Enumeration describing the different statuses of {@link Player}}.
	 */
	public enum Status {
		READY,
		PAUSED,
		PLAYING,
		STOPPED
	};
	
	protected Song song;
	
	protected ReadOnlyObjectWrapper<Duration> currentTime;
	
	protected int volume;
	
	protected Timer timer;
	
	protected Status status;
	
	/**
	 * Default constructor for the abstract Player class that initializes
	 * the Player with default values.
	 */
	public Player() { 
		this.currentTime = new ReadOnlyObjectWrapper<Duration>();
		this.currentTime.set(Duration.ZERO);
	}
	
	/**
	 * Starts the timer for the Player that is used to count seconds
	 * for the current time of the Player.
	 */
	public void startTimer() {
		this.timer = new Timer(true);
		this.timer.schedule(new TimerTask() {
			@Override
			public void run() {
				currentTime.set(currentTime.get().add(Duration.millis(100)));
			}
		}, 0, 100);
	}
	
	/**
	 * Stops the timer for the Player that counts the current time of the
	 * Player.
	 */
	public void stopTimer() {
		this.timer.cancel();
	}
	
	/**
	 * Getter for the current status of the Player.
	 * 
	 * @return - Current status of the Player.
	 */
	public Status getStatus() {
		return this.status;
	}
}
