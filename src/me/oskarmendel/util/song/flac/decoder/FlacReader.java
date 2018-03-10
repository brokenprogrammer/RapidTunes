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

package me.oskarmendel.util.song.flac.decoder;

import java.io.File;
import java.io.IOException;

import me.oskarmendel.util.song.flac.Frame;
import me.oskarmendel.util.song.flac.StreamInfo;
import me.oskarmendel.util.song.flac.VorbisComments;
import me.oskarmendel.util.song.flac.inputstream.FlacInputStream;
import me.oskarmendel.util.song.flac.inputstream.SeekableFlacInputStream;
import me.oskarmendel.util.song.flac.structure.FlacMetadataBlockType;

/**
 * Flac reading class responsible for reading metadata for a flac file as well
 * as its raw audio samples.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FlacReader.java
 * 
 * TODO: Finish the SeekTable class.
 * TODO: Look over all classes and make members private.
 * TODO: Implement seek methods.
 * TODO: Update StreamInfo and VorbisComments and connected classes, documentation and what not.
 */
public class FlacReader {

	private FlacInputStream input;
	private StreamInfo streamInfo;
	//public SeekTable seekTable;
	private VorbisComments vorbisComments;

	private FrameReader frameReader;

	private long metadataEndPosition;
	
	public FlacReader(File file) throws IOException {
		this.input = new SeekableFlacInputStream(file);
		
		// Magic code for fLaC
		if (input.readUnsignedInt(32) != 0x664C6143) {
			throw new IOException(); // TODO: Make a proper exception class for this.
		}
		
		this.metadataEndPosition = -1;
		readMetadataBlocks();
	}
	
	//TODO: Implement this instead of the if statement in constructor.
	private boolean isFlacFile(int magic) {
		return false;
	}
	
	/**
	 * Performs the read on the files meta data blocks and acts depending on which 
	 * block it is currently reading. 
	 * 
	 * @throws IOException - If an I/O error occurs from the InputStream.
	 */
	private void readMetadataBlocks() throws IOException {
		if (this.metadataEndPosition != -1) {
			return; // Meta data has already been read.
		}
		
		boolean isLast = false;
		while (!isLast) {
			boolean last = input.readUnsignedInt(1) != 0;
			int type = input.readUnsignedInt(7);
			int length = input.readUnsignedInt(24);
			
			byte[] data = new byte[length];
			input.readAll(data);
			
			if (type == FlacMetadataBlockType.STREAMINFO.getType()) {
				if (streamInfo != null) {
					throw new IOException(); // TODO: Change to use my own exception.
				}
				streamInfo = new StreamInfo(data);
			} else {
				
				if (type == FlacMetadataBlockType.PADDING.getType()) {
					
				} else if (type == FlacMetadataBlockType.APPLICATION.getType()) {
					
				} else if (type == FlacMetadataBlockType.SEEKTABLE.getType()) {
					//if (seekTable != null) {
					//	throw new IOException(); //TODO: Change to use my own exception.
					//}
					//seekTable = new SeekTable(data);
				} else if (type == FlacMetadataBlockType.VORBIS_COMMENT.getType()) {
					if (this.vorbisComments != null) {
						throw new IOException(); //TODO: Change to use my own exceptions.
					}
					this.vorbisComments = new VorbisComments(data);
				} else if (type == FlacMetadataBlockType.CUESHEET.getType()) {
					
				} else if (type == FlacMetadataBlockType.PICTURE.getType()) {
					
				}
			}
			
			
			if (last) {
				isLast = true;
				this.metadataEndPosition = this.input.getPosition();
				frameReader = new FrameReader(input, streamInfo.getBitsPerSample());
			}
		}
	}
	
	/**
	 * Reads and decodes the next block of audio data into the specified samples buffer,
	 * returning the number of samples in the block.
	 * 
	 * @param samples - The buffer into which the data is read.
	 * @param off - The offset of where to read from.
	 * 
	 * @return - Zero if the read started at the end of the stream or a value in the 
	 * 	range 1 - 65536 if a valid block was read.
	 * 
	 * @throws IOException - TODO
	 */
	public int readAudioBlock(int[][] samples, int off) throws IOException {
		if (this.frameReader == null) {
			throw new IllegalStateException("Metadata has not been read yet.");
		}
		
		Frame frame = frameReader.readFrame(samples, off);
		
		if (frame == null) {
			return 0;
		} else {
			return frame.getBlockSize();
		}
	}
	
	public int seekAndReadAudioBlock(long position, int[][] samples, int off) throws IOException {
		return 0;
	}
	
	private long[] getBestSeekPoint(long position) {
		return null;
	}
	
	private long [] seekBySyncAndDecode(long posirion) throws IOException {
		return null;
	}
	
	private long[] getNextFrameOffsets(long position) throws IOException {
		return null;
	}
	
	private long getSampleOffset(Frame frame) {
		
		return 0;
	}
	
	public void close() throws IOException {
		if (this.input != null) {
			this.streamInfo = null;
			this.vorbisComments = null;
			this.frameReader = null;
			this.input.close();
			this.input = null;
		}
	}

	/**
	 * @return the streamInfo
	 */
	public StreamInfo getStreamInfo() {
		return streamInfo;
	}

	/**
	 * @return the vorbisComments
	 */
	public VorbisComments getVorbisComments() {
		return vorbisComments;
	}

	/**
	 * @param streamInfo the streamInfo to set
	 */
	public void setStreamInfo(StreamInfo streamInfo) {
		this.streamInfo = streamInfo;
	}

	/**
	 * @param vorbisComments the vorbisComments to set
	 */
	public void setVorbisComments(VorbisComments vorbisComments) {
		this.vorbisComments = vorbisComments;
	}
}
