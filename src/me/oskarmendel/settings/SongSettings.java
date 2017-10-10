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

package me.oskarmendel.settings;

import java.util.Properties;

/**
 * SongSettings class that stores and controls settings handling
 * songs and the playing of songs.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SongSettings.java
 */
public class SongSettings extends Settings {
	
	private static final String PATH = "settings/song.properties";
	
	private static final boolean DEFAULT_CROSSFADE = false;
	private static final int 	 DEFAULT_CROSSFADE_SECONDS = 6;
	
	private static final boolean DEFAULT_SOUNDENHANCER = false;
	private static final int	 DEFAULT_SOUNDENHANCER_VALUE = 50;
	
	private static final PlaybackQuality DEFAULT_PLAYBACKQUALITY = PlaybackQuality.BEST_AVAILABLE;
	
	/**
	 * 
	 */
	public enum PlaybackQuality {
		BAD,
		GOOD,
		BEST_AVAILABLE
	}
	
	private boolean crossfade;					//
	private int 	crossfadeSeconds;			//
	
	private boolean soundEnhancer;				//
	private int 	soundEnhancerValue;			//
	
	private PlaybackQuality playbackQuality;	//
	
	/**
	 * 
	 */
	public SongSettings() {
		super();
	}
	
	/**
	 * 
	 * @param properties
	 */
	public SongSettings(Properties properties) {
		super();
	}
	
	/**
	 * @return the crossfade
	 */
	public boolean isCrossfade() {
		return crossfade;
	}

	/**
	 * @return the crossfadeSeconds
	 */
	public int getCrossfadeSeconds() {
		return crossfadeSeconds;
	}

	/**
	 * @return the soundEnhancer
	 */
	public boolean isSoundEnhancer() {
		return soundEnhancer;
	}

	/**
	 * @return the soundEnhancerValue
	 */
	public int getSoundEnhancerValue() {
		return soundEnhancerValue;
	}

	/**
	 * @return the playbackQuality
	 */
	public PlaybackQuality getPlaybackQuality() {
		return playbackQuality;
	}

	/**
	 * @param crossfade the crossfade to set
	 */
	public void setCrossfade(boolean crossfade) {
		this.crossfade = crossfade;
	}

	/**
	 * @param crossfadeSeconds the crossfadeSeconds to set
	 */
	public void setCrossfadeSeconds(int crossfadeSeconds) {
		this.crossfadeSeconds = crossfadeSeconds;
	}

	/**
	 * @param soundEnhancer the soundEnhancer to set
	 */
	public void setSoundEnhancer(boolean soundEnhancer) {
		this.soundEnhancer = soundEnhancer;
	}

	/**
	 * @param soundEnhancerValue the soundEnhancerValue to set
	 */
	public void setSoundEnhancerValue(int soundEnhancerValue) {
		this.soundEnhancerValue = soundEnhancerValue;
	}

	/**
	 * @param playbackQuality the playbackQuality to set
	 */
	public void setPlaybackQuality(PlaybackQuality playbackQuality) {
		this.playbackQuality = playbackQuality;
	}

	@Override
	public String getPath() {
		return SongSettings.PATH;
	}

	@Override
	public Properties toProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("crossfade", String.valueOf(this.crossfade));
		properties.setProperty("crossfade-seconds", String.valueOf(this.crossfadeSeconds));
		properties.setProperty("soundEnhancer", String.valueOf(this.soundEnhancer));
		properties.setProperty("soundEnhancer", String.valueOf(this.soundEnhancerValue));
		properties.setProperty("playbackQuality", String.valueOf(this.playbackQuality));
		
		return properties;
	}
	
	@Override
	public Properties getDefaultProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("crossfade", String.valueOf(DEFAULT_CROSSFADE));
		properties.setProperty("crossfade-seconds", String.valueOf(DEFAULT_CROSSFADE_SECONDS));
		properties.setProperty("soundEnhancer", String.valueOf(DEFAULT_SOUNDENHANCER));
		properties.setProperty("soundEnhancer", String.valueOf(DEFAULT_SOUNDENHANCER_VALUE));
		properties.setProperty("playbackQuality", String.valueOf(DEFAULT_PLAYBACKQUALITY));
		
		return properties;
	}
}
