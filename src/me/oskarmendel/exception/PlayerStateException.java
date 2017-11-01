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

package me.oskarmendel.exception;

import me.oskarmendel.player.Player;

/**
 * Exception class thrown when an illegal player state is encountered.
 * 
 * @author Oskar Mendel
 * @name PlayerStateException.java
 * @version 0.00.00
 */
public class PlayerStateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5334388931378231018L;

	/**
	 * Default constructor for the PlayerStateException, constructs a new PlayerStateException 
	 * with null as its detail message. 
	 */
	public PlayerStateException() {
		super();
	}
	
	/**
	 * Constructs a new PlayerStateException with the specified detail message.
	 * 
	 * @param message - The detail message.
	 */
	public PlayerStateException(String message) {
		super(message);
	}
	
	/**
	 * Constructs a new PlayerStateException with the a generated detail message using
	 * the specified player.
	 * 
	 * @param player - Player to generate detail message from.
	 */
	public PlayerStateException(Player player) {
		super("Player Status: " + player.getStatus() + 
				" Player current time: " + player.getCurrentTime().get());
	}
	
	/**
	 * Constructs a new PlayerStateException with the specified detail message and data from
	 * the specified player.
	 * 
	 * @param message - The detail message.
	 * @param player - Player object to retrieve data from.
	 */
	public PlayerStateException(String message, Player player) {
		super(message + ", Player status: " + player.getStatus() +
				" Player current time: " + player.getCurrentTime().get());
	}

	/**
	 * Constructs a new PlayerStateException with the specified detail message and cause. 
	 * 
	 * @param message - The detail message.
	 * @param cause - The cause.
	 */
	public PlayerStateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new PlayerStateException with the specified cause and a detail message of 
	 * (cause==null ? null : cause.toString())
	 * 
	 * @param cause - The cause.
	 */
	public PlayerStateException(Throwable cause) {
		super(cause);
	}
}
