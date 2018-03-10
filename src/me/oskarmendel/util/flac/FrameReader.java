package me.oskarmendel.util.flac;

import java.io.IOException;
import java.util.Arrays;

/**
 * 
 * @author Oskar
 *
 */
public class FrameReader {

	/**
	 * The fixed coefficients used for flac decoding.
	 * Source: https://xiph.org/flac/format.html
	 * 		Under the prediction section there is links to audiopak and shorten studies.
	 */
	private static final int [][] FIXED_COEFFICIENTS = {
			{},
			{1},
			{2, -1},
			{3, -3, 1},
			{4, -6, 4, -1}
	};
	
	private FlacInputStream input;
	private int bitsPerSample;
	
	private long[] temp0;
	private long[] temp1;		//TODO: Are theese needed?
	
	private int currentBlockSize;
	
	public FrameReader(FlacInputStream input, int bitsPerSample) {
		this.input = input;
		this.bitsPerSample = bitsPerSample;
		
		temp0 = new long[65536];	//TODO ARE NEEDED ?
		temp1 = new long[65536];
		
		this.currentBlockSize = -1;
	}
	
	//TODO Change so it returns my own frame.
	public Frame readFrame(int[][] samples, int offset) throws IOException {
		if (this.input == null) {
			throw new IllegalStateException("Input stream was invalidated.");
		}
		
		if (this.currentBlockSize != -1) {
			throw new IllegalStateException("Multiples calls using the same reader was made.");
		}
		
		if (samples == null) {
			throw new IllegalArgumentException("Samples buffer was null.");
		}
		
		// Parsing Frame header
		long start = input.getPosition();
		Frame frame = Frame.readFrame(input);
		
		if (frame == null) {
			return null;
		}
		
		if (frame.getBitsPerSample() != -1 && frame.getBitsPerSample() != this.bitsPerSample) {
			throw new IOException(); // TODO: make my own exception for this.
		}
		
		// Validate the arguments
		this.currentBlockSize = frame.getBlockSize();
		if (offset < 0 || offset > samples[0].length) {
			throw new IndexOutOfBoundsException();
		}
		if (samples.length < frame.getNumberChannels()) {
			throw new IllegalArgumentException("Samples buffer is too small for the number of channels.");
		}
		if (offset > samples[0].length - this.currentBlockSize) {
			throw new IndexOutOfBoundsException();
		}
		
		// Decode and read subframes
		readSubframes(this.bitsPerSample, frame.getChannelAssignment(), samples, offset);
		
		// Read the padding followed by the frame's footer
		if (this.input.readUnsignedInt((8 - this.input.getBitPosition()) % 8) != 0) {
			// Invalid padding for the bits
			throw new IOException(); // TODO: make my own exception for this.
		}
		
		int crc16 = this.input.getCrc16();
		
		if (this.input.readUnsignedInt(16) != crc16) {
			// CRC16 doesnt match.
			throw new IOException(); // TODO: make my own exception for this.
		}
		
		// Set the frame size
		long frameSize = input.getPosition() - start;
		if (frameSize < 10) {
			throw new IllegalStateException("Illegal frame size.");
		}
		if ((int)frameSize != frameSize) {
			// Frame size is too large.
			throw new IOException(); // TODO: make my own exception for this.
		}
		
		frame.setFrameSize((int)frameSize);
		this.currentBlockSize = -1;
		return frame;
	}
	
	private void readSubframes(int bitsPerSample, int channelAssignment, int[][] samples, int offset) throws IOException {
		if (bitsPerSample > 32 || bitsPerSample < 1) {
			throw new IllegalArgumentException();
		}
		if ((channelAssignment >>> 4) != 0) {
			throw new IllegalArgumentException();
		}
		
		if (channelAssignment >= 0 && channelAssignment <= 7) {
			// Handle the 1 - 8 coded channels
			
			int channels = channelAssignment + 1;
			for (int channel = 0; channel < channels; channel++) {
				readSubframe(bitsPerSample, temp0); // TODO: Remove the use of this disgusting temp0
				int[] ressultChannel = samples[channel];
				for (int i = 0; i < this.currentBlockSize; i++) {
					ressultChannel[offset + i] = checkBitsPerSample(temp0[i], bitsPerSample);
				}
			}
		} else if (channelAssignment >= 8 && channelAssignment <= 10) {
			// Handle the 8 - 10 coded channels
//			if (channelAssignment == 9) {
//				readSubframe(bitsPerSample + 1, temp0);
//				readSubframe(bitsPerSample, 	temp1);
//			} else {
//				readSubframe(bitsPerSample, 	temp0);
//				readSubframe(bitsPerSample + 1, temp1);
//			}
			readSubframe(bitsPerSample + (channelAssignment == 9 ? 1 : 0), temp0);
			readSubframe(bitsPerSample + (channelAssignment == 9 ? 0 : 1), temp1);
			
			if (channelAssignment == 8) {
				for (int i = 0; i < this.currentBlockSize; i++) {
					temp1[i] = temp0[i] - temp1[i];
				}
			} else if (channelAssignment == 9) {
				for (int i = 0; i < this.currentBlockSize; i++) {
					temp0[i] += temp1[i];
				}
			} else if (channelAssignment == 10) {
				for (int i = 0; i < this.currentBlockSize; i++) {
					long left = temp1[i];
					long right = temp0[i] - (left >> 1);
					
					temp1[i] = right;
					temp0[i] = right + left;
				}
			} else {
				throw new IllegalStateException("Invalid channel assignments was read.");
			}
			
			int[] left = samples[0];
			int[] right = samples[1];
			for (int i = 0; i < this.currentBlockSize; i++) {
				left [offset + i] = checkBitsPerSample(temp0[i], this.bitsPerSample);
				right[offset + i] = checkBitsPerSample(temp1[i], this.bitsPerSample);
			}
		} else {
			// Channel assignment was in range of 11 - 15 which is reserved.
			throw new IOException(); // TODO: make my own exception for this.
		}
	}
	
