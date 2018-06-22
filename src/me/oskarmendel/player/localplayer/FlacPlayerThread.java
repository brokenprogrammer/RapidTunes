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

package me.oskarmendel.player.localplayer;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

import me.oskarmendel.song.LocalSong;
import me.oskarmendel.util.song.flac.StreamInfo;
import me.oskarmendel.util.song.flac.decoder.FlacReader;

/**
 * FlacPlayerThread which contains the implementation for the thread that plays the sound
 * of the FlacPlayer.
 * 
 * TODO: Review if fields should be volatile and methods synchronized or not.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FlacPlayerThread.java
 */
public class FlacPlayerThread extends Thread {
	private volatile boolean playing = false;
	private boolean isReady = false;
	
	private FlacReader reader;
	private StreamInfo streamInfo;
	private AudioFormat format;
	private DataLine.Info info;
	private SourceDataLine line;
	
	private int bytesPerSample;
	private long startTime;
	private int[][] samples;
	private byte[] sampleBytes;
	
	public FlacPlayerThread(LocalSong song) {
		try {
			//TODO: Use the actual localsong filepath here.
			this.reader = new FlacReader(new File("./demo/Jimmy Pengiun - Untitled Star.flac"));
			this.streamInfo = reader.getStreamInfo();
			
			if (this.streamInfo.getNumChannels() == 0) {
				throw new IllegalArgumentException("Unknwon audio length.");
			}
			
			this.format = new AudioFormat(this.streamInfo.getSampleRate(),
										  this.streamInfo.getBitsPerSample(),
										  this.streamInfo.getNumChannels(),
										  true, false);
			
			this.info = new DataLine.Info(SourceDataLine.class, this.format);
			this.line = (SourceDataLine)AudioSystem.getLine(info);
			this.line.open(this.format);
			this.line.start();
			
			this.bytesPerSample = this.streamInfo.getBitsPerSample() / 8;
			this.startTime = line.getMicrosecondPosition();
			this.samples = new int[this.streamInfo.getNumChannels()][65536];
			this.sampleBytes = new byte[65536 * this.streamInfo.getNumChannels() * this.bytesPerSample];
		} catch (Exception e) {
			//TODO: Proper error handling.
			e.printStackTrace();
		}
		
		this.isReady = true;
		this.playing = true;
	}
	
	public void run() {
		try {
			//TODO: Should this be a while(true) ?
			while (true) {
				while (this.playing) {
					int blockSamples = this.reader.readAudioBlock(samples, 0);
					int sampleBytesLen = 0;
					for (int index = 0; index < blockSamples; index++) {
						for (int channel = 0; channel < this.streamInfo.getNumChannels(); channel++) {
							int value = this.samples[channel][index];
							for (int i = 0; i < this.bytesPerSample; i++, sampleBytesLen++) {
								this.sampleBytes[sampleBytesLen] = (byte)(value >>> (i << 3));
							}
						}
					}
					this.line.write(this.sampleBytes, 0, sampleBytesLen);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO: There is a delay when changing volume and for the change to take effect.
	//TODO: There may be a way to solve this by changing the size of the buffer for the SourceDataLine?
	public synchronized void setGain(int percent) {
		if (this.line.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
			FloatControl control = (FloatControl)this.line.getControl(FloatControl.Type.MASTER_GAIN);
			float volume = (percent / 100f);
			
			control.setValue((float) ( 20 * Math.log10(volume <= 0.0 ? 0.0000 : volume)));
		} else {
			System.out.println("COULDNT FIND VOLUME CONTROL.");
		}
	}
	
	public synchronized void play() {
		this.playing = true;
	}
	
	public synchronized void pause() {
		this.playing = false;
	}
	
	public synchronized boolean isPlaying() {
		return this.playing;
	}
	
	public synchronized boolean isReady() {
		return this.isReady;
	}
}
