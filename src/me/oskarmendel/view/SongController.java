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

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import me.oskarmendel.entities.Song;
import me.oskarmendel.model.CurrentlyPlayingModel;
import me.oskarmendel.player.SongPlayerHandler;

/**
 * 
 * @author Oskar
 * @version 0.00.00
 * @name SongController.java
 */
public class SongController implements RapidTunesController {
	
	@FXML private Button songPrev;
	@FXML private Button songPlay;
	@FXML private Button songNext;
	
	private boolean playing = false;
	
	private static final Logger LOGGER = Logger.getLogger(SongController.class.getName());
	
	private CurrentlyPlayingModel currentlyPlayingModel;
	private SongPlayerHandler player;
	
	@FXML 
	public void initialize() {
		LOGGER.log(Level.FINE, "Initialized: " + this.getClass().getName());
		
		initSongPlayer();
		
		songPrev.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		songNext.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//TODO: Application will crash if you press play on startup without a song 
		// Being initialized.
		songPlay.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (playing) {
					songPlay.setText("Play");
					player.pause();
					playing = false;
				} else {
					songPlay.setText("Pause");
					player.play();
					playing = true;
				}
			}
		});
	}
	
	/**
	 * Initializes the CurrentlyPlayingModel which this class will get data from when a song is 
	 * clicked within the SongBrowser and this class will use the song object to play the song. 
	 * 
	 * @param currentlyPlayingModel - currentlyPlayingModel object to send data to.
	 */
	public void initCurrentlyPlayingModel(CurrentlyPlayingModel currentlyPlayingModel) {
		//Make sure model is only set once.
		if (this.currentlyPlayingModel != null) {
			throw new IllegalStateException("Model can only be initialized once");
		}
		
		this.currentlyPlayingModel = currentlyPlayingModel;
		
		currentlyPlayingModel.getCurrentSong().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				//Less disgusting solution than using SupressWarning?
				@SuppressWarnings("unchecked")
				ObjectProperty<Song> property = (ObjectProperty<Song>) observable;
				
				player.setSong(property.getValue());
				player.play();
				
				playing = true;
				songPlay.setText("Pause");
			}
			
		});
	}
	
	/**
	 * Initializes the songPlayer connected to this controller.
	 */
	private void initSongPlayer() {
		this.player = new SongPlayerHandler();
	}
}