	private void readSubframe(int bitsPerSample, long[] samples) throws IOException {
		if (samples == null) {
			throw new IllegalArgumentException();
		}
		if (1 > bitsPerSample || bitsPerSample > 33) {
			throw new IllegalArgumentException();
		}
		if (samples.length < currentBlockSize) {
			throw new IllegalArgumentException();
		}
		
		// Read the subframe header fields.
		if (input.readUnsignedInt(1) != 0) {
			// Invalid padding bit..
			throw new IOException(); // TODO: make my own exception for this.
		}
		
		int subframeType = this.input.readUnsignedInt(6);
		int wastedBitsFlag = this.input.readUnsignedInt(1);
		
		if (wastedBitsFlag == 1) {
			while (this.input.readUnsignedInt(1) == 0) {
				if (wastedBitsFlag >= bitsPerSample) {
					// Wasted bits per sample exceeds bits per samples.
					throw new IOException(); // TODO: make my own exception for this.
				}
				
				wastedBitsFlag++;
			}
		}
		
		if (!(0 <= wastedBitsFlag && wastedBitsFlag <= bitsPerSample)) {
			throw new IllegalStateException("Invalid amount of wasted bits read.");
		}
		
		bitsPerSample -= wastedBitsFlag;
		
		// Read the sample data based on its subframe type.
		if (subframeType == 0) { // Constant coding
			Arrays.fill(samples, 0, this.currentBlockSize, this.input.readSignedInt(bitsPerSample));
		} else if (subframeType == 1) { // Verbatim coding
			for (int i = 0; i < this.currentBlockSize; i++) {
				samples[i] = this.input.readSignedInt(bitsPerSample);
			}
		} else if (subframeType >= 8 && subframeType <= 12) {
			readFixedPredictionSubframe(subframeType - 8, bitsPerSample, samples);
		} else if (subframeType >= 32 && subframeType <= 63) {
			readLinearPredictionSubframe(subframeType - 31, bitsPerSample, samples);
		} else {
			// Reserved subframe type..
			throw new IOException(); // TODO: make my own exception for this.
		}
		
		// Add the trailing wasted bits to the samples.
		if (wastedBitsFlag > 0) {
			for (int i = 0; i < this.currentBlockSize; i++) {
				samples[i] <<= wastedBitsFlag;
			}
		}
	}
	
	/**
	 * Checks that the value is a signed bit integer and either returns an integer
	 * which is a downcast from the long value or an exception if its out of range.
	 * 
	 * @param value - Value to check.
	 * @param bitsPerSample - Target bits per sample.
	 * 
	 * @return - Value downcasted to an integer.
	 */
	private static int checkBitsPerSample(long value, int bitsPerSample) {
		if ((-(value >> (bitsPerSample - 1)) | 1) != 1) {
			throw new IllegalArgumentException("Value: " + value + " is not a signed " + bitsPerSample + "-bit value.");
		} else {
			return (int)value;
		}
	}
	
	private void readFixedPredictionSubframe(int predictionOrder, int bitsPerSample, long[] samples) throws IOException {
		if (samples == null) {
			throw new IllegalArgumentException();
		}
		if (samples.length < this.currentBlockSize) {
			throw new IllegalArgumentException();
		}
		if (1 > bitsPerSample || bitsPerSample > 33) {
			throw new IllegalArgumentException();
		}
		if (predictionOrder < 0 || predictionOrder >= FIXED_COEFFICIENTS.length) {
			throw new IllegalArgumentException();
		}
		if (predictionOrder > this.currentBlockSize) {
			// Fixed prediction order exceeds block size.
			throw new IOException(); // TODO: make my own exception for this.
		}
		
		for (int i = 0; i < predictionOrder; i++) {
			samples[i] = input.readSignedInt(bitsPerSample);
		}
		
		readSubframeResiduals(predictionOrder, samples);
		handleLPC(FIXED_COEFFICIENTS[predictionOrder], bitsPerSample, 0, samples);
	}
	
