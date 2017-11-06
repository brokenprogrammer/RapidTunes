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

import org.controlsfx.glyphfont.Glyph;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import me.oskarmendel.model.SearchResultModel;
import me.oskarmendel.model.SettingsModel;
import me.oskarmendel.player.search.SearchHandler;
import me.oskarmendel.song.Song;
import me.oskarmendel.util.DoublyLinkedList;

/**
 * Controller class for the navigation menu of the application.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name NavigationController.java
 */
public class NavigationController implements RapidTunesController {
	
	@FXML private AnchorPane menu;
	@FXML private MenuBarController menuBarController;
	@FXML private Glyph navLogoIco;
	@FXML private TextField navSearchField;
	@FXML private Button navSearchBtn;
	@FXML private Text navAccountName;
	@FXML private Button navAccountBtn;
	@FXML private Glyph navAccountBtnIco;
	
	private static final Logger LOGGER = Logger.getLogger(NavigationController.class.getName());
	
	private SettingsModel settingsModel;
	private SearchResultModel searchResultModel;
	
	DoublyLinkedList<String> searchHistory = new DoublyLinkedList<String>();
	DoublyLinkedList<String>.DoublyLinkedListIterator searchHistoryIterator;
	int searchIterator = 0;
	
	/**
	 * Initialize the navigation controller. 
	 */
	@FXML 
	public void initialize() {
		LOGGER.log(Level.FINE, "Initialized: " + this.getClass().getName());
		
		searchHistoryIterator = searchHistory.getIterator(true);
		this.navLogoIco.size(25.0);
		this.navAccountBtnIco.size(20.0);
	}
	
	@FXML
	public void onSearch(ActionEvent event) {
		//Save the search to history so we can back to it later.
        searchHistory.add(navSearchField.getText());
        searchHistoryIterator.update(true);
        
        //SearchHandler performs a search and ads results to list.
        SearchHandler sh = SearchHandler.getInstance();
        List<Song> songList = sh.search(navSearchField.getText(), "./demo");
        
        //TODO: Make on change event / observable instead of changing entire list each time?
        //Performs the search for the keywords in the YouTube data API and 
        //populates the searchResultModel with results.
        searchResultModel.setSearchResultList(songList);
	}
	
	/**
	 * Initializes the SettingsModel which contains the applications settings.
	 * 
	 * @param settingsModel - settingsModel object to share data with.
	 */
	public void initSettingsModel(SettingsModel settingsModel) {
		if (this.settingsModel != null) {
			throw new IllegalStateException("Model can only be initialized once");
		}
		
		this.settingsModel = settingsModel;
		
		// Initialize the SettingsModel for the controller of the MenuBar.
		this.menuBarController.initSettingsModel(settingsModel);
	}
	
	/**
	 * Initializes the SearchResultModel which connects the list of search results between
	 * the NavigationController and the SongBrowserController.
	 * This controller will edit the SearchResultModel by pushing searchResults to its ObservableList.
	 * 
	 * @param searchResultModel - searchResultModel object to share data to.
	 */
	public void initSearchResultModel(SearchResultModel searchResultModel) {
		//Make sure model is only set once.
		if (this.searchResultModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
		}
		
		this.searchResultModel = searchResultModel;
		
		// Initialize the SearchResultModel for the controller of the MenuBar.
		this.menuBarController.initSearchResultModel(searchResultModel);
	}
}
