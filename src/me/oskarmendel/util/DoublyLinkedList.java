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

package me.oskarmendel.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Doubly Linked List data structure implemented as generic to fit multiple types.
 * TODO: Currently a linked list, change it to doubly linked.
 * TODO: Implement this as a Doubly-Linked List.
 * 
 * @author Oskar
 * @version 0.00.00
 * @name DoublyLinkedList.java
 */
public class DoublyLinkedList<T> implements List<T>{
	private int size;				// Number of elements in the list.
	private Node<T> first = null;	// First item in the list.
	private Node<T> last = null;	// Last item in the list.
	
	/**
	 * Adds a new node entry to the front of the list.
	 * 
	 * @param data - data to populate the Node object with.
	 */
	public void addFirst(T data) {
		Node<T> newEntry = new Node<T>(data);
		
		if (isEmpty()) {
			newEntry.next = null; //no need since its null by default
			newEntry.prev = null; //no need since its null by default
			first = newEntry;
			last = newEntry;
		} else {
			first.prev = newEntry;
			newEntry.next = first;
			newEntry.prev = null; //no need since its null by default
			first = newEntry;
		}
	}
	
	/**
	 * Adds a new history entry to the end of the list.
	 * 
	 * @param data - data to populate the Node object with.
	 */
	@Override
	public boolean add(T data) {
		Node<T> newEntry = new Node<T>(data);
		
		if (isEmpty()) {
			newEntry.next = null;
			newEntry.prev = null;
			first = newEntry;
			last = newEntry;
		} else {
			last.next = newEntry;
			newEntry.prev = last;
			newEntry.next = null;
			last = newEntry;
		}
		return true;
	}
	
	/**
	 * Removes the current first element of the list.
	 * 
	 * @return The searchTerm within the removed History object.
	 */
	public T removeFirst() {
		if (isEmpty()) {
			//Throw exception
		}
		
		if (!isEmpty()) {
			Node<T> oldFirst = first;
			
			if (first.next == null) {
				first = null;
				last = null;
			} else {
				first = first.next;
			}
			return oldFirst.getContent(); // CHECK THIS HERE <------
		}
		return null;
	}
	
	/**
	 * Removes the last item of the list.
	 * 
	 * @return The searchTerm within the removed History object.
	 */
	/*public String remove() {
		if (isEmpty()) {
			//Throw exception
		}
		
		if (!isEmpty()) {
			String searchTerm = "";
			
			if(first.next == null) {
				first = null;
				last = null;
			} else {
				searchTerm = last.toString();
				last = last.prev;
				last.next = null;
			}
			return searchTerm;
		}
		return null;
	}*/
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
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
		for (Node<T> x = first; x != null; x = x.next) {
			System.out.println(x.toString());
		}
	}

	/**
	 * Returns the number of nodes within the list.
	 * 
	 * @return The size of the doubly linked list in terms of 
	 * number of objects stored in it.
	 */
	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T set(int index, T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, T element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * History object used by this list.
	 *  
	 * @author Oskar
	 */
	private static class Node<T> {
		private T content;
		private Node<T> next;
		private Node<T> prev;
		
		/**
		 * 
		 * @param searchTerm
		 */
		public Node(T content) {
			this.content = content;
		}
		
		/**
		 * 
		 * @return
		 */
		public T getContent() {
			return content;
		}
		
		/**
		 * 
		 */
		@Override
		public String toString() {
			return content.toString();
		}
	}
}
