package me.oskarmendel.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

public class SongParser {
	
	public String[] parseSong(String songurl){
		
		//Later check url if its youtube, soundcloud etc..
		return parseYoutube(songurl);
	}
	
	private String[] parseYoutube(String songurl){
		String[] videoData = {};
		
		URL url;
		try {
			url = new URL(songurl);
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
				for(String line; (line = reader.readLine()) != null;) {
					//Decode the URL encoded information.
					String result = URLDecoder.decode(line, "UTF-8");
					videoData = result.split("&");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return videoData;
	}
}
