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
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.util.Duration;
import me.oskarmendel.player.Player;
import me.oskarmendel.song.LocalSong;
import me.oskarmendel.song.LocalSongFormat;
import me.oskarmendel.song.Song;
import me.oskarmendel.util.song.flac.StreamInfo;
import me.oskarmendel.util.song.flac.decoder.FlacReader;

/**
 * FlacPlayer that manages the playing of local flac files.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FlacPlayer.java
 */
public class FlacPlayer extends Player {	
	private LocalSong currentSong;
	private FlacPlayerThread playerThread;
		
	public FlacPlayer() {
		this.status = Status.READY;
	}
	
	@Override
	public void play() {
		if (this.getStatus() == Status.READY || this.getStatus() == Status.PAUSED) {
			if (this.playerThread.isReady && this.playerThread.isPlaying()) {
				System.out.println("Time to run");
				this.playerThread.run();
				
				this.status = Status.PLAYING;
			} else {
				//TODO: Throw exception, trying to play non existing song.
			}
		}
	}

	@Override
	public void pause() {
		if (this.getStatus() == Status.PLAYING) {
			//TODO: Pause the thread that plays the song.
			
			this.status = Status.PAUSED;
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSong(Song song) {
		if (!(song instanceof LocalSong)) {
			throw new IllegalArgumentException("Expected LocalSong.");
		}
		
		LocalSong localSong = (LocalSong)song;
		if (localSong.getSongFormat() != LocalSongFormat.FLAC) {
			throw new IllegalArgumentException("Expected LocalSong to have format FLAC.");
		}
		
		this.currentSong = localSong;
		this.playerThread = new FlacPlayerThread(this.currentSong);
		this.playerThread.setDaemon(true);
		this.playerThread.start();
		
		this.status = Status.PLAYING;
	}

	@Override
	public void seek(long seekTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReadOnlyObjectProperty<Duration> getCurrentTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVolume(int volume) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getVolume() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void dispose() {
		//TODO: Implement. Clear all objects.
	}
	
	private class FlacPlayerThread extends Thread {
		
		private boolean playing = false;
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
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public boolean isPlaying() {
			return this.playing;
		}
		
		public boolean isReady() {
			return this.isReady;
		}
	}
}
