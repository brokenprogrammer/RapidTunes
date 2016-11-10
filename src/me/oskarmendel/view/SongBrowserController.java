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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * 
 * @author Oskar
 * @version 0.00.00
 * @name SongBrowserController.java
 */
public class SongBrowserController implements RapidTunesController {
	
	@FXML private GridPane songBrowserGrid;
	
	private static final Logger LOGGER = Logger.getLogger(SongBrowserController.class.getName());
	
	@FXML 
	public void initialize() {
		LOGGER.log(Level.FINE, "Initialized: " + this.getClass().getName());
		
		songBrowserGrid.setPadding(new Insets(20));
		songBrowserGrid.setHgap(20);
		songBrowserGrid.setVgap(20);
		
		ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        col1.setHalignment(HPos.CENTER);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        col2.setHalignment(HPos.CENTER);
        songBrowserGrid.getColumnConstraints().addAll(col1,col2);
		
//		Text scenetitle = new Text("Welcome");
//		songBrowserGrid.add(scenetitle, 0, 0, 2, 1);
        
		songBrowserGrid.add(new Label("1"), 1, 0);
		songBrowserGrid.add(new Label("0"), 0, 0);
		songBrowserGrid.add(new Label("2"), 0, 1);
		songBrowserGrid.add(new Label("3"), 1, 1);
	}
	
	/**
	 * 
	 */
	public void populateSongBrowser() {
		
	}
}
