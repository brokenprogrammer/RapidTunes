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
	 * Enum that defines different playback qualities that will be used to
	 * select the quality of the song playback for sources that allows it.
	 */
	public enum PlaybackQuality {
		BAD,
		GOOD,
		BEST_AVAILABLE
	}
	
	private boolean crossfade;					// Boolean toggle for turning crossfade of songs on / off.
	private int 	crossfadeSeconds;			// Amount of seconds to spend on crossfading songs.
	
	private boolean soundEnhancer;				// Boolean toggle for turning sound enhancer on / off.
	private int 	soundEnhancerValue;			// Value to enhance the sound with.
	
	private PlaybackQuality playbackQuality;	// Which playback quality to select for sources that allows it.
	
	/**
	 * Default constructor for the SongSettings that simply calls
	 * the parent constructor and leaves all members uninitialized.
	 */
	public SongSettings() {
		super();
	}
	
	/**
	 * Constructor that initializes all members using the specified Properties
	 * object.
	 * 
	 * @param properties - Properties object to retrieve data from.
	 */
	public SongSettings(Properties properties) {
		super();
		
		this.crossfade = Boolean.getBoolean(properties.getProperty("crossfade"));
		this.crossfadeSeconds = Integer.parseInt(properties.getProperty("crossfade-seconds"));
		
		this.soundEnhancer = Boolean.getBoolean(properties.getProperty("soundEnhancer"));
		this.soundEnhancerValue = Integer.parseInt(properties.getProperty("soundEnhancer-value"));
		
		this.playbackQuality = toPlaybackQuality(properties.getProperty("playbackQuality"));
	}
	
	/**
	 * Getter for the crossfade value of this SongSettings.
	 * 
	 * @return - Crossfade value of this SongSettings.
	 */
	public boolean isCrossfade() {
		return crossfade;
	}

	/**
	 * Getter for the crossfadeSeconds value of this SongSettings.
	 * 
	 * @return - CrossfadeSeconds value of this SongSettings.
	 */
	public int getCrossfadeSeconds() {
		return crossfadeSeconds;
	}

	/**
	 * Getter for the soundEnhancer value of this SongSettings.
	 * 
	 * @return - SoundEnhancer value of this SongSettings.
	 */
	public boolean isSoundEnhancer() {
		return soundEnhancer;
	}

	/**
	 * Getter for the soundEnhancerValue of this SongSettings.
	 * 
	 * @return - SoundEnhancerValue value of this SongSettings.
	 */
	public int getSoundEnhancerValue() {
		return soundEnhancerValue;
	}

	/**
	 * Getter for the playbackQuality value of this SongSettings.
	 * 
	 * @return - PlaybackQuality value of this SongSettings.
	 */
	public PlaybackQuality getPlaybackQuality() {
		return playbackQuality;
	}

	/**
	 * Setter for the crossfade value of this SongSettings.
	 * 
	 * @param crossfade - Crossfade value to set.
	 */
	public void setCrossfade(boolean crossfade) {
		this.crossfade = crossfade;
	}

	/**
	 * Setter for the crossfadeSeconds value of this SongSettings.
	 * 
	 * @param crossfadeSeconds - CrossfadeSeconds value to set.
	 */
	public void setCrossfadeSeconds(int crossfadeSeconds) {
		this.crossfadeSeconds = crossfadeSeconds;
	}

	/**
	 * Setter for the soundEnhancer value of this SongSettings.
	 * 
	 * @param soundEnhancer - SoundEnhancer value to set.
	 */
	public void setSoundEnhancer(boolean soundEnhancer) {
		this.soundEnhancer = soundEnhancer;
	}

	/**
	 * Setter for the soundEnhancerValue value of this SongSettings.
	 * 
	 * @param soundEnhancerValue - SoundEnhancerValue value to set.
	 */
	public void setSoundEnhancerValue(int soundEnhancerValue) {
		this.soundEnhancerValue = soundEnhancerValue;
	}

	/**
	 * Setter for the playbackQuality value of this SongSettings.
	 * 
	 * @param playbackQuality - PlaybackQuality value to set.
	 */
	public void setPlaybackQuality(PlaybackQuality playbackQuality) {
		this.playbackQuality = playbackQuality;
	}
	
	/**
	 * Helper method that converts a String containing a PlaybackQuality
	 * keyword into a PlaybackQuality constant.
	 * 
	 * @param quality - String containing a PlaybackQuality keyword.
	 * 
	 * @return - A PlaybackQuality constant extracted from the specified String.
	 */
	private PlaybackQuality toPlaybackQuality(String quality) {
		if (quality.isEmpty()) {
			// Throw Settings Error..
		}
		
		if (quality.contains("BAD")) {
			return PlaybackQuality.BAD;
		} else if (quality.contains("GOOD")) {
			return PlaybackQuality.GOOD;
		} else if (quality.contains("BEST_AVAILABLE")) {
			return PlaybackQuality.BEST_AVAILABLE;
		} else {
			//TODO Replace with own RapidTunes settings exception.
			throw new IllegalArgumentException("Illegal PlaybackQuality string.");
		}
	}
	
	/**
	 * Returns the path for the settings file.
	 * This path is used when saving / loading setting files.
	 * 
	 * @return - Path of the Settings file.
	 */
	@Override
	public String getPath() {
		return SongSettings.PATH;
	}

	/**
	 * Returns a Settings object converted into a Properties object.
	 * 
	 * @return - Properties object with all the Settings defined by the target Settings object.
	 */
	@Override
	public Properties toProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("crossfade", String.valueOf(this.crossfade));
		properties.setProperty("crossfade-seconds", String.valueOf(this.crossfadeSeconds));
		properties.setProperty("soundEnhancer", String.valueOf(this.soundEnhancer));
		properties.setProperty("soundEnhancer-value", String.valueOf(this.soundEnhancerValue));
		properties.setProperty("playbackQuality", String.valueOf(this.playbackQuality));
		
		return properties;
	}
	
	/**
	 * Returns the default Properties object defined by the target Settings object.
	 * 
	 * @return - Properties object with all the default properties.
	 */
	@Override
	public Properties getDefaultProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("crossfade", String.valueOf(DEFAULT_CROSSFADE));
		properties.setProperty("crossfade-seconds", String.valueOf(DEFAULT_CROSSFADE_SECONDS));
		properties.setProperty("soundEnhancer", String.valueOf(DEFAULT_SOUNDENHANCER));
		properties.setProperty("soundEnhancer-value", String.valueOf(DEFAULT_SOUNDENHANCER_VALUE));
		properties.setProperty("playbackQuality", String.valueOf(DEFAULT_PLAYBACKQUALITY));
		
		return properties;
	}
}
