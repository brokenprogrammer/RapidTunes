package me.oskarmendel.player.search;

import com.google.api.services.youtube.YouTube;

/**
 * Search History object to keep track of search entries.
 * 
 * TODO Add API key inside a file.
 * 
 * @author Oskar
 * @version 0.00.00
 * @name YouTubeSearch.java
 */
public class YouTubeSearch {
	
	/*
	 * Define a file that contains the developers API-key.
	 * Idea from YouTube code samples. 
	 */
	public static final String PROPERTIES_FILENAME = "youtube.properties";
	
	public static final long MAX_VIDEOS_RETURNED = 25;
	
	/*
	 * Global instance of the YouTube object that lets you 
	 * make YouTube Data API requests.
	 */
	public static YouTube youtube;
	
}
