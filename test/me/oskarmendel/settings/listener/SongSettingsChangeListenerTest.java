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

package me.oskarmendel.settings.listener;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import me.oskarmendel.settings.SongSettings;
import me.oskarmendel.settings.SongSettings.PlaybackQuality;

/**
 * Tests for the SongSettingsChangeListener.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SongSettingsChangeListenerTest.java
 */
public class SongSettingsChangeListenerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link me.oskarmendel.settings.listener.SongSettingsChangeListener#changed(javafx.beans.value.ObservableValue, me.oskarmendel.settings.SongSettings, me.oskarmendel.settings.SongSettings)}.
	 */
	@Test
	public final void testChanged() {
		SongSettingsChangeListener songSettingsListener = new SongSettingsChangeListener();
		
		// Setting up SongSettings object to bind the listener to.
		final ObjectProperty<SongSettings> songSettings = new SimpleObjectProperty<>();
		songSettings.set(createSongSettings());
		songSettings.addListener(songSettingsListener);
		
		assertEquals("Current crossfade is set to true.", songSettings.get().isCrossfade(), true);
		assertEquals("Current crossfade-seconds is set to 5.", songSettings.get().getCrossfadeSeconds(), 5);
		assertEquals("Current playback quality is set to BAD.", songSettings.get().getPlaybackQuality(), PlaybackQuality.BAD);
		assertEquals("Current sound enhancer is set to true.", songSettings.get().isSoundEnhancer(), true);
		assertEquals("Current sound enhancer value is set to 34.", songSettings.get().getSoundEnhancerValue(), 34);
		
		songSettings.set(createSongSettings(false, 10, PlaybackQuality.GOOD, false, 50));
		
		assertEquals("Current crossfade is set to false.", songSettings.get().isCrossfade(), false);
		assertEquals("Current crossfade-seconds is set to 10.", songSettings.get().getCrossfadeSeconds(), 10);
		assertEquals("Current playback quality is set to GOOD.", songSettings.get().getPlaybackQuality(), PlaybackQuality.GOOD);
		assertEquals("Current sound enhancer is set to false.", songSettings.get().isSoundEnhancer(), false);
		assertEquals("Current sound enhancer value is set to 50.", songSettings.get().getSoundEnhancerValue(), 50);
	}
	
	/**
	 * Helper method to create a dummy SongSettings object.
	 * 
	 * @return - SongSettings object with preset values.
	 */
	private SongSettings createSongSettings() {
		SongSettings settings = new SongSettings();
		
		settings.setCrossfade(true);
		settings.setCrossfadeSeconds(5);
		settings.setPlaybackQuality(PlaybackQuality.BAD);
		settings.setSoundEnhancer(true);
		settings.setSoundEnhancerValue(34);
		
		return settings;
	}
	
	/**
	 * Helper method to create a dummy SongSettings object with specified
	 * values.
	 * 
	 * @param crossfade - Toggle for crossfade.
	 * @param crossfadeSec - Seconds for crossfade.
	 * @param quality - PlaybackQuality for playback.
	 * @param soundEnhancer - Toggle for SoundEnhancer.
	 * @param soundEnhancerVal - Value for SoundEnhancer.
	 * 
	 * @return - SongSettings object with the specified values.
	 */
	private SongSettings createSongSettings(boolean crossfade, int crossfadeSec, PlaybackQuality quality, 
			boolean soundEnhancer, int soundEnhancerVal) {
		SongSettings settings = new SongSettings();
		
		settings.setCrossfade(crossfade);
		settings.setCrossfadeSeconds(crossfadeSec);
		settings.setPlaybackQuality(quality);
		settings.setSoundEnhancer(soundEnhancer);
		settings.setSoundEnhancerValue(soundEnhancerVal);
		
		return settings;
	}
}
