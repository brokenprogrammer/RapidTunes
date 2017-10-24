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

package me.oskarmendel.util.song;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import me.oskarmendel.song.Song;

/**
 * Class with static methods to provide Utility functions for Songs.
 * Example converting the Song length into a display format etc.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SongUtil.java
 */
public class SongUtil {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	/**
	 * Get the song length as a String with the format HH:mm:ss.
	 * 
	 * @return - String representation of the Song length.
	 */
	public static String lengthToString(Song s) {
		LocalTime time = LocalTime.MIN.plusSeconds(s.getLength());
		
		return formatter.format(time);
	}
	
	/**
	 * Converts the current Song time into String with format HH:mm:ss.
	 * 
	 * @param seconds - Seconds value to convert into String format.
	 * 
	 * @return - String representation of the current Song time.
	 */
	public static String currentSongTimeToString(double seconds) {
		LocalTime t = LocalTime.MIN.plusSeconds((long) seconds);
		
		return t.toString();
	}
}
