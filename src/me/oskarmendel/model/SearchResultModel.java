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

package me.oskarmendel.model;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.oskarmendel.entities.songs.Song;

/**
 * Model that manages search results for the application.
 * This allows multiple controllers have access to the search results.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SearchResultModel.java
 */
public class SearchResultModel {
	
	private final ObservableList<Song> searchResultList = FXCollections.observableArrayList();
	
	/**
	 * 
	 * @return
	 */
	public ObservableList<Song> getSearchResultList() {
		return searchResultList;
	}
	
	/**
	 * 
	 * @param searchResultList
	 */
	public void setSearchResultList(List<Song> searchResultList) {
		this.searchResultList.clear();
		
		// TODO: Temp til i know how to add all List elements to ObservableList
		for (int x = 0; x <= searchResultList.size()-1; x++) {
			this.searchResultList.add(searchResultList.get(x));
		}
	}

}
