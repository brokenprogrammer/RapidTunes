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

package me.oskarmendel.util.song.mp4

import java.io.File

import me.oskarmendel.util.song.MusicFile

/**
 * Object to represent files of the MP4 format.
 * Implemented using the formatting for MP4 files represented here:
 * http://xhelmboyx.tripod.com/formats/mp4-layout.txt
 * TODO: Tests
 *
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Mp4File.groovy
 */
class Mp4File extends MusicFile{
	
	Mp4File() {
		
	}
	
	/**
	 * Constructs a new Mp4File object and directly populates it with data
	 * from the specified file at the target file path.
	 *
	 * @param path - Path of the Mp4 file.
	 */
	Mp4File(String path) {
		this.filePath = path
		this.file = new File(this.filePath)
		
		parse()
	}
	
	/**
	 * Constructs a new Mp4File object and directly populates it with data
	 * from the specified file object.
	 *
	 * @param file - File object to retrieve data from.
	 */
	Mp4File(File file) {
		this.file = file
		this.filePath = "$file"
		
		parse()
	}
	
	/**
	 * Initializes the parsing of data using the Mp4Parser class.
	 *
	 * @throws IllegalArgumentException - When the Mp4File doesn't have an initialized file and filepath.
	 */
	@Override
	def parse() {
		// TODO Auto-generated method stub
		return null;
	}

}
