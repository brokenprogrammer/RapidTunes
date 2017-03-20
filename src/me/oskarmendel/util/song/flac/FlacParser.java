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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import me.oskarmendel.util.BinaryUtil;

/**
 * Class to parse files of the Flac format retrieving data such as meta tags.
 * Implemented using the formatting for Flac files represented here:
 * https://xiph.org/flac/format.html
 * TODO: Parse length of song.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FlacParser.java
 */
public class FlacParser {
	
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
	 * Parses target File and populates the FlacFile from the parameters
	 * with data from the file. If the file is not a flac file an 
	 * IllegalArgumentsException will be thrown.
	 * 
	 * @param file - Target file to read using an InputStream.
	 * @param flac - Target FlacFile object to populate with data.
	 * @throws FileNotFoundException - If the file does not exist, is a directory, or cannot be opened.
	 * @throws IOException  - if an I/O error occurs from the InputStream.
	 */
	public static void parseFlacFile(File file, FlacFile flac) throws IOException, FileNotFoundException {
		//This constructor populates the FlacFile with content needed.
		//Using private methods that handle the individual parsing. 
		if(!isFlacFile(file)) {
			throw new IllegalArgumentException("File type is not recognized as flac!");
		}
		
		InputStream input = new BufferedInputStream(new FileInputStream(file));
		
		//Read the first block which is a 32 bit FLAC stream marker.
		byte[] streamTag = new byte[4];
		BinaryUtil.readBytes(input, streamTag);
		
		readMetadataBlocks(input, flac);
		
		input.close();
	}
	
	/**
	 * Checks if the target file is a Flac file.
	 * 
	 * @param file - Target file to preform the control on.
	 * @throws FileNotFoundException - If the file does not exist, is a directory, or cannot be opened.
	 * @throws IOException  - if an I/O error occurs from the InputStream.
	 * @return True if the file is a Flac file, false otherwise.
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
	 * Performs the read on the files meta data blocks and acts depending on which 
	 * block it is currently reading. 
	 * 
	 * @param input - InputStream of the file to read.
	 * @param flac - Target FlacFile object to populate with data.
	 * @throws IOException - if an I/O error occurs from the InputStream.
	 */
	private static void readMetadataBlocks(InputStream input, FlacFile flac) throws IOException {
		boolean finished = false;
		
		while (!finished) {
			int blockInt = input.read();
			byte blockType = BinaryUtil.intToByte(blockInt);
			
			//Check the first bit in the block header to retrieve the 
			//Last-metadata-block flag. If the flag is 1 then it is the last block.
			if (BinaryUtil.getBitAtBE(blockType, 0) == 0) {
				byte[] len = new byte[3]; //length of the data to be parsed.
				BinaryUtil.readBytes(input, len);
				
				//Parse the length of the data to an integer so array can be constructed.
				int dataLength = (int)BinaryUtil.addBytesToIntBE(len[0], len[1], len[2]);
				
				//Create array to store data and read the data into it.
				byte[] data =  new byte[dataLength];
				BinaryUtil.readBytes(input, data);
				
				//Act depending on what type of block it is.
				switch (blockType) {
				case STREAMINFO:
					readStreamInfo(input, flac, data);
					break;
				case PADDING:
					readUnhandledMetadata();
					break;
				case APPLICATION:
					readUnhandledMetadata();
					break;
				case SEEKTABLE:
					readUnhandledMetadata();
					break;
				case VORBIS_COMMENT:
					readVorbisComments(input, flac, data);
					break;
				case CUESHEET:
					readUnhandledMetadata();
					break;
				case PICTURE:
					readUnhandledMetadata();
					break;
				}
			} else {
				finished = true;
			}
		}
	}
	
