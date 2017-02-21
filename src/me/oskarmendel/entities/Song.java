package me.oskarmendel.entities;

/**
 * Object representing a song
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
	
	/**
	 * Get song title
	 * 
	 * @return title
	 */
	public String getTitle(){
		
		return title;
	}
	/**
	 * Set song title
	 * 
	 * @param title
	 */
	public void setTitle(String title){
		
		this.title = title;
	}
	/**
	 * Get artist
	 * 
	 * @return artist
	 */
	public String getArtist(){
		
		return artist;
	}
	/**
	 * Set artist
	 * 
	 * @param artist
	 */
	public void setArtist(String artist){
		
		this.artist = artist;
	}
	/**
	 * Get album
	 * 
	 * @return album
	 */
	public String getAlbum(){
		
		return album;
	}
	/**
	 * Set album
	 * 
	 * @param album
	 */
	public void setAlbum(String album){
		
		this.album = album;

	}
	/**
	 * Get song length
	 * 
	 * @return length
	 */
	public int getLength(){
		
		return length;
	}
	/**
	 * Set song length
	 * 
	 * @param length
	 */
	public void setLength(int length){
		
		this.length = length;
	}
}
