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

package me.oskarmendel.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Parses information about songs required to download them.
 * 
 * @author Oskar
 * @version 0.00.00
 * @name SongParser.java
 */
public class SongParser {
	
	/**
	 * Decides what source the target song is from.
	 * @param songurl - target song URL.
	 * @return an array of strings with data parsed from the song source.
	 */
	public String[] parseSong(String songurl){
		
		//Later check url if its youtube, soundcloud etc..
		return parseYoutube(songurl);
	}
	
	/**
	 * Parses data for youtube videos.
	 * @param songurl - target video URL
	 * @return array with data parsed for that youtube video.
	 */
	private String[] parseYoutube(String songurl){
		String[] videoData = {};
		
		URL url;
		try {
			url = new URL(songurl);
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
				for(String line; (line = reader.readLine()) != null;) {
					//Decode the URL encoded information.
					String result = URLDecoder.decode(line, "UTF-8");
					videoData = result.split("&");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return videoData;
	}
}