	/**
	 * Helper method to read the contents of the STREAMINFO meta data block of the flac file.
	 * 
	 * @param input - InputStream of the file to read.
	 * @param flac - Target FlacFile object to populate with data.
	 * @param data - Byte array of data connected to this meta data block.
	 */
	private static void readStreamInfo(InputStream input, FlacFile flac, byte[] data) {
		int bitOffset = 0;
		
		flac.setMinimumBlockSize(BinaryUtil.addBytesToIntBE(
      		BinaryUtil.byteToInt(data[bitOffset++]),
      		BinaryUtil.byteToInt(data[bitOffset++]))
		);
		
		flac.setMaximumBlockSize(BinaryUtil.addBytesToIntBE(
      		BinaryUtil.byteToInt(data[bitOffset++]),
      		BinaryUtil.byteToInt(data[bitOffset++]))
		);
		
		flac.setMinimumFrameSize((int)BinaryUtil.addBytesToIntBE(
      		BinaryUtil.byteToInt(data[bitOffset++]),
      		BinaryUtil.byteToInt(data[bitOffset++]),
      		BinaryUtil.byteToInt(data[bitOffset++]))
		);
		
		flac.setMaximumFrameSize((int)BinaryUtil.addBytesToIntBE(
      		BinaryUtil.byteToInt(data[bitOffset++]),
      		BinaryUtil.byteToInt(data[bitOffset++]),
      		BinaryUtil.byteToInt(data[bitOffset++]))
		);
		
		int[] lastEight = new int[8];
		for (int i = 0; i < 8; i++) {
			lastEight[i] = BinaryUtil.byteToInt(data[bitOffset+i]);
		}
		bitOffset += 8;
		
		/*
		 * This section of code is implemented using the Flac format specification.
		 * Sample Rate: 20 bits.
		 * Number of Channels 3 bits.
		 * Bits per sample: 5 bits.
		 * Number of samples: 36 bits.
		 * 
		 * This section manually reads the bytes and places them into their corresponding integer.
		 * 
		 * Example: Sample Rate is made out of 20 bits, that takes up 2 and a half byte. That means
		 * that 4 of the bits will be in the third byte. We then read the first two bytes and shifts
		 * them as many steps as the next bits takes up. For Sample rate the lowest 4 bits read from
		 * the 3rd byte takes 4 positions so the 8 bits read from the 2nd byte has to be shifted 4
		 * positions and then the 8 bits in the first byte has to be shifted 8+4 = 12 positions.
		 */
		int sampleRate = (lastEight[0]<<12) + (lastEight[1]<<4) + ((lastEight[2]&0xf0)>>4);
		int numChannels = ((lastEight[2] & 0x0e) >> 1) + 1;
		int bitsPerSample = ((lastEight[2]&0x01)<<4) + ((lastEight[3]&0xf0)>>4) + 1;
		int numberOfSamples = ((lastEight[3]&0x0f)<<30) + (lastEight[4]<<24) + 
        		(lastEight[5]<<16) + (lastEight[6]<<8) + lastEight[7];
        
        flac.setSampleRate(sampleRate);
        flac.setNumChannels(numChannels);
        flac.setBitsPerSample(bitsPerSample);
        flac.setNumSamples(numberOfSamples);
	}
	
	/**
	 * Helper method to read the contents of the STREAMINFO meta data block of the flac file.
	 * Vorbis Comment specification can be found here: https://www.xiph.org/vorbis/doc/v-comment.html
	 * 
	 * @param input - InputStream of the file to read.
	 * @param flac - Target FlacFile object to populate with data.
	 * @param data - Byte array of data connected to this meta data block.
	 */
	private static void readVorbisComments(InputStream input, FlacFile flac, byte[] data) {
		int bitOffset = 0;
		
		int vendorLength = (int) BinaryUtil.addBytesToInt4(data, bitOffset);
		bitOffset += 4;
		
		String vendorString = BinaryUtil.getBytesToString(data, bitOffset, vendorLength);
		flac.setVendor(vendorString);
		
		bitOffset+= vendorLength;
		
		int numComments = (int) BinaryUtil.addBytesToInt4(data, bitOffset);
		bitOffset += 4;
		
		//Loop through each comment and parse them to the FlacFile.
		for (int i = 0; i < numComments; i++) {
			int length = (int) BinaryUtil.addBytesToInt4(data, bitOffset);
			bitOffset += 4;
			
			String thisComment = BinaryUtil.getBytesToString(data, bitOffset, length);
			bitOffset += length;
			
			parseComment(thisComment, flac);
		}
	}
	
	/**
	 * 
	 */
	private static void readUnhandledMetadata() {
		return;
	}
	
	/**
	 * Helper method to parse the comments retrieved from reading the Vorbis comments.
	 * The comments are stored as a key value pair and this method splits the key and value 
	 * then acts depending on what key it is and stores the value within the FlacFile.
	 * 
	 * @param comment - The String comment to parse.
	 * @param flac - Target FlacFile object to populate with data.
	 */
	private static void parseComment(String comment, FlacFile flac) {
		int equals = comment.indexOf('=');
		if(equals == -1) {
			throw new IllegalArgumentException("Unable to parse comment '"+comment+"'");
		}
		
		//Split the key value pair.
		String tag = comment.substring(0, equals);
		String value = comment.substring(equals+1);
		
		//Act depending on what key for the comment.
		switch(tag) {
		case "TITLE":
			flac.setTitle(value);
			break;
		case "ALBUM":
			flac.setAlbum(value);
			break;
		case "TRACKNUMBER":
			flac.setTrackNumber(value);
			break;
		case "ARTIST":
			flac.setArtist(value);
			break;
		case "GENRE":
			flac.setGenre(value);
			break;
		case "DATE":
			flac.setDate(value);
			break;
		}
	}
}
