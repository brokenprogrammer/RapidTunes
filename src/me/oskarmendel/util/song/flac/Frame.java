package me.oskarmendel.util.flac;

import java.io.IOException;

/**
 * Frame object with all the frame fields for a flac frame.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Frame.java
 */
public class Frame {
	
	/**
	 * The different block size codes supported by flac.
	 */
	private static final int[] BLOCK_SIZE_CODES = {
		-1, 192, 576, 1152, 2304, 4608, -1, -1, 256, 
		512, 1024, 2048, 4096, 8192, 16384, 32768
	};
	
	/**
	 * The bits per sample sizes or samples sizes supported by flac.
	 */
	private static final int[] BITS_PER_SAMPLE_CODES = {
		-1, 8, 12, -1, 16, 20, 24, -1 
	};
	
	/**
	 * The sample rate codes supported by flac.
	 */
	private static final int[] SAMPLE_RATE_CODES = {
		-1, 88200, 176400, 192000, 8000, 16000, 
		22050, 24000, 32000, 44100, 48000, 96000
	};
	
	/**
	 * The index for the subframe where the first subframe has index 0 and future
	 * frame increments this value.
	 */
	private int frameIndex;
	
	/**
	 * The offset of this frame in respect to the input stream.
	 */
	private long sampleOffset;
	
	/**
	 * The number of channels for the audio this frame is using.
	 */
	private int numberChannels;
	
	/**
	 * The number of independent channels for the frame, determines if 
	 * the frame is using stereo, mono etc..
	 */
	private int channelAssignment;
	
	/**
	 * The number of samples per channel in this frame.
	 */
	private int blockSize;
	
	/**
	 * The sample rate for this frame specified in hertz (Hz).
	 */
	private int sampleRate;
	
	/**
	 * The bits per sample for this stream of the sample size.
	 */
	private int bitsPerSample;
	
	/**
	 * The size of this frame in bytes.
	 */
	private int frameSize;
	
	/**
	 * Constructs a new empty frame invalidating all its members.
	 */
	public Frame() {
		frameIndex = -1;
		sampleOffset = -1;
		numberChannels = -1;
		channelAssignment = -1;
		blockSize = -1;
		sampleRate = -1;
		bitsPerSample = -1;
		frameSize = -1;
	}
	
	/**
	 * Reads a new frame header from the specified input stream.
	 * 
	 * @param input - Input stream to read frame header form.
	 * 
	 * @return - Frame header populated with read data.
	 * 
	 * @throws IOException - On input stream failure.
	 */
	public static Frame readFrame (FlacInputStream input) throws IOException {
		input.resetCrcs();
		
		int temp = input.readByte();
		if (temp == -1) {
			return null;
		}
		
		Frame frame = new Frame();
		frame.setFrameSize(-1);
		
		// Read sync code
		int syncCode = (temp << 6) | input.readUnsignedInt(6);
		if (syncCode != 0x3FFE) {
			// Expected sync code but was not found.
			throw new IOException(); //TODO Make my own exception here.
		}
		
		// Read frame fields.
		if (input.readUnsignedInt(1) != 0) {
			// Reserved bit.
			throw new IOException(); //TODO Make my own exception here.
		}
		
		int blockingStrategy = input.readUnsignedInt(1);
		int blockSize = input.readUnsignedInt(4);
		int sampleRate = input.readUnsignedInt(4);
		int channelAssignment = input.readUnsignedInt(4);
		
		frame.setChannelAssignments(channelAssignment);
		
		if (channelAssignment < 8) {
			frame.setNumberChannels(channelAssignment + 1);
		} else if (channelAssignment >= 8 && channelAssignment <= 10) {
			frame.setNumberChannels(2);
		} else {
			// Reserved bit.
			throw new IOException(); //TODO Make my own exception here.
		}
		
		frame.setBitsPerSample(readBitsPerSample(input.readUnsignedInt(3)));
		
		if (input.readUnsignedInt(1) != 0) {
			// Reserved bit.
			throw new IOException(); //TODO Make my own exception here.
		}
		
		long position = readUtf8Int(input);
		
		if (blockingStrategy == 0) {
			if ((position >>> 31) != 0) {
				// Frame index is too large.
				throw new IOException(); //TODO Make my own exception here.
			}
			frame.setFrameIndex((int)position);
			frame.setSampleOffset(-1);
		} else if (blockingStrategy == 1) {
			frame.setSampleOffset(position);
			frame.setFrameIndex(-1);
		} else {
			throw new IllegalStateException("Invalid blocking strategy.");
		}
		
		frame.setBlockSize(readBlockSize(blockSize, input));
		frame.setSampleRate(readSampleRate(sampleRate, input));
		
		int crc8 = input.getCrc8();
		if (input.readUnsignedInt(8) != crc8) {
			// CRC 8 missmatch
			throw new IOException(); //TODO Make my own exception here.
		}
		
		return frame;
	}

	/**
	 * Reads a UTF-8 coded sample number used for the blocksize.
	 * 
	 * @param input - Input stream to read bytes from.
	 * 
	 * @return - 36 bit unsigned integer form read bytes.
	 * 
	 * @throws IOException - On input stream failure.
	 */
	private static long readUtf8Int(FlacInputStream input) throws IOException {
		int h = input.readUnsignedInt(8);
		int n = Integer.numberOfLeadingZeros(~(h << 24));
		
		if (n == 0) {
			return h;
		} else if (n == 1 || n == 8) {
			// Invalid UTF8 number
			throw new IOException(); //TODO Make my own exception here.
		} else {
			long res = h & (0x7F >>> n);
			for (int i = 0; i < n - 1; i++) {
				int t = input.readUnsignedInt(8);
				if ((t & 0xC0) != 0x80) {
					// Invalid UTF8 number
					throw new IOException(); //TODO Make my own exception here.
				}
				res = (res << 6) | (t & 0x3F);
			}
			
			if ((res >>> 36) != 0) {
				throw new IllegalStateException();
			}
			
			return res;
		}
	}
	
