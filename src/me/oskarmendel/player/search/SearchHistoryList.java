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


/**
 * Search History object to keep track of search entries.
 * TODO: Currently a linked list, change it to doubly linked.
 * TODO Implement this as a Doubly-Linked List.
 * 
 * @author Oskar
 * @version 0.00.00
 * @name SearchHistoryList.java
 */
public class SearchHistoryList {
	
	/**
	 * 
	 * @author Oskar
	 *
	 */
	private class History {
		String searchTerm;
		History next;
		
		/**
		 * 
		 * @param searchTerm
		 */
		public History(String searchTerm) {
			this.searchTerm = searchTerm;
		}
	}
	
	History first = null;
	History last = null;
	
	/**
	 * Adds a new history entry to the front of the list.
	 * 
	 * @param searchTerm - searchTerm to populate the History object with.
	 */
	public void addFirst(String searchTerm) {
		History newEntry = new History(searchTerm);
		
		if (isEmpty()) {
			newEntry.next = null;
			first = newEntry;
			last = newEntry;
		} else {
			History oldfirst = first;
			first = newEntry;
			first.next = oldfirst;
		}
	}
	
	/**
	 * Adds a new history entry to the end of the list.
	 * 
	 * @param searchTerm - searchTerm to populate the History object with.
	 */
	public void add(String searchTerm) {
		History newEntry = new History(searchTerm);
		
		if (isEmpty()) {
			newEntry.next = null;
			first = newEntry;
			last = newEntry;
		} else {
			History oldLast = last;
			oldLast.next = newEntry;
			last = newEntry;
		}
	}
	
	/**
	 * Removes the current first element of the list.
	 * 
	 * @return The searchTerm within the removed History object.
	 */
	public String remove() {
		if (!isEmpty()) {
			History oldFirst = first;
			
			if (first.next == null) {
				first = null;
				last = null;
			} else {
				first = first.next;
			}
			return oldFirst.searchTerm;
		}
		return null;
	}
	
	/**
	 * Removes the last item of the list.
	 * 
	 * @return The searchTerm within the removed History object.
	 */
	public String removeLast() {
		return null;
	}
	
	/**
	 * Checks if the entire list is empty or not.
	 * 
	 * @return True if the list is empty and false if it is not.
	 */
	public boolean isEmpty() {
		return first == null;
	}
	
	/**
	 * TODO: Remove this method and override toString.
	 */
	public void displayList() {
		for (History x = first; x != null; x = x.next) {
			System.out.println(x.searchTerm);
		}
	}
}
