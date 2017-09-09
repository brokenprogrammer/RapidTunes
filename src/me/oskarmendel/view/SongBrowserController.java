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

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import me.oskarmendel.entities.songs.Song;
import me.oskarmendel.model.CurrentlyPlayingModel;
import me.oskarmendel.model.SearchResultModel;

/**
 * TODO: Create a CellFactory that decides how to present the listView Cells.
 * 		Can be found at: https://github.com/james-d/SimpleMVP/blob/master/src/examples/mvp/list/ListController.java
 * 
 * TODO: http://stackoverflow.com/questions/11180884/how-to-populate-a-tableview-that-is-defined-in-an-fxml-file-that-is-designed-in
 * TODO: https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/table-view.htm#CJAGAAEE
 * 
 * @author Oskar
 * @version 0.00.00
 * @name SongBrowserController.java
 */
public class SongBrowserController implements RapidTunesController {
	
	@FXML private AnchorPane songBrowserPane;
	@FXML private TableView<Song> songList;
	@FXML private TableColumn<Song, String> songListSong;
	@FXML private TableColumn<Song, String> songListPublisher;
	@FXML private TableColumn<Song, String> songListTime;
	@FXML private TableColumn<Song, String> songListSource;
	
	private static final Logger LOGGER = Logger.getLogger(SongBrowserController.class.getName());
	
	private SearchResultModel searchResultModel;
	private CurrentlyPlayingModel currentlyPlayingModel;
	
	@FXML 
	public void initialize() {
		LOGGER.log(Level.FINE, "Initialized: " + this.getClass().getName());
		
		//Define width for the TableView columns
		songList.getColumns().forEach(c -> {
			if(c.getText().equals("Song")) {
				c.prefWidthProperty().bind(songList.widthProperty().divide(2));
			} else if(c.getText().equals("Publisher")){
				c.prefWidthProperty().bind(songList.widthProperty().divide(4));
			} else {
				c.prefWidthProperty().bind(songList.widthProperty().divide(8));
			}
		});
		
		//This is how to set the value by using Property of a object
		//We will switch to this way after we implemented a yt song object.
		//TODO: When implementing read up on the PropertyValueFactory class.
		//TODO: https://docs.oracle.com/javafx/2/api/javafx/scene/control/cell/PropertyValueFactory.html
		//songListSong.setCellValueFactory(new PropertyValueFactory<Video, String>("title"));
		
		//Manually setting the content as a simple string property.
		songListSong.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitle()));
		songListPublisher.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getArtist()));
		songListTime.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getLength()));
		songListSource.setCellValueFactory(c -> new SimpleStringProperty("YT"));
		
		//Define an on-click for the table rows.
		songList.setRowFactory(tv -> {
			TableRow<Song> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if(event.getClickCount() == 2 && (!row.isEmpty())) {
					//Here we will call an even to start the song.
					Song s = row.getItem();
					currentlyPlayingModel.setCurrentSong(s);
				}
			});
			return row;
		});
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
		/*songList.setCellFactory(lv -> new ListCell<SearchResult>() {
			@Override
			public void updateItem(SearchResult result, boolean empty) {
				super.updateItem(result, empty);
				if (empty) {
					setText(null);
				} else {
					setText(result.getSnippet().getTitle());
				}
			}
		});*/
	}
	
	/**
	 * Initializes the CurrentlyPlayingModel which this class will send data to when a song is 
	 * clicked within the SongBrowser. 
	 * 
	 * @param currentlyPlayingModel - currentlyPlayingModel object to send data to.
	 */
	public void initCurrentlyPlayingModel(CurrentlyPlayingModel currentlyPlayingModel) {
		//Make sure model is only set once.
		if (this.currentlyPlayingModel != null) {
			throw new IllegalStateException("Model can only be initialized once");
		}
		
		this.currentlyPlayingModel = currentlyPlayingModel;
	}
}
