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

 package me.oskarmendel.util.song

 import com.mpatric.mp3agic.ID3v1
 import com.mpatric.mp3agic.ID3v2
 import com.mpatric.mp3agic.Mp3File
import me.oskarmendel.entities.LocalSong
import me.oskarmendel.entities.Song;
import me.oskarmendel.util.song.flac.FlacFile

/**
 * File stripper that attempts to strip tags from song files.
 * TODO: Check if there is enough error checking. def keyword?
 * TODO: Method to check what file it is to strip.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FileStripper.groovy
 */
class FileStripper {
	
	/**
	 * Strips the tags from a mp3 file and places them within a Song object.
	 * 
	 * @param mp3File - Target File to strip the tags from.
	 * @return A Song object containing all the tags from the mp3 file.
	 */
	LocalSong stripMp3Song(File mp3File) {
		if(!mp3File.exists()) {
			throw new IllegalArgumentException("No such file exists!")
		}
		
		Mp3File mp3 = new Mp3File(mp3File)
		LocalSong song = new LocalSong()
		
		if(mp3.hasId3v1Tag()) {
			song = stripMp3ID3v1(mp3)
		} else if(mp3.hasId3v2Tag()) {
			song = stripMp3ID3v2(mp3)
		}
		
		return song
	}
	
	/**
	 * Strips the tags from a Flac file and places them within a Song object.
	 * 
	 * @param flacFile - Target File to strip the tags from.
	 * @return A Song object containing all the tags from the Flac file.
	 */
	LocalSong stripFlacSong(File flacFile) {
		if(!flacFile.exists()) {
			throw new IllegalArgumentException("No such file exists!")
		}
		
		FlacFile flac = new FlacFile(flacFile)
		LocalSong song = new LocalSong()
		
		song.setTitle(flac.getTitle())
		song.setArtist(flac.getArtist())
		song.setAlbum(flac.getAlbum())
		song.setLength("")
		
		return song
	}
	
	/**
	 * Private helper method that strips ID3v1 tags from an mp3 file.
	 * 
	 * @param mp3File - Target Mp3File with ID3v1 tags to strip.
	 * @return A Song object containing all the tags from the mp3File.
	 */
	private LocalSong stripMp3ID3v1(Mp3File mp3File) {
		if(!mp3File.hasId3v1Tag()) {
			throw new IllegalArgumentException("No such file exists!")
		}
		
		ID3v1 id3v1Tags = mp3File.getId3v1Tag()
		LocalSong mp3Song = new LocalSong()
		
		mp3Song.setTitle(id3v1Tags.getTitle())
		mp3Song.setArtist(id3v1Tags.getArtist())
		mp3Song.setAlbum(id3v1Tags.getAlbum())
		mp3Song.setLength(mp3File.getLengthInSeconds().toString())
		
		return mp3Song
	}
	
	/**
	 * Private helper method that strips ID3v2 tags from an mp3 file.
	 * 
	 * @param mp3File - Target Mp3File with ID3v2 tags to strip.
	 * @return A Song object containing all the tags from the mp3File.
	 */
	private LocalSong stripMp3ID3v2(Mp3File mp3File) {
		if(!mp3File.hasId3v2Tag()) {
			throw new IllegalArgumentException("No such file exists!")
		}
		
		ID3v2 id3v2Tags = mp3File.getId3v2Tag()
		LocalSong mp3Song = new LocalSong()
		
		mp3Song.setTitle(id3v2Tags.getTitle())
		mp3Song.setArtist(id3v2Tags.getArtist())
		mp3Song.setAlbum(id3v2Tags.getAlbum())
		mp3Song.setLength(mp3File.getLengthInSeconds().toString())
		
		return mp3Song
	}
}
