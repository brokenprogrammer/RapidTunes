package me.oskarmendel.player.search.local;

import java.io.File;
import java.util.ArrayList;

import me.oskarmendel.entities.Song;

/**
 * 
 * @author Jesper
 * @version 0.00.00
 * @name LocalSearch.java
 */
public class LocalSearch {
	
	ArrayList<Song> list = new ArrayList<Song>();
	
	public LocalSearch(){
		
	}
	
	public ArrayList<Song> search(String keyword, File directory) {
		
		// if file is directory
		if(directory.isDirectory()){
			
			File[] filesList = directory.listFiles();
			
			for(int i=0; i<filesList.length; i++){
				search(keyword, filesList[i]);
			}
		}
		// if file is .mp3 and if the keyword matches
		else if(directory.getAbsolutePath().endsWith(".mp3") && directory.getName().toLowerCase().contains(keyword.toLowerCase())){
			
			Song s = new Song();
			
			s.setTitle(directory.getName());
			
			list.add(s);
		}
		
		return list;
	}
}
