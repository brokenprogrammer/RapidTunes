package me.oskarmendel.util.flac;

import java.io.File;
import java.io.IOException;

import me.oskarmendel.util.song.flac.StreamInfo;
import me.oskarmendel.util.song.flac.VorbisComments;
import me.oskarmendel.util.song.flac.structure.FlacMetadataBlockType;

/**
 * 
 * @author Oskar Mendel
 * TODO: Finalize Frame & FrameReader + add docs.
 * TODO: Finish the SeekTable class.
 * TODO: Look over all classes and make members private.
 * TODO: Implement seek methods.
 */
public class FlacReader {

	private FlacInputStream input;
	public StreamInfo streamInfo;
	//public SeekTable seekTable;
	private VorbisComments vorbisComments;

	private FrameReader frameDec;

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
				frameDec = new FrameReader(input, streamInfo.getBitsPerSample());
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
		if (this.frameDec == null) {
			throw new IllegalStateException("Metadata has not been read yet.");
		}
		
		Frame frame = frameDec.readFrame(samples, off);
		
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
		
	}
}
