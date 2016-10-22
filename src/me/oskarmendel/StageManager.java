package me.oskarmendel;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author Oskar
 *
 */
public class StageManager {
	
	static StageManager instance;
	private Stage mainStage;
	
	/**
	 * 
	 * @param primaryStage
	 * @throws IOException
	 */
	public void showRapidTunes(Stage primaryStage) throws IOException{
		mainStage = primaryStage;
		mainStage.setTitle("RapidTunes");
		
		
		VBox navigationLayout = (VBox) loadLayout("/view/Navigation.fxml");
		AnchorPane rootLayout = (AnchorPane) loadLayout("/view/Root.fxml");
		
		rootLayout.getChildren().add(navigationLayout);
		
		Scene mainScene = new Scene(rootLayout);
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
		Parent nodeLayout = loader.load();
		
		return nodeLayout;
	}
}
