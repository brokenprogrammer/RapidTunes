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

import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.util.Duration;
import me.oskarmendel.model.CurrentlyPlayingModel;
import me.oskarmendel.player.SongPlayerHandler;
import me.oskarmendel.song.Song;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SongController.java
 */
public class SongController implements RapidTunesController {
	
	@FXML private Label songSong;
	@FXML private Label songArtist;
	
	@FXML private Button songPrev;
	@FXML private Glyph songPrevIco;
	
	@FXML private Button songPlay;
	@FXML private Glyph songPlayIco;
	
	@FXML private Button songNext;
	@FXML private Glyph songNextIco;
	
	@FXML private Label songCurrentTime;
	@FXML private ProgressBar songProgressBar;
	private ChangeListener<Duration> progressBarChangeListener;
	@FXML private Label songTotalTime;
	
	@FXML private CheckBox songShuffle;
	@FXML private Glyph songShuffleIco;
	
	@FXML private CheckBox songRepeat;
	@FXML private Glyph songRepeatIco;
	
	@FXML private Slider songVolume;
	
	private boolean playing = false;
	
	private static final Logger LOGGER = Logger.getLogger(SongController.class.getName());
	
	private CurrentlyPlayingModel currentlyPlayingModel;
	private SongPlayerHandler player;
	
	@FXML 
	public void initialize() {
		LOGGER.log(Level.FINE, "Initialized: " + this.getClass().getName());
		
		songProgressBar.setMaxWidth(Double.MAX_VALUE);
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
					songPlayIco.setIcon(FontAwesome.Glyph.PLAY);
					player.pause();
					playing = false;
				} else {
					songPlayIco.setIcon(FontAwesome.Glyph.PAUSE);
					player.play();
					playing = true;
				}
			}
		});
		
		progressBarChangeListener = new ChangeListener<Duration>() {
			@Override
			public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
				Platform.runLater(() -> {
					//TODO: This way of doing things is used in the Song class getLengthString
					//TODO and in the SongBrowserController, Create class / helper functions to perform this conversion.
					LocalTime t = LocalTime.MIN.plusSeconds((long) newValue.toSeconds());
					songCurrentTime.setText(t.toString());
				});
				songProgressBar.setProgress(1 * newValue.toSeconds() / player.getSong().getLength());
			}
		};
		player.getCurrentTimeObserver().addListener(progressBarChangeListener);
		
		
		songVolume.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				if (songVolume.isValueChanging()) {
					player.setVolume((int)songVolume.getValue());
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
		
		//Bind currently playing song strings to the song title and artist label.
		this.songSong.textProperty().bind(currentlyPlayingModel.getCurrentSongTitle());
		this.songArtist.textProperty().bind(currentlyPlayingModel.getCurrentSongArtist());
		
		currentlyPlayingModel.getCurrentSong().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				//Less disgusting solution than using SupressWarning?
				@SuppressWarnings("unchecked")
				ObjectProperty<Song> property = (ObjectProperty<Song>) observable;
				
				// Reset the current progressbar.
				songProgressBar.setProgress(0);
				
				player.getCurrentTimeObserver().removeListener(progressBarChangeListener);
				
				// Set the new song for the player to play.
				player.setSong(property.getValue());
				player.play();
				
				player.getCurrentTimeObserver().addListener(progressBarChangeListener);
				
				playing = true;
				songPlayIco.setIcon(FontAwesome.Glyph.PAUSE);
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
