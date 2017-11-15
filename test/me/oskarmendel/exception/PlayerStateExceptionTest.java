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

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.util.Duration;
import me.oskarmendel.player.Player;
import me.oskarmendel.player.Player.Status;

/**
 * Tests for the PlayerStateExceptionTest.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name PlayerStateExceptionTest.java
 */
public class PlayerStateExceptionTest {
	
	@Mock
	Player mockedPlayer;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockedPlayer = Mockito.mock(Player.class);
		
		Mockito.when(this.mockedPlayer.getStatus()).thenReturn(Status.PLAYING);
		Mockito.when(this.mockedPlayer.getCurrentTime()).thenReturn(new ReadOnlyObjectWrapper<Duration>(Duration.ONE));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link me.oskarmendel.exception.PlayerStateException#PlayerStateException()}.
	 */
	@Test(expected = PlayerStateException.class)
	public final void testPlayerStateException() throws PlayerStateException {
		PlayerStateException playerException = new PlayerStateException();
		
		assertEquals("The PlayerStateException should be initialized with null as message.", playerException.getMessage(), null);
		
		throw new PlayerStateException();
	}

	/**
	 * Test method for {@link me.oskarmendel.exception.PlayerStateException#PlayerStateException(java.lang.String)}.
	 */
	@Test
	public final void testPlayerStateExceptionString() {
		try {
			throw new PlayerStateException("PlayerStateException was thrown.");
		} catch(PlayerStateException e) {
			assertEquals("Specified detail message is in the thrown exception.", e.getMessage(), "PlayerStateException was thrown.");
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.exception.PlayerStateException#PlayerStateException(me.oskarmendel.player.Player)}.
	 */
	@Test
	public final void testPlayerStateExceptionPlayer() {
		try {
			throw new PlayerStateException(this.mockedPlayer);
		} catch(PlayerStateException e) {
			assertEquals("Detail message is generated using Player data.", e.getMessage(), "Player Status: PLAYING Player current time: 1.0 ms");
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.exception.PlayerStateException#PlayerStateException(java.lang.String, me.oskarmendel.player.Player)}.
	 */
	@Test
	public final void testPlayerStateExceptionStringPlayer() {
		try {
			throw new PlayerStateException("Player reached an illegal state", this.mockedPlayer);
		} catch(PlayerStateException e) {
			assertEquals("Detail message is generated using Player data.", e.getMessage(), "Player reached an illegal state, Player Status: PLAYING Player current time: 1.0 ms");
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.exception.PlayerStateException#PlayerStateException(java.lang.String, java.lang.Throwable)}.
	 */
	@Test
	public final void testPlayerStateExceptionStringThrowable() {
		IOException cause = new IOException();
		try {
			throw new PlayerStateException("Player failed with specified cause.", cause);
		} catch(PlayerStateException e) {
			assertEquals("Specified detail message is added.", e.getMessage(), "Player failed with specified cause.");
			assertEquals("Specified cause is added.", e.getCause(), cause);
		}
	}

	/**
	 * Test method for {@link me.oskarmendel.exception.PlayerStateException#PlayerStateException(java.lang.Throwable)}.
	 */
	@Test
	public final void testPlayerStateExceptionThrowable() {
		IOException cause = new IOException();
		try {
			throw new PlayerStateException(cause);
		} catch(PlayerStateException e) {
			assertEquals("Specified cause is added.", e.getCause(), cause);
		}
	}
}
