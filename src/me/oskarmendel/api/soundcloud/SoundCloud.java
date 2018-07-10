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

package me.oskarmendel.api.soundcloud;

/**
 * Definition of the SoundCloud API.
 * 
 * //TODO: SoundCloud is currently not accepting more developer apps so as soon as they accept more a 
 * 	developer application for RapidTunes have to be registered.
 * //TODO: Implement this builder pattern properly..
 * //TODO: Use a properties file for the keys like for the youtube api key
 * //TODO: Add functionality to connect to the SoundCloud API.
 * //TODO: Add functionality to perform search through the SoundCloud API.
 * //TODO: Translate result from search result to Track objects.
 * //TODO: Create SoundCloud search strategy and display results in Song Browser.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SoundCloud.java
 */
public class SoundCloud {
	
	private String clientId;
	private String clientSecret;
	private String redirectURI;
	
	/**
	 * 
	 * @param clientId
	 * @param clientSecret
	 * @param redirectURI
	 */
	public SoundCloud(String clientId, String clientSecret, String redirectURI) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectURI = redirectURI;
	}
	
	/**
	 * 
	 * @param builder
	 */
	private SoundCloud(Builder builder) {
		this.clientId = builder.clientId;
		this.clientSecret = builder.clientSecret;
		this.redirectURI = builder.redirectURI;
	}
	
	/**
	 * 
	 */
	public void search() {
		
	}
	
	/**
	 * 
	 * @author Oskar
	 *
	 */
	public class Builder {
		private String clientId;
		private String clientSecret;
		private String redirectURI;
		
		/**
		 * 
		 * @param clientId
		 * @param clientSecret
		 * @param redirectURI
		 */
		public Builder(String clientId, String clientSecret, String redirectURI) {
			this.clientId = clientId;
			this.clientSecret = clientSecret;
			this.redirectURI = redirectURI;
		}
		
		/**
		 * 
		 * @param clientId
		 * @return
		 */
		public Builder setClientId(String clientId) {
			this.clientId = clientId;
			return this;
		}
		
		/**
		 * 
		 * @param clientSecret
		 * @return
		 */
		public Builder setClientSecret(String clientSecret) {
			this.clientSecret = clientSecret;
			return this;
		}
		
		/**
		 * 
		 * @param redirectURI
		 * @return
		 */
		public Builder setRedirectURI(String redirectURI) {
			this.redirectURI = redirectURI;
			return this;
		}
		
		/**
		 * 
		 * @return
		 */
		public SoundCloud build() {
			return new SoundCloud(this);
		}
	}
}
