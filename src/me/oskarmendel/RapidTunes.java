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

import javafx.application.Application;
import javafx.stage.Stage;
import me.oskarmendel.StageManager;
import me.oskarmendel.util.SongParser;

/**
 * 
 * @author Oskar
 * @version 0.00.00
 * @name RapidTunes.java
 */
public class RapidTunes extends Application{

	public static void main(String[] args) {
		// TODO Connect controllers to the individual layouts., styling in css..
		// TODO Create a logger to log to the console when parts of the application are loaded etc. - LoggerFactory
		// TODO Add functionality to search / query song sources.
		// TODO Display search results from searched / queried source.
		// TODO Download song and split video / sound if required and add to created song object.
		SongParser sp = new SongParser();
		
		String[] videoData = {};
		videoData = sp.parseSong("http://www.youtube.com/get_video_info?video_id=t-_fGnCPXeQ");
		
		//So far parsed video data, and decoded. (Song object that contains some specific data?)
		System.out.println("Length of videoData: " + videoData.length);
		for(int i = 0; i <= videoData.length-1; i++) {
			//System.out.println(videoData[i]);
		}
		
		//Launches this application and calls the start method.
		Application.launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		StageManager sm = new StageManager();
		sm.showRapidTunes(arg0);
	}

}
