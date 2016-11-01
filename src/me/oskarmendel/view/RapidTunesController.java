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

package me.oskarmendel.view;

/**
 * 
 * @author Oskar
 * @version 0.00.00
 * @name RapidTunesController.java
 */
public interface RapidTunesController {

	String LAYOUTS_PATH = "/view/";
	String IMAGES_PATH = "/images/";
	String STYLES_PATH = "/css/";
	String KEYS_PATH = "/keys/";

	String ROOT_LAYOUT = LAYOUTS_PATH + "RootLayout.fxml";
	String NAVIGATION_LAYOUT = LAYOUTS_PATH + "NavigationLayout.fxml";
	String SONGCONTROL_LAYOUT = LAYOUTS_PATH + "SongControlLayout.fxml";
	String PLAYLISTCONTROL_LAYOUT = LAYOUTS_PATH + "PlaylistControlLayout.fxml"; 
	
	String YOUTUBE_PROPERTIES = KEYS_PATH + "youtube.properties";
}
