package me.oskarmendel.entities;

/**
 * 
 * @author Jesper
 * @version 0.00.00
 * @name Song.java
 */
public class Song {
	
	private String title, artist, album;
	private int length;
	
	public Song(){
		
	}
	
	public String getTitle(){
		
		return title;
	}
	public void setTitle(String t){
		
		title = t;
	}
	public String getArtist(){
		
		return artist;
	}
	public void setArtist(String t){
		
		artist = t;
	}
	public String getAlbum(){
		
		return album;
	}
	public void setAlbum(String t){
		
		album = t;
	}
	public int getLength(){
		
		return length;
	}
	public void setLength(int t){
		
		length = t;
	}
}
