/**
 * RapidTunes.
 * The music application to help you use all your music sources in one place.
 *
 * The MIT License (MIT)
 *
 * Copyright (C) 2018 The RapidTunes
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

package me.oskarmendel.util.song.flac.inputstream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * An implementation of the FlacInputStream using the AbstractFlacInputStream as a base.
 * This is using a RandomAccessFile as an underlying stream.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name AbstractFlacInputStream.java
 */
public class SeekableFlacInputStream extends AbstractFlacInputStream {
	
	/**
	 * Underlying stream to read data from. 
	 */
	private RandomAccessFile randomAccessFile;
	
	/**
	 * Constructs a new SeekableFlacInputStream using the specified
	 * file.
	 * 
	 * @param file - File to form stream of.
	 * @throws IOException - If something goes wrong initializing the RandomAccessFile.
	 */
	public SeekableFlacInputStream(File file) throws IOException {
		super();
		
		this.randomAccessFile = new RandomAccessFile(file, "r");
	}
	
	/**
	 * Returns the total number of bytes in the Flac file that this
	 * input stream is reading. 
	 * 
	 * @return - Total number of bytes in underlying file.
	 */
	@Override
	public long getLength() {
		try {
			return this.randomAccessFile.length();
		} catch (IOException e) {
			//TODO: RuntimeException is unacceptable for RapidTunes,
			//	Change this to something sane. Oskar Mendel 2018-03-09
			throw new RuntimeException(e);
		}
	}

	/**
	 * Changes the position of the input stream to the specific byte offset
	 * from the start of the stream.
	 * This also resets the CRCs and the bit position.
	 * 
	 * @param position - Byte offset to change the stream to.
	 * 
	 * @throws IOException - TODO
	 */
	@Override
	public void seekTo(long position) throws IOException {
		this.randomAccessFile.seek(position);
		this.positionChanged(position);
	}

	/**
	 * Abstract readUnderlying method that allows for child's to specify how to read
	 * a byte from its underlying byte buffer.
	 * 
	 * @param buf - The buffer into which the data is read.
	 * @param off - The start offset in array b at which the data is written.
	 * @param len - The maximum number of bytes read.
	 * 
	 * @return - the total number of bytes read into the buffer, 
	 * 	or -1 if there is no more data because the end of the file has been reached.
	 * 
	 * @throws IOException - TODO
	 */
	@Override
	protected int readUnderlying(byte[] buf, int off, int len) throws IOException {
		return this.randomAccessFile.read(buf, off, len);
	}
	
	/**
	 * Closes the RandomAccessFile member's stream and then proceeds 
	 * with closing the superclass's buffers.
	 */
	@Override
	public void close() throws IOException {
		if (this.randomAccessFile != null) {
			this.randomAccessFile.close();
			this.randomAccessFile = null;
			super.close();
		}
	}
}
