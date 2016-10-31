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

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
	
	List<String> searchHistory = new ArrayList<String>();
	int searchIterator = 0;
	
	/**
	 * Initilize the navigation controller. 
	 * TODO Fix out of bounds exception when scrolling search terms.
	 * TODO Implement YouTube search functionality.
	 */
	@FXML public void initialize() {
		//Add action events to buttons.
		System.out.println("Initialized navigation controller.");
		
		navBackBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e){
				if (searchIterator >= 1) {
					System.out.println(searchHistory.size());
					navSearchField.setText(searchHistory.get(searchIterator-2)); //Fix out of bounds exception
					searchIterator -= 1;
				}
			}
		});
		
		navFrontBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e){
				if (searchIterator <= searchHistory.size()-1) {
					System.out.println(searchHistory.size());
					searchIterator += 1;
				}
			}
		});
		
		navSearchBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        preformSearch(navSearchField.getText());
		        //Save the search to history so we can back to it later.
		        searchHistory.add(navSearchField.getText());
		        searchIterator += 1;
		    }
		});
	}
	
	/**
	 * 
	 * @param searchTerms
	 */
	public void preformSearch(String searchTerms) {
		System.out.println("Search for this.. " + searchTerms);
	}
}