	/**
	 * Reads the blockSize based on the values previously read from the input stream and
	 * translates it to the actual block size of this frame.
	 * 
	 * @param blockSize - Previously read blockSize value.
	 * 
	 * @param input - Input stream to read data from.
	 * 
	 * @return - The block size this frame is using.
	 * 
	 * @throws IOException - On input stream failure.
	 */
	private static int readBlockSize(int blockSize, FlacInputStream input) throws IOException {
		if ((blockSize >>> 4) != 0) {
			throw new IllegalArgumentException();
		}
		
		switch (blockSize) {
		case 0: 
			throw new IOException("Reserved block size."); //TODO Make my own exception.
		case 6: 
			return input.readUnsignedInt(8) + 1;
		case 7:
			return input.readUnsignedInt(16) + 1;
		default:
			int res = BLOCK_SIZE_CODES[blockSize];
			
			if (res < 1 || res > 65536) {
				throw new IllegalStateException("Invalid block size read.");
			}
			
			return res;
		}
	}
	
	/**
	 * Reads the sample rate based on the previously read sample rate value and
	 * translates it to a valid sample rate for this frame.
	 * 
	 * @param sampleRate - Previously read sampleRate value.
	 * @param input - Input stream to read data from.
	 * 
	 * @return - The sample rate for this frame.
	 * 
	 * @throws IOException - On input stream failure.
	 */
	private static int readSampleRate(int sampleRate, FlacInputStream input) throws IOException {
		if ((sampleRate >>> 4) != 0) {
			throw new IllegalArgumentException();
		}
		
		switch (sampleRate) {
		case 0: 
			return -1;
		case 12:
			return input.readUnsignedInt(8);
		case 13:
			return input.readUnsignedInt(16);
		case 14:
			return input.readUnsignedInt(16) * 10;
		case 15:
			throw new IOException("Invalid sample rate"); //TODO Make my own exception.
		default:
			int res = SAMPLE_RATE_CODES[sampleRate];
			
			if (res < 1 || res > 65536) {
				throw new IllegalStateException("Invalid sample rate read.");
			}
			
			return res;
		}
		
	}
	
	/**
	 * Reads the bits per samples based on the previously read value and
	 * translates it to a valid bits per sampel for this frame.
	 * 
	 * @param bitsPerSample - Previously read bits per sample value.
	 * 
	 * @return - The bits per sample for this frame.
	 */
	private static int readBitsPerSample(int bitsPerSample) {
		if ((bitsPerSample >>> 3) != 0) {
			throw new IllegalArgumentException();
		}
		
		if (bitsPerSample == 0) {
			return -1;
		} else {
			int res = BITS_PER_SAMPLE_CODES[bitsPerSample];
			
			if (res == -1) {
				throw new IllegalStateException("Reserved bits per sample size."); // TODO: Make this my own exception
			}
			
			if (res < 1 || res > 32) {
				throw new IllegalStateException("Invalid bits per sample read.");
			}
			
			return res;
		}
	}
	
	/**
	 * @return the frameIndex
	 */
	public int getFrameIndex() {
		return frameIndex;
	}

	/**
	 * @return the sampleOffset
	 */
	public long getSampleOffset() {
		return sampleOffset;
	}

	/**
	 * @return the numberChannels
	 */
	public int getNumberChannels() {
		return numberChannels;
	}

	/**
	 * @return the channelAssignment
	 */
	public int getChannelAssignment() {
		return channelAssignment;
	}

	/**
	 * @return the blockSize
	 */
	public int getBlockSize() {
		return blockSize;
	}

	/**
	 * @return the sampleRate
	 */
	public int getSampleRate() {
		return sampleRate;
	}

	/**
	 * @return the bitsPerSample
	 */
	public int getBitsPerSample() {
		return bitsPerSample;
	}

	/**
	 * @return the frameSize
	 */
	public int getFrameSize() {
		return frameSize;
	}

	/**
	 * @param frameIndex the frameIndex to set
	 */
	public void setFrameIndex(int frameIndex) {
		this.frameIndex = frameIndex;
	}

	/**
	 * @param sampleOffset the sampleOffset to set
	 */
	public void setSampleOffset(long sampleOffset) {
		this.sampleOffset = sampleOffset;
	}

	/**
	 * @param numberChannels the numberChannels to set
	 */
	public void setNumberChannels(int numberChannels) {
		this.numberChannels = numberChannels;
	}

	/**
	 * @param channelAssignments the channelAssignments to set
	 */
	public void setChannelAssignments(int channelAssignment) {
		this.channelAssignment = channelAssignment;
	}

	/**
	 * @param blockSize the blockSize to set
	 */
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	/**
	 * @param sampleRate the sampleRate to set
	 */
	public void setSampleRate(int sampleRate) {
		this.sampleRate = sampleRate;
	}

	/**
	 * @param bitsPerSample the bitsPerSample to set
	 */
	public void setBitsPerSample(int bitsPerSample) {
		this.bitsPerSample = bitsPerSample;
	}

	/**
	 * @param frameSize the frameSize to set
	 */
	public void setFrameSize(int frameSize) {
		this.frameSize = frameSize;
	}
}
