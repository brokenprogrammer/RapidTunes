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

public class FlacPlayer extends Player {	
	private LocalSong currentSong;
	
	private FlacReader reader;
	private StreamInfo streamInfo;
	private AudioFormat format;
	private DataLine.Info info;
	private SourceDataLine line;
	
	private int bytesPerSample;
	private long startTime;
	private int[][] samples;
	private byte[] sampleBytes;
		
	public FlacPlayer() {
		this.status = Status.READY;
	}
	
	@Override
	public void play() {
		if (this.getStatus() == Status.READY || this.getStatus() == Status.PAUSED) {
			if (this.reader != null && this.streamInfo != null) { //TODO: Check samples, bytesPerSample, sampleBytes..
				//TODO: Start thread that plays the song.
				
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
		
		//TODO: Loading of flac file.
		try {
			FlacReader reader = new FlacReader(new File(this.currentSong.getPath()));
			this.streamInfo = reader.getStreamInfo();
			
			if (this.streamInfo.getNumChannels() == 0) {
				throw new IllegalArgumentException("Unknwon audio length.");
			}
			
			this.format = new AudioFormat(this.streamInfo.getSampleRate(),
										  this.streamInfo.getBitsPerSample(),
										  this.streamInfo.getNumChannels(),
										  true, false);
			
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, this.format);
			SourceDataLine line = (SourceDataLine)AudioSystem.getLine(info);
			line.open(this.format);
			line.start();
			
			this.bytesPerSample = this.streamInfo.getBitsPerSample() / 8;
			this.startTime = line.getMicrosecondPosition();
			this.samples = new int[this.streamInfo.getNumChannels()][65536];
			this.sampleBytes = new byte[65536 * this.streamInfo.getNumChannels() * this.bytesPerSample];
		} catch (Exception e) {
			//TODO: Proper error handling.
			e.printStackTrace();
		}
		
		this.status = Status.PLAYING;
		this.play();
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
}
