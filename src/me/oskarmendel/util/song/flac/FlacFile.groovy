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

package me.oskarmendel.util.song.flac

import me.oskarmendel.util.song.MusicFile

/**
 * Object to represent files of the Flac format.
 * Implemented using the formatting for Flac files represented here:
 * https://xiph.org/flac/format.html
 *
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FlacFile.groovy
 */
class FlacFile extends MusicFile{
	
	int minimumBlockSize	//Represented as 16 bits
	int maximumBlockSize	//Represented as 16 bits
	int minimumFrameSize	//Represented as 24 bits
	int maximumFrameSize	//Represented as 24 bits
	
	int sampleRate			//Represented as 20 bits
	int numChannels		//Represented as 3 bits
	int bitsPerSample		//Represented as 5 bits
	int numSamples			//Represented as 36 bits
	
	//Data from the Vorbis Comments within the Flac file.
	String vendor
	
	/**
	 * Constructs a new FlacFile object and directly populates it with data
	 * from the specified file at the target file path.
	 * 
	 * @param path - Path of the Flac file.
	 */
	FlacFile(String path) {
		this.filePath = path
		this.file = new File(this.filePath)
		
		parse()
	}
	
	/**
	 * Constructs a new FlacFile object and directly populates it with data
	 * from the specified file object.
	 * 
	 * @param file - File object to retrieve data from.
	 */
	FlacFile(File file) {
		this.file = file
		this.filePath = "$file"
		
		parse()
	}

	/**
	 * Initializes the parsing of data using the FlacParser class.
	 */
	@Override
	def parse() {
		FlacParser.parseFlacFile(this.file, this)
	}
}
