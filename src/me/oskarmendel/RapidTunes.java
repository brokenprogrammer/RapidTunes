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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

import javafx.application.Application;
import javafx.stage.Stage;
import me.oskarmendel.StageManager;
import me.oskarmendel.entities.Song;
import me.oskarmendel.player.search.local.LocalSearch;

/**
 * 
 * @author Oskar
 * @version 0.00.00
 * @name RapidTunes.java
 */
public class RapidTunes extends Application{
	
	private static final Logger LOGGER = Logger.getLogger(RapidTunes.class.getName());
	private static final String LOG_FILE = "RapidTunes-log.txt";
	
	public static void main(String[] args) {
		// TODO Create stylings like text size that fits all screens, now fits mac not windows comp.
		// TODO Add functionality to search / query song sources.
		// TODO Display search results from searched / queried source.
		// TODO Download song and split video / sound if required and add to created song object.
		// TODO Does logger need improovement, Add formatting through simple formatter?? 
		// TODO Implement threading when song is splitted with video etc.. Concurrency package
		
		
		//SongParser sp = new SongParser();
		//String[] videoData = {};
		//videoData = sp.parseSong("http://www.youtube.com/get_video_info?video_id=t-_fGnCPXeQ");
		
		//So far parsed video data, and decoded. (Song object that contains some specific data?)
		//System.out.println("Length of videoData: " + videoData.length);
		//for(int i = 0; i <= videoData.length-1; i++) {
			//System.out.println(videoData[i]);
		//}
		
//		try{
//			File directory = new File("C:\\Users\\JeSpEr\\Desktop");
//			LocalSearch ls = new LocalSearch();
//			ArrayList<Song> songs = ls.search("silverstein", directory);
//			
//			for(int i=0; i<songs.size(); i++){
//				
//				System.out.println(songs.get(i).getTitle());
//			}
//			
//		} catch(Exception e){
//			System.out.println("File not found.");
//		}
		
		initializeLogger();
		
		//Launches this application and calls the start method.
		LOGGER.log(Level.FINE, "Launching RapidTunes");
		Application.launch(args);
	}

	/**
	 * Main entry point for JavaFX applications.
	 * Method implemented from Application.
	 */
	@Override
	public void start(Stage arg0) throws Exception {
		StageManager sm = new StageManager();
		sm.showRapidTunes(arg0);
	}
	
	/**
	 * Initialize the logger and the file the logs will be stored in.
	 */
	public static void initializeLogger() {
		LogManager logManager = LogManager.getLogManager();
		java.util.logging.Logger log = logManager.getLogger("");
		log.setUseParentHandlers(false); //Removing console handler.
		log.setLevel(Level.FINE); 		 //Log up to the FINE level
		
		FileHandler fh; 
		
		try {
			fh = new FileHandler(LOG_FILE);
			log.addHandler(fh);
			
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
