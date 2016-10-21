package me.oskarmendel;

import javafx.application.Application;
import javafx.stage.Stage;
import me.oskarmendel.StageManager;
import me.oskarmendel.util.SongParser;

public class RapidTunes extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Add Gradle
		// TODO Finish first scene/view for the application. Create the view parts in FXML, styling in css..
		// TODO Create a logger to log to the console when parts of the application are loaded etc.
		// TODO Add functionality to search / query song sources.
		// TODO Display search results from searched / queried source.
		// TODO Download song and split video / sound if required and add to created song object.
		SongParser sp = new SongParser();
		
		String[] videoData = {};
		videoData = sp.parseSong("http://www.youtube.com/get_video_info?video_id=t-_fGnCPXeQ");
		
		//So far parsed video data, and decoded. (Song object that contains some specific data?)
		
		System.out.println("Length of videoData: " + videoData.length);
		for(int i = 0; i <= videoData.length-1; i++) {
			System.out.println(videoData[i]);
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
