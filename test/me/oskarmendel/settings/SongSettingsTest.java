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

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the SongSettings.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SongSettingsTest.java
 */
public class SongSettingsTest {

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
	 * Test method for {@link me.oskarmendel.settings.SongSettings#SongSettings(java.util.Properties)}.
	 */
	@Test
	public final void testSongSettingsProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("crossfade", "true");
		properties.setProperty("crossfade-seconds", "12");
		properties.setProperty("soundEnhancer", "true");
		properties.setProperty("soundEnhancer-value", "74");
		properties.setProperty("playbackQuality", "GOOD");
		
		SongSettings settings = new SongSettings(properties);
		
		assertEquals("Crossfade is toggled to true.", settings.isCrossfade(), true);
		assertEquals("Crossfade seconds is set to 12.", settings.getCrossfadeSeconds(), 12);
		
		assertEquals("SoundEnhancer is toggled to true.", settings.isSoundEnhancer(), true);
		assertEquals("SoundEnhancer value is set to 74.", settings.getSoundEnhancerValue(), 74);
		
		assertEquals("Playback quality is set to Good.", settings.getPlaybackQuality(), SongSettings.PlaybackQuality.GOOD);
	}
	
	/**
	 * Test method for {@link me.oskarmendel.settings.SongSettings#toProperties()}.
	 */
	@Test
	public final void testToProperties() {
		SongSettings settings = new SongSettings();
		
		settings.setCrossfade(false);
		settings.setCrossfadeSeconds(4);
		
		settings.setSoundEnhancer(true);
		settings.setSoundEnhancerValue(33);
		
		settings.setPlaybackQuality(SongSettings.PlaybackQuality.BAD);
		
		Properties properties = settings.toProperties();
		
		assertEquals("Crossfade is toggled to false.", properties.get("crossfade").equals("false"), true);
		assertEquals("Crossfade seconds is set to 4.", properties.get("crossfade-seconds").equals("4"), true);
		
		assertEquals("SoundEnhancer is toggled to true.", properties.get("soundEnhancer").equals("true"), true);
		assertEquals("SoundEnhancer value is set to 33.", properties.get("soundEnhancer-value").equals("33"), true);
		
		assertEquals("Playback quality is set to Bad.", properties.get("playbackQuality").equals("BAD"), true);
	}

	/**
	 * Test method for {@link me.oskarmendel.settings.SongSettings#getDefaultProperties()}.
	 */
	@Test
	public final void testGetDefaultProperties() {
		SongSettings settings = new SongSettings();
		
		Properties properties = settings.getDefaultProperties();
		
		assertEquals("Default crossfade is toggled to false.", properties.get("crossfade").equals("false"), true);
		assertEquals("Default crossfade seconds is set to 6.", properties.get("crossfade-seconds").equals("6"), true);
		
		assertEquals("Default soundEnhancer is toggled to false.", properties.get("soundEnhancer").equals("false"), true);
		assertEquals("Default soundEnhancer value is set to 50.", properties.get("soundEnhancer-value").equals("50"), true);
		
		assertEquals("Default playback quality is set to Best Available.", properties.get("playbackQuality").equals("BEST_AVAILABLE"), true);
	}
}
