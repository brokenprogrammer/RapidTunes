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
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import me.oskarmendel.entities.Song;
import me.oskarmendel.model.SearchResultModel;
import me.oskarmendel.player.search.SearchHandler;

import me.oskarmendel.util.DoublyLinkedList;

/**
 * Controller class for the navigation menu of the application.
 * 
 * @author Oskar
 * @version 0.00.00
 * @name NavigationController.java
 */
public class NavigationController implements RapidTunesController {
	
	@FXML private Button navBackBtn;
	@FXML private Button navFrontBtn;
	@FXML private TextField navSearchField;
	@FXML private Button navSearchBtn;
	
	private static final Logger LOGGER = Logger.getLogger(NavigationController.class.getName());
	
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
		
		//Back Button in the search bar.
		navBackBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e){
				if (searchHistoryIterator.hasPrevious()) {
					searchHistoryIterator.previous();
					navSearchField.setText(searchHistoryIterator.current());
				}
			}
		});
		
		//Forward Button in the search bar.
		navFrontBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e){
				if (searchHistoryIterator.hasNext()) {
					searchHistoryIterator.next();
					navSearchField.setText(searchHistoryIterator.current());
				}
			}
		});
		
		//Search Button in the search bar.
		navSearchBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		        //Save the search to history so we can back to it later.
		        searchHistory.add(navSearchField.getText());
		        searchHistoryIterator.update(true);
		        
		        //SearchHandler performs a search and ads results to list.
		        SearchHandler sh = SearchHandler.getInstance();
		        List<Song> songList = sh.search(navSearchField.getText(), "./demo");
		        
		        //Performs the search for the keywords in the YouTube data API and 
		        //populates the searchResultModel with results.
		        searchResultModel.setSearchResultList(songList);
		    }
		});
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
		
	}
}
