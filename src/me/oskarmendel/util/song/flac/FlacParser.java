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

package me.oskarmendel.util.song.flac;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class to parse files of the Flac format retrieving data such as meta tags.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FlacParser.java
 */
public class FlacParser {
	//File to populate with data.
	//private FlacFile flacFile;
	
	//The different blocks of content in the Flac file.
	private static final byte STREAMINFO = 0;
	private static final byte PADDING = 1;
	private static final byte APPLICATION = 2;
	private static final byte SEEKTABLE = 3;
	private static final byte VORBIS_COMMENT = 4;
	private static final byte CUESHEET = 5;
	private static final byte PICTURE = 6;
	// 7-126 reserved
	// 127 invalid, to avoid confusion with a frame sync code
	
	
	private FlacParser() {
		
	}
	
	
	/**
	 * 
	 * @param file
	 * @param flac
	 * @throws IOException 
	 */
	public static void parseFlacFile(File file, FlacFile flac) throws IOException {
		//This constructor populates the FlacFile with content needed.
		//Using private methods that handle the individual parsing. 
		if(!isFlacFile(file)) {
			throw new IllegalArgumentException("File type is not recognized as flac!");
		}
		
		InputStream input = new BufferedInputStream(new FileInputStream(file));
		
		
		
		input.close();
	}
	
	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static boolean isFlacFile(File file) throws IOException{
		InputStream input = new BufferedInputStream(new FileInputStream(file)); //JAVADOC why this can give IOException
		byte[] STREAMTAG = new byte[4];
		input.read(STREAMTAG, 0, 4);
		input.close();
		
		if(STREAMTAG[0] == (byte)'f' && STREAMTAG[1] == (byte)'L' &&
		   STREAMTAG[2] == (byte)'a' && STREAMTAG[3] == (byte)'C') {
			if (new String(STREAMTAG).equals("fLaC")) {
				return true;
			}
		}
		
		return false;
	}
}
