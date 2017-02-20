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

package me.oskarmendel.player.search;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

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
	
	private static final Logger LOGGER = Logger.getLogger(YouTubeSearch.class.getName());
	
	/*
	 * Define a file that contains the developers API-key.
	 * Idea from YouTube code samples. 
	 */
	public static final String PROPERTIES_FILENAME = "youtube.properties";
	
	public static final long MAX_VIDEOS_RETURNED = 25;
	public static String API_KEY;
	
	/*
	 * Global instance of the YouTube object that lets you 
	 * make YouTube Data API requests.
	 */
	public static YouTube youtube;
	
	public YouTubeSearch () {
		// Load the properties required for YouTube data api requests by
		// using the API key stored inside the properties file.
		Properties properties = new Properties();
		try {
			InputStream inStream = getClass().getResourceAsStream("/properties/" + PROPERTIES_FILENAME);
			properties.load(inStream);
			
			//Set the loaded key
			API_KEY = properties.getProperty("youtube.apikey");
			
		} catch (IOException e) {
			System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
            + " : " + e.getMessage());
		}
	}
	
	public List<SearchResult> search(String keywords) {
		LOGGER.log(Level.FINE, "YouTube Search Initiated..");
		
		try {
			// This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("rapidtunes").build();
			
			// Define the API request for retrieving search results.
			YouTube.Search.List search = youtube.search().list("id, snippet");
			
			// Set developer key.
			search.setKey(API_KEY);
			
			// Set search query.
			search.setQ(keywords);
			
			// Restrict the search results to only include videos. See:
            // https://developers.google.com/youtube/v3/docs/search/list#type
			search.setType("video");
			
			// To increase efficiency, only retrieve the fields that the
            // application uses.
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url, snippet/channelTitle)");
            search.setMaxResults(MAX_VIDEOS_RETURNED);
            
            // Call the api and handle results.
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            
            
            // Handle the results from the api request.
            if (searchResultList != null) {
            	//Print result for testing
            	//int x = 0;
            	//while (x < searchResultList.size()) {
            		//SearchResult oneVid = searchResultList.get(x);
            		//System.out.println("Title: " + oneVid.getSnippet().getTitle() + " "
            		//		+ "Thumbnail: " + oneVid.getSnippet().getThumbnails().getDefault().getUrl());
            		//x++;
            		//return oneVid.getSnippet().getThumbnails().getDefault().getUrl();
            	//}
            	return searchResultList;
            }
			
		} catch (GoogleJsonResponseException e) {
			System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
		} catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
		}
		
		return null;
	}
	
}
