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

package me.oskarmendel;

import me.oskarmendel.model.CurrentlyPlayingModel;
import me.oskarmendel.model.SearchResultModel;
import me.oskarmendel.view.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Singleton that manages the loading and changes of a stage.
 * 
 * @author Oskar
 * @version 0.00.00
 * @name StageManager.java
 */
public class StageManager {
	
	private static final Logger LOGGER = Logger.getLogger(StageManager.class.getName());
	
	private static StageManager instance;
	
	private SearchResultModel searchResultModel;
	private CurrentlyPlayingModel currentlyPlayingModel;
	private Stage mainStage;
	
	/**
	 * Returns the singleton instance of StageManager creating it if 
	 * necessary.
	 * 
	 * @return the singleton instance of StageManager.
	 */
	public static StageManager getInstance() {
		if (instance == null)
			instance = new StageManager();
		return instance;
	}
	
	/**
	 * Stores the controllers of each layout view
	 */
	private Map<String, RapidTunesController> controllers = new HashMap<>();
	
	public NavigationController getNavigationController() {
		return (NavigationController) controllers.get(RapidTunesController.NAVIGATION_LAYOUT);
	}
	
	public SongController getSongController() {
		return (SongController) controllers.get(RapidTunesController.SONGCONTROL_LAYOUT);
	}
	
	public PlaylistController getPlaylistController() {
		return (PlaylistController) controllers.get(RapidTunesController.PLAYLISTCONTROL_LAYOUT);
	}
	
	public SongBrowserController getSongBrowserController() {
		return (SongBrowserController) controllers.get(RapidTunesController.SONGBROWSER_LAYOUT);
	}
	
	/**
	 * Manages the setting up and loading of the main window.
	 *  TODO: Decide what stylesheet to load depending on what it says in the properties file
	 * 
	 * @param primaryStage
	 * @throws IOException
	 */
	public void showRapidTunes(Stage primaryStage) throws IOException{
		mainStage = primaryStage;
		mainStage.setTitle("RapidTunes");
		
		LOGGER.log(Level.FINE, "Loading layouts..");
		// Loading all the layouts for the different parts of the application.
		VBox navigationLayout = (VBox) loadLayout(RapidTunesController.NAVIGATION_LAYOUT);
		VBox playlistControlLayout = (VBox) loadLayout(RapidTunesController.PLAYLISTCONTROL_LAYOUT);
		HBox songControlLayout = (HBox) loadLayout(RapidTunesController.SONGCONTROL_LAYOUT);
		AnchorPane songBrowserLayout = (AnchorPane) loadLayout(RapidTunesController.SONGBROWSER_LAYOUT);
		BorderPane rootLayout = (BorderPane) loadLayout(RapidTunesController.ROOT_LAYOUT);
		
		searchResultModel = new SearchResultModel();
		currentlyPlayingModel = new CurrentlyPlayingModel();
		
		getNavigationController().initSearchResultModel(searchResultModel);
		getSongBrowserController().initSearchResultModel(searchResultModel);
		
		getPlaylistController().initCurrentlyPlayingModel(currentlyPlayingModel);
		getSongBrowserController().initCurrentlyPlayingModel(currentlyPlayingModel);
		getSongController().initCurrentlyPlayingModel(currentlyPlayingModel);
		
		rootLayout.setTop(navigationLayout);
		rootLayout.setLeft(playlistControlLayout);
		rootLayout.setBottom(songControlLayout);
		rootLayout.setCenter(songBrowserLayout);
		
		Scene mainScene = new Scene(rootLayout);
		
		LOGGER.log(Level.FINE, "Loading stylesheet: " + RapidTunesController.DEFAULT_STYLING);
		mainScene.getStylesheets().add(getClass().getResource(RapidTunesController.DEFAULT_STYLING).toString());
		
		mainStage.setScene(mainScene);
		mainStage.setMinWidth(800);
		mainStage.setMinHeight(600);
        mainStage.show();
	}
	
	/**
	 * 
	 * @param layout The *.fxml file to be loaded.
	 * 
	 * @return The {@link Parent} object that is in the layout.
	 * 
	 * @throws IOException thrown if the *.fxxml file was not found.
	 */
	private Parent loadLayout(String layout) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		
		LOGGER.log(Level.FINE, "Loading Layout: " + layout);
		loader.setLocation(getClass().getResource(layout));
		
		Parent nodeLayout = loader.load();
		
		//Store controller of target layout
		controllers.put(layout, loader.getController());
		
		return nodeLayout;
	}
}
