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
		this.size++;
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
		this.size++;
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
			this.size--;
			return oldFirst.getContent(); // CHECK THIS HERE <------
		}
		return null;
	}
	
	/**
	 * Removes the last element of the list.
	 * 
	 * @return the value from the removed item.
	 */
	public T remove() {
		if (isEmpty()) {
			//Throw exception
		}
		
		if (!isEmpty()) {
			Node<T> oldLast = last;
			if (first.next == null) {
				first = null;
				last = null;
			} else {
				last = last.prev;
				last.next = null;
			}
			this.size--;
			return oldLast.getContent(); 
		}
		return null;
	}
	
	/**
	 * Removes the first occurrence of the specified item from the list.
	 * TODO: DOES THIS WORK ??? DOES NOT WORK - http://stackoverflow.com/questions/8523436/removing-a-node-from-a-doubly-linked-list
	 * @param o - object to be removed if present.
	 * @return True if object was removed, false otherwise.
	 */
	@Override
	public boolean remove(Object o) {
		if (isEmpty()) {
			//Throw Exception
		}
		
		if (!isEmpty()) {
			for (Node<T> x = first; x != null; x = x.next) {
				if (o.equals(x.getContent())) {
					Node<T> next = x.next;
					Node<T> prev = x.prev;
					
					if (prev == null) {		   //At first
						first = next;
						next.prev = prev;
					} else if (next == null) { //At last
						prev.next = next;
						last = prev;
					} else {
						prev.next = next;
						next.prev = prev;
					}
					return true;
				}
			}
		}
		
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

	/**
	 * Checks if object exists within the list.
	 * 
	 * @param object - object to look for within the list.
	 * @return True if the object exists in the list.
	 */
	@Override
	public boolean contains(Object object) {
		if (!isEmpty()) {
			for (Node<T> x = first; x != null; x = x.next) {
				if (object.equals(x.getContent())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns an new iterator capable of iterating over all the elements
	 * within this list.
	 * 
	 * @return an iterator of the elements in this list.
	 */
	@Override
	public Iterator<T> iterator() {
		return new DoublyLinkedListIterator();
	}
	
	/**
	 * Returns an iterator capable of iterating forward and backwards of 
	 * the elements within the list.
	 * 
	 * @return a new DoublyLinkedListIterator instance.
	 */
	public DoublyLinkedListIterator getIterator() {
		return new DoublyLinkedListIterator();
	}
	
	public DoublyLinkedListIterator getIterator(boolean startback) {
		return new DoublyLinkedListIterator(startback);
	}

	/**
	 * Returns an array of all the elements in this list.
	 * 
	 * @return an array containing all the elements of this list.
	 */
	@Override
	public Object[] toArray() {
		if (!isEmpty()) {
			Object[] list = new Object[this.size()];
			int i = 0;
			
			for (Node<T> x = first; x != null; x = x.next) {
				list[i] = (x.getContent());
				i++;
			}
			
			return list;
		}
		return null;
	}

	/**
	 * 
	 */
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

	/**
	 * Removes all the elements from the list.
	 * The list will be empty after this call returns.
	 */
	@Override
	public void clear() {
		first = null;
		last  = null;
		size = 0;
	}

	/**
	 * Returns the element at the specified index.
	 * 
	 * @param index - position of the item to return.
	 * @return the element at the specified position in this list.
	 */
	@Override
	public T get(int index) {
		if (!isEmpty()) {
			for (Node<T> x = first; x != null; x = x.next) {
				if (index == 0) {
					return x.getContent();
				}
				index--;
			}
		}
		
		return null;
	}

	/**
	 * Replaces the element at the given index.
	 * 
	 * @param index - position of the element to replace.
	 * @param element - the element to replace the old element with.
	 * @return the old element at the specified position.
	 */
	@Override
	public T set(int index, T element) {
		if (!isEmpty()) {
			for (Node<T> x = first; x != null; x = x.next) {
				if (index == 0) {
					T oldElement = x.getContent();
					x.setContent(element);
					return oldElement;
				}
				index--;
			}
		}
		return null;
	}

	/**
	 * Inserts the specified element at the specified index and pushes
	 * old elements position forward by one.
	 * 
	 * @param index - position element should be inserted at.
	 * @param element - element to be inserted.
	 */
	@Override
	public void add(int index, T element) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
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
	 * Node object used by this list.
	 *  
	 * @author Oskar
	 * @version 0.00.00
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
		 * @param content
		 */
		public void setContent(T content) {
			this.content = content;
		}
		
		/**
		 * 
		 */
		@Override
		public String toString() {
			return content.toString();
		}
	}
	
	/**
	 * Iterator class for the doubly linked list capable of iterating
	 * forwards and backwards.
	 * 
	 * @author Oskar
	 * @version 0.00.00
	 */
	public class DoublyLinkedListIterator implements Iterator<T> {

		private Node<T> iteratorNode;
		
		/**
		 * 
		 */
		public DoublyLinkedListIterator() {
			this.iteratorNode = first;
		}
		
		public DoublyLinkedListIterator(boolean startFromBack) {
			if (startFromBack) {
				this.iteratorNode = last;
			} else {
				this.iteratorNode = first;
			}
		}
		
		/**
		 * Returns true if the list contains more items forward in the list.
		 * 
		 * @return True if there is more elements forwards in the list.
		 */
		@Override
		public boolean hasNext() {
			return iteratorNode.next != null;
		}
		
		/**
		 * Returns true if the list contains more items backwards in the list.
		 * 
		 * @return True if there is more elements backwards in the list.
		 */
		public boolean hasPrev() {
			return iteratorNode.prev != null;
		}

		/**
		 * Traverses forward in the list then returns the element at the current
		 * position in the list. 
		 * 
		 * @return next element in the iteration.
		 */
		@Override
		public T next() {
			if (hasNext()) {
				iteratorNode = iteratorNode.next;
			}
			return iteratorNode.getContent();
		}
		
		/**
		 * Traverses backwards in the list then returns the element at the current 
		 * position in the list.
		 * 
		 * @return previous element in the iteration.
		 */
		public T prev() {
			if (hasPrev()) {
				iteratorNode = iteratorNode.prev;
			}
			return iteratorNode.getContent();
		}
		
		/**
		 * Returns the current element in the iteration without traversing the list.
		 * 
		 * @return current element in the iteration.
		 */
		public T current() {
			return iteratorNode.getContent();
		}
	}
}
