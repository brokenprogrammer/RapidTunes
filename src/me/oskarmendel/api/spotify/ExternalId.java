/**
 * RapidTunes.
 * The music application to help you use all your music sources in one place.
 *
 * The MIT License (MIT)
 *
 * Copyright (C) 2018 The RapidTunes
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

package me.oskarmendel.api.spotify;

import java.util.Map;

/**
 * A Spotify External Id object.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name ExternalId.java
 */
public class ExternalId {

	private Map<String, String> externalIds;
	
	/**
	 * Default constructor for the ExternalId object creating an empty ExternalId object. 
	 */
	public ExternalId() {
		
	}
	
	/**
	 * Constructs a ExternalId object with the values of the specified parameters.
	 * 
	 * @param externalIds
	 */
	public ExternalId(Map<String, String> externalIds) {
		this.externalIds = externalIds;
	}

	/**
	 * @return the externalIds
	 */
	public Map<String, String> getExternalIds() {
		return externalIds;
	}

	/**
	 * @param externalIds the externalIds to set
	 */
	public void setExternalIds(Map<String, String> externalIds) {
		this.externalIds = externalIds;
	}
}
