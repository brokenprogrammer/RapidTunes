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

import com.google.api.services.youtube.model.SearchResult;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import me.oskarmendel.model.SearchResultModel;

/**
 * TODO: Create a CellFactory that decides how to present the listView Cells.
 * 		Can be found at: https://github.com/james-d/SimpleMVP/blob/master/src/examples/mvp/list/ListController.java
 * 
 * @author Oskar
 * @version 0.00.00
 * @name SongBrowserController.java
 */
public class SongBrowserController implements RapidTunesController {
	
	@FXML private AnchorPane songBrowserPane;
	@FXML private ListView<SearchResult> songList;
	
	private static final Logger LOGGER = Logger.getLogger(SongBrowserController.class.getName());
	
	private SearchResultModel searchResultModel;
	
	@FXML 
	public void initialize() {
		LOGGER.log(Level.FINE, "Initialized: " + this.getClass().getName());
	}
	
	/**
	 * Initializes the SearchResultModel which connects the list of search results between
	 * the SongBrowserController and the NavigationController.
	 * This controller will use the data within the models ObservableList and display it within 
	 * the local ListView.
	 * 
	 * @param searchResultModel - searchResultModel object to get data from.
	 */
	public void initSearchResultModel(SearchResultModel searchResultModel) {
		//Make sure model is only set once.
		if (this.searchResultModel != null) {
			throw new IllegalStateException("Model can only be initialized once");
		}
		
		this.searchResultModel = searchResultModel;
		songList.setItems(searchResultModel.getSearchResultList());
		
		//Only display the title of each song in the search result list
		songList.setCellFactory(lv -> new ListCell<SearchResult>() {
			@Override
			public void updateItem(SearchResult result, boolean empty) {
				super.updateItem(result, empty);
				if (empty) {
					setText(null);
				} else {
					setText(result.getSnippet().getTitle());
				}
			}
		});
	}
}