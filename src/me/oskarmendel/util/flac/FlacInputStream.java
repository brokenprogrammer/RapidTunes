package me.oskarmendel.util.flac;

import java.io.IOException;

/**
 * 
 * @author Oskar
 *
 */
public interface FlacInputStream extends AutoCloseable{

	public long getLength();
	
	public long getPosition();
	
	public int getBitPosition();
	
	public void seekTo(long position) throws IOException;
	
	public int readUnsignedInt(int n) throws IOException;
	
	public int readSignedInt(int n) throws IOException;
	
	public void readRiceSignedInts(int param, long[] result, int start, int end) throws IOException;

	public int readByte() throws IOException;
	
	public void readAll(byte[] b) throws IOException;
	
	public void resetCrcs();
	
	public int getCrc8();
	
	public int getCrc16();
	
	public void close() throws IOException;
}
