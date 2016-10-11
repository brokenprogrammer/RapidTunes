package me.oskarmendel;

import me.oskarmendel.util.SongParser;

public class RapidTunes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SongParser sp = new SongParser();
		String[] videoData = {};
		videoData = sp.parseSong("http://www.youtube.com/get_video_info?video_id=t-_fGnCPXeQ");
		
		//So far parsed video data, and decoded. (Song object that contains some specific data?)
		
		System.out.println("Length of videoData: " + videoData.length);
		for(int i = 0; i <= videoData.length-1; i++) {
			System.out.println(videoData[i]);
		}
		
	}

}
