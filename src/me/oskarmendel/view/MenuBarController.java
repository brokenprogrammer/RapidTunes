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

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import me.oskarmendel.model.SearchResultModel;

/**
 * Controller class for the MenuBar in the Navigation part of the application.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name MenuBarController.java
 */
public class MenuBarController implements RapidTunesController {
	
	@FXML public MenuBar menuMenuBar;
	
	// File Menu
	@FXML public MenuItem menuFileNewPlaylist;
	@FXML public MenuItem menuFileImportPlaylist;
	@FXML public MenuItem menuFileExit;
	
	// Edit Menu
	@FXML public MenuItem menuEditUndo;
	@FXML public MenuItem menuEditRedo;
	@FXML public MenuItem menuEditCut;
	@FXML public MenuItem menuEditCopy;
	@FXML public MenuItem menuEditPaste;
	@FXML public MenuItem menuEditDelete;
	@FXML public MenuItem menuEditMarkAll;
	@FXML public MenuItem menuEditSearch;
	@FXML public MenuItem menuEditSettings;
	
	// View Menu
	
	// PlayBack Menu
	@FXML public MenuItem menuPlaybackPlay;
	@FXML public MenuItem menuPlaybackNext;
	@FXML public MenuItem menuPlaybackPrevious;
	@FXML public MenuItem menuPlaybackFastForward;
	@FXML public MenuItem menuPlaybackRewind;
	@FXML public MenuItem menuPlaybackShuffle;
	@FXML public MenuItem menuPlaybackRepeat;
	@FXML public MenuItem menuPlaybackVolumeUp;
	@FXML public MenuItem menuPlaybackVolumeDown;
	@FXML public MenuItem menuPlaybackOpenQueue;
	
	// Help Menu
	@FXML public MenuItem menuHelpRapidTunesHelp;
	@FXML public MenuItem menuHelpAboutRapidTunes;
	
	private static final Logger LOGGER = Logger.getLogger(MenuBarController.class.getName());

	private SearchResultModel searchResultModel;
	
	@FXML 
	public void initialize() {
		LOGGER.log(Level.FINE, "Initialized: " + this.getClass().getName());
	}
	
	public void foo() {
		System.out.println("Tudelou");
	}
	
	/**
	 * Initializes the SearchResultModel which connects the list of search results between
	 * the MenuBarController and the SongBrowserController.
	 * 
	 * @param searchResultModel - searchResultModel object to share data to.
	 */
	public void initSearchResultModel(SearchResultModel searchResultModel) {
		//Make sure model is only set once.
		if (this.searchResultModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
		}
		
		this.searchResultModel = searchResultModel;
	}
}
