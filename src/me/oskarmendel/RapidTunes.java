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
import java.util.logging.*;

import javafx.application.Application;
import javafx.stage.Stage;
import me.oskarmendel.StageManager;


/**
 * Main entry-point of the application.
 * 
 * @author Oskar
 * @version 0.00.00
 * @name RapidTunes.java
 */
public class RapidTunes extends Application{
	
	private static final Logger LOGGER = Logger.getLogger(RapidTunes.class.getName());
	private static final String LOG_FILE = "RapidTunes-log.txt";
	
	public static void main(String[] args) {
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