	private void readLinearPredictionSubframe(int lpcOrder, int bitsPerSample, long[] samples) throws IOException {
		if (samples == null) {
			throw new IllegalArgumentException();
		}
		if (samples.length < this.currentBlockSize) {
			throw new IllegalArgumentException();
		}
		if (1 > bitsPerSample || bitsPerSample > 33) {
			throw new IllegalArgumentException();
		}
		if (lpcOrder < 1 || lpcOrder > 32) {
			throw new IllegalArgumentException();
		}
		if (lpcOrder > this.currentBlockSize) {
			// LPC order exceeds block size..
			throw new IOException(); // TODO: make my own exception for this.
		}
		
		// Read non Rice encoded samples.
		for (int i = 0; i < lpcOrder; i++) {
			samples[i] = this.input.readSignedInt(bitsPerSample);
		}
		
		// Read LPC coefficients
		int predictorPrecision = this.input.readUnsignedInt(4) + 1;
		if (predictorPrecision == 16) {
			// invalid predictor precision value.
			throw new IOException(); // TODO: make my own exception for this.
		}
		
		int predictorCoefficientShift = this.input.readSignedInt(5);
		if (predictorCoefficientShift < 0) {
			// Invalid predictor coefficient shift
			throw new IOException(); // TODO: make my own exception for this.
		}
		
		int[] coefficients = new int[lpcOrder];
		for (int i = 0; i < coefficients.length; i++) {
			coefficients[i] = this.input.readSignedInt(predictorPrecision);
		}
		
		readSubframeResiduals(lpcOrder, samples);
		handleLPC(coefficients, bitsPerSample, predictorCoefficientShift, samples);
	}
	
	private void readSubframeResiduals(int start, long[] samples) throws IOException {
		if (samples == null) {
			throw new IllegalArgumentException();
		}
		if (samples.length < this.currentBlockSize) {
			throw new IllegalArgumentException();
		}
		if (start < 0 || start > this.currentBlockSize) {
			throw new IllegalArgumentException();
		}
		
		int residualCodingMethod = this.input.readUnsignedInt(2);
		if (residualCodingMethod >= 2) {
			// Reserved residual coding method
			throw new IOException(); // TODO: make my own exception for this.
		}
		
		if (!(residualCodingMethod == 0 || residualCodingMethod == 1)) {
			throw new IllegalStateException("Invalid residual coding method.");
		}
		
		//TODO Use if statements here
		int residualParameterBits = residualCodingMethod == 0 ? 4 : 5;
		int escapeCode = residualCodingMethod == 0 ? 0xF : 0x1F;
		int partitionOrder = this.input.readUnsignedInt(4);
		int numberPartitions = 1 << partitionOrder;
		
		if (this.currentBlockSize % numberPartitions != 0) {
			// Block size is not divisible by the number of rice partitions.
			throw new IOException(); // TODO: make my own exception for this.
		}
		
		for (int i = this.currentBlockSize >>> partitionOrder, partitionEnd = i, sampleIndex = start;
				partitionEnd <= this.currentBlockSize; partitionEnd += i) {
			int encodingParameter = this.input.readUnsignedInt(residualParameterBits);
			
			if (encodingParameter == escapeCode) {
				int bits = this.input.readUnsignedInt(5);
				for (; sampleIndex < partitionEnd; sampleIndex++) {
					samples[sampleIndex] = this.input.readSignedInt(bits);
				}
			} else {
				this.input.readRiceSignedInts(encodingParameter, samples, sampleIndex, partitionEnd);
				sampleIndex = partitionEnd;
			}
		}
	}
	
	private void handleLPC(int[] coefficients, int bitsPerSample, int coefficientShift, long[] samples) {
		if (samples == null) {
			throw new IllegalArgumentException();
		}
		if (samples.length < this.currentBlockSize) {
			throw new IllegalArgumentException();
		}
		if (coefficients == null) {
			throw new IllegalArgumentException();
		}
		if (1 > bitsPerSample || bitsPerSample > 33) {
			throw new IllegalArgumentException();
		}
		if (coefficientShift < 0 || coefficientShift > 63) {
			throw new IllegalArgumentException();
		}
		
		long lowBound = (-1) << (bitsPerSample - 1);
		long uppBound = -(lowBound + 1);
		
		for (int i = coefficients.length; i < this.currentBlockSize; i++) {
			long s = 0;
			for (int j = 0; j < coefficients.length; j++) {
				s += samples[i - 1 - j] * coefficients[j];
			}
			s = samples[i] + (s >> coefficientShift);
			
			if (s < lowBound || s > uppBound) {
				//TODO: LPC result exceeded bit per sample. Make my own exception type here.
			}
			
			samples[i] = s;
		}	
	}
}
