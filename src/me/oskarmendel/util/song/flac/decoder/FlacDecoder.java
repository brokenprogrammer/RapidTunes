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

package me.oskarmendel.util.song.flac.decoder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import me.oskarmendel.util.BinaryUtil;
import me.oskarmendel.util.song.flac.FlacFile;
import me.oskarmendel.util.song.flac.SeekTable;
import me.oskarmendel.util.song.flac.StreamInfo;
import me.oskarmendel.util.song.flac.VorbisComments;
import me.oskarmendel.util.song.flac.structure.FlacMetadataBlockType;

/**
 * Class to decode files using the Flac format.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FlacDecoder.java
 */
public class FlacDecoder {
	
	private File file;
	private FlacFile flacFile;
	private StreamInfo streamInfo;
	private SeekTable seekTable;
	private VorbisComments vorbisComments;
	
	private FlacDecoder() {
		
	}
	
	/**
	 * 
	 * @param file
	 * @param flac
	 * @throws IOException 
	 */
	public FlacDecoder(File file, FlacFile flacFile) throws IOException {
		this.file = file;
		this.flacFile = flacFile;
		
		if (!isFlacFile(this.file)) {
			throw new IllegalArgumentException("File type is not recognized as flac!");
		}
		
		InputStream input = new BufferedInputStream(new FileInputStream(this.file));
		
		//Read the first block which is a 32 bit FLAC stream marker.
		byte[] streamTag = new byte[4];
		BinaryUtil.readBytes(input, streamTag);
		
		readMetadataBlocks(input);
		
		input.close();
	}
	

	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException 
	 */
	public static boolean isFlacFile(File file) throws IOException, FileNotFoundException {
		if(!file.exists()){
			throw new IllegalArgumentException("Specified file doesn't exist!");
		}
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
	
	/**
	 * 
	 * @param input
	 * @throws IOException
	 */
	private void readMetadataBlocks(InputStream input) throws IOException {
		boolean finished = false;
		
		while (!finished) {
			int blockInt = input.read();
			byte blockType = BinaryUtil.intToByte(blockInt);
			
			if (BinaryUtil.getBitAtBE(blockType,  0) == 0) {
				byte[] len = new byte[3];
				BinaryUtil.readBytes(input, len);
				
				//Parse the length of the data to an integer so array can be constructed.
				int dataLength = (int)BinaryUtil.addBytesToIntBE(len[0], len[1], len[2]);
				
				//Create array to store data and read the data into it.
				byte[] data =  new byte[dataLength];
				BinaryUtil.readBytes(input, data);
				
				//Act depending on what type of block it is.
				if (blockType == FlacMetadataBlockType.STREAMINFO.getType()) {
					this.streamInfo = new StreamInfo(data);
				} else if (blockType == FlacMetadataBlockType.PADDING.getType()) {
					
				} else if (blockType == FlacMetadataBlockType.APPLICATION.getType()) {
					
				} else if (blockType == FlacMetadataBlockType.SEEKTABLE.getType()) {
					this.seekTable = new SeekTable(data);
				} else if (blockType == FlacMetadataBlockType.VORBIS_COMMENT.getType()) {
					this.vorbisComments = new VorbisComments(data);
				} else if (blockType == FlacMetadataBlockType.CUESHEET.getType()) {
					
				} else if (blockType == FlacMetadataBlockType.PICTURE.getType()) {
					
				}
				
			} else {
				finished = true;
				
				// Metadata end?
			}
		}
	}
	
	public int readAudioBlock(int[][] samples, int offset) {
		
		return 0;
	}
}
