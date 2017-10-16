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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
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
import me.oskarmendel.model.CurrentlyPlayingModel;
import me.oskarmendel.model.SearchResultModel;
import me.oskarmendel.model.SettingsModel;
import me.oskarmendel.view.NavigationController;
import me.oskarmendel.view.PlaylistController;
import me.oskarmendel.view.RapidTunesController;
import me.oskarmendel.view.SongBrowserController;
import me.oskarmendel.view.SongController;

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
	
	private SettingsModel settingsModel;
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
		
		settingsModel = new SettingsModel();
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
		loader.setResources(getResourseBundle(System.getProperty("user.language")));
		Parent nodeLayout = loader.load();
		
		//Store controller of target layout
		controllers.put(layout, loader.getController());
		
		return nodeLayout;
	}

	/**
	 *  Return correct strings depend on user language. May be extended for language-location (etc en-GB, en-US).
	 *  case en just for better human reading
	 *
	 * @param language
	 * @return ResourceBundle for entire application
	 */
	private ResourceBundle getResourseBundle(String language) {
		switch (language) {
			case "sv":
			case "ru":
				return ResourceBundle.getBundle("bundle.strings",
						new Locale(language), new UTF8Control());
			case "en":
			default:
				return ResourceBundle.getBundle("bundle.strings", Locale.ROOT, new UTF8Control());
		}
	}

	/**
	 * Override default ResourceBundle.Control class, since it use antic ISO charset
	 * Only one line is changed to make it to read properties files as UTF-8.
	 * Stack says that bug marked as resolved in Java 9.
	 */
	private class UTF8Control extends ResourceBundle.Control {
		public ResourceBundle newBundle
				(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
				throws IllegalAccessException, InstantiationException, IOException
		{
			// The below is a copy of the default implementation.
			String bundleName = toBundleName(baseName, locale);
			String resourceName = toResourceName(bundleName, "properties");
			ResourceBundle bundle = null;
			InputStream stream = null;
			if (reload) {
				URL url = loader.getResource(resourceName);
				if (url != null) {
					URLConnection connection = url.openConnection();
					if (connection != null) {
						connection.setUseCaches(false);
						stream = connection.getInputStream();
					}
				}
			} else {
				stream = loader.getResourceAsStream(resourceName);
			}
			if (stream != null) {
				try {
					// Only this line is changed to make it to read properties files as UTF-8.
					bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
				} finally {
					stream.close();
				}
			}
			return bundle;
		}
	}
}
