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

import me.oskarmendel.view.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Manages the loading and changes of a stage.
 * 
 * @author Oskar
 * @version 0.00.00
 * @name StageManager.java
 */
public class StageManager {
	
	static StageManager instance;
	private Stage mainStage;
	
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
		
		// Loading all the layouts for the different parts of the application.
		VBox navigationLayout = (VBox) loadLayout(RapidTunesController.NAVIGATION_LAYOUT);
		VBox playlistControlLayout = (VBox) loadLayout(RapidTunesController.PLAYLISTCONTROL_LAYOUT);
		HBox songControlLayout = (HBox) loadLayout(RapidTunesController.SONGCONTROL_LAYOUT);
		BorderPane rootLayout = (BorderPane) loadLayout(RapidTunesController.ROOT_LAYOUT);
		
		rootLayout.setTop(navigationLayout);
		rootLayout.setLeft(playlistControlLayout);
		rootLayout.setBottom(songControlLayout);
		
		Scene mainScene = new Scene(rootLayout);
		mainScene.getStylesheets().add(getClass().getResource(RapidTunesController.DARK_STYLING).toString());
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
		System.out.println(getClass().getResource(layout));
		loader.setLocation(getClass().getResource(layout));
		//Store controller of target layout
		controllers.put(layout, loader.getController());
		Parent nodeLayout = loader.load();
		
		return nodeLayout;
	}
}
