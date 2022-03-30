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

import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Doubly Linked List data structure implemented as generic to fit multiple types.
 * TODO: Test all implemented Methods to make sure they work.
 * 
 * @author Oskar
 * @version 0.00.00
 * @name DoublyLinkedList.java
 */
public class DoublyLinkedList<T> extends AbstractSequentialList<T>{
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
			last = newEntry;
		} else {
			first.prev = newEntry;
			newEntry.next = first;
		}
		
		first = newEntry;
		this.size++;
	}
	
	/**
	 * Adds a new history entry to the end of the list.
	 * 
	 * @param data - data to populate the Node object with.
	 */
	public boolean add(T data) {
		Node<T> newEntry = new Node<T>(data);
		
		if (isEmpty()) {
			first = newEntry;
		} else {
			last.next = newEntry;
			newEntry.prev = last;
		}
		
		last = newEntry;
		this.size++;
		return true;
	}
	
	/**
	 * Inserts the specified element at the specified index and pushes
	 * old elements position forward by one. 
	 * 
	 * 
	 * @param index - position element should be inserted at.
	 * @param element - element to be inserted.
	 * 
	 * @throws IndexOutOfBoundsException - index is out of bounds.
	 */
	@Override
	public void add(int index, T element) {
		if ((index < 0 || index >= size())) {
			//Attempting to add to an non-existing index.
			throw new IndexOutOfBoundsException("Attempt to add element to index that does not exist.");
		}
		
		for (Node<T> x = first; x != null; x = x.next) {
			if (index == 0) {
				Node<T> next = x.next;
				Node<T> prev = x.prev;
				Node<T> newEntry = new Node<T>(element);
				
				//Size of the list is 1.
				if (prev == null && next == null) {
					addFirst(element);
				} else if (prev == null) { // Found element is at the first index of the list.
					addFirst(element);
				} else { 
					//The elements should be pushed forward and since this method
					//only accept indexes compared to elements within this list 
					//the same logic applies to an index at the end of the list 
					//as the middle of the list. An element will never be pushed 
					//to the end of the list with this method since thats and uninitialized index.
					prev.next = newEntry;
					newEntry.prev = prev;
					
					x.prev = newEntry;
					newEntry.next = x;
				}
				
				size++;
			}
			index--;
		}
	}
	
	/**
	 * Removes the current first element of the list.
	 * 
	 * @return The searchTerm within the removed History object.
	 * 
	 * @throws IndexOutOfBoundsException - if attempting to remove element from empty list.
	 */
	public T removeFirst() {
		if (isEmpty()) {
			//Trying to remove element from an empty list.
			throw new IndexOutOfBoundsException();
		}
		
		Node<T> oldFirst = first;
		
		if (first.next == null) {
			first = null;
			last = null;
		} else {
			first = first.next;
			first.prev = null;
		}
		
		this.size--;
		return oldFirst != null ? oldFirst.getContent() : null;
	}
	
	/**
	 * Removes the last element of the list.
	 * 
	 * @return the value from the removed item.
	 * 
	 * @throws IndexOutOfBoundsException - if attempting to remove element from empty list.
	 */
	public T remove() {
		if (isEmpty()) {
			//Trying to remove element from an empty list.
			throw new IndexOutOfBoundsException();
		}
		
		Node<T> oldLast = last;
		
		if (first.next == null) {
			first = null;
			last = null;
		} else {
			last = last.prev;
			last.next = null;
		}
		
		this.size--;
		return oldLast != null ? oldLast.getContent() : null;
	}
	
	/**
	 * Removes the element at the specified position in this list (optional operation). 
	 * Shifts any subsequent elements to the left (subtracts one from their indices). 
	 * Returns the element that was removed from the list.
	 * 
	 * @param index - the index of the element to be removed
	 * @return the element that was removed from the list.
	 * 
	 * @throws IndexOutOfBoundsException - if attempting to remove element from empty list.
	 */
	@Override
	public T remove(int index) {
		if(isEmpty() || (index < 0 || index > size())) {
			//Trying to remove element from an empty list.
			throw new IndexOutOfBoundsException();
		}
		
		for (Node<T> x = first; x != null; x = x.next) {
			if (index == 0) {
				Node<T> next = x.next;
				Node<T> prev = x.prev;
				T removed = x.getContent();
				
				//Size of the list is 1 and the list will be empty after this element is removed.
				if (prev == null && next == null) {
					first = null;
					last = null;
				} else if (prev == null) { // Found element is at the first index of the list.
					first = first.next;
					first.prev = null;
				} else if (next == null) { // Found element is at the last index of the list.
					last = last.prev;
					last.next = null;
				} else {
					prev.next = next;
					next.prev = prev;
				}
				
				size--;
				return removed;
			}
			index--;
		}
		
		return null;
	}
	
	/**
	 * Removes the first occurrence of the specified item from the list.
	 * 
	 * @param o - object to be removed if present.
	 * @return true if object was found and removed, false otherwise.
	 * 
	 * @throws IndexOutOfBoundsException - if attempting to remove element from empty list.
	 */
	public boolean remove(Object o) {
		if (isEmpty()) {
			//Trying to remove element from an empty list.
			throw new IndexOutOfBoundsException();
		}
		
		for (Node<T> x = first; x != null; x = x.next) {
			if (o.equals(x.getContent())) {
				Node<T> next = x.next;
				Node<T> prev = x.prev;
				
				//Size of the list is 1 and the list will be empty after this element is removed.
				if (next == null && prev == null) {
					first = null;
					last = null;
				} else if (prev == null) { //Found element is at the first index of the list.
					first = first.next;
					first.prev = null;
				} else if (next == null) { // Found element is at the last index of the list.
					last = last.prev;
					last.next = null;
				} else {
					prev.next = next;
					next.prev = prev;
				}
				this.size--;
				return true;
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
	 * Return an new iterator capable of iterator over all elements 
	 * within this list.
	 * 
	 * @param index - starting index for the iterator.
	 * @return an iterator of the elemends in this list.
	 */
	@Override
	public ListIterator<T> listIterator(int index) {
		if (index > size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		return new DoublyLinkedListIterator(index);
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
	
	/**
	 * Returns an iterator capable of iterating forward and backwards of 
	 * the elements within the list.
	 * 
	 * @param startBack - decide whether the list should start from the last element or not.
	 * @return a new DoublyLinkedListIterator instance.
	 */
	public DoublyLinkedListIterator getIterator(boolean startback) {
		return new DoublyLinkedListIterator(startback);
	}

	/**
	 * Returns an array of all the elements in this list.
	 * 
	 * @return an array containing all the elements of this list.
	 */
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
	 * Removes all the elements from the list.
	 * The list will be empty after this call returns.
	 */
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
		 * Contruct a new Node object with the specified content.
		 * 
		 * @param content - the content to be stored within the node.
		 */
		public Node(T content) {
			this.content = content;
		}
		
		/**
		 * Getter for the Node objects content.
		 * 
		 * @return this node's content.
		 */
		public T getContent() {
			return content;
		}
		
		/**
		 * Setter for the Node objects content.
		 * 
		 * @param content - new content to store within the Node.
		 */
		public void setContent(T content) {
			this.content = content;
		}
		
		/**
		 * Returns a string representation of the object. In general, 
		 * the toString method returns a string that "textually represents" 
		 * this object. The result should be a concise but informative 
		 * representation that is easy for a person to read. It is 
		 * recommended that all subclasses override this method.
		 * 
		 * @return a string representation of the object.
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
	public class DoublyLinkedListIterator implements ListIterator<T> {

		private Node<T> iteratorNode;
		public int index;
		
		/**
		 * Initializes a DoublyLinkedListIterator with the default value of 
		 * iteratorNode starting from the start of the list.
		 */
		public DoublyLinkedListIterator() {
			this.iteratorNode = first;
			this.index = 0;
		}
		
		public DoublyLinkedListIterator(int index) {
			this.iteratorNode = first;
			this.index = 0;
			
			for (int x = 0; x < index; x++) {
				next();
			}
		}
		
		/**
		 * Initializes a DoublyLinkedListIterator with the option of choosing
		 * if iteratorNode should start at the first element or last of the list.
		 * 
		 * @param setToLast - Set the iteratorNode to the last item of the DoublyLinkedList
		 */
		public DoublyLinkedListIterator(boolean setToLast) {
			if (setToLast) {
				this.iteratorNode = last;
				this.index = size();
			} else {
				this.iteratorNode = first;
				this.index = 0;
			}
		}
		
		/**
		 * Updates the iteratorNode to the DoublyLinkedList boundy elements last or first.
		 * 
		 * @param setToLast - Set the iteratorNode to the last item of the DoublyLinkedList
		 */
		public void update(boolean setToLast) {
			if (setToLast) {
				this.iteratorNode = last;
				this.index = size();
			} else {
				this.iteratorNode = first;
				this.index = 0;
			}
		}
		
		/**
		 * Returns true if the list contains more items forward in the list.
		 * 
		 * @return True if there is more elements forwards in the list.
		 */
		@Override
		public boolean hasNext() {
			return index < size();
		}
		
		/**
		 * Returns true if the list contains more items backwards in the list.
		 * 
		 * @return True if there is more elements backwards in the list.
		 */
		@Override
		public boolean hasPrevious() {
			return index > 1;
		}

		/**
		 * Traverses forward in the list then returns the element at the current
		 * position in the list. 
		 * 
		 * @return next element in the iteration.
		 */
		@Override
		public T next() {
			T current = iteratorNode.getContent();
			
			if (hasNext()) {
				iteratorNode = iteratorNode.next;
			}
			
			index++;
			return current;
		}
		
		/**
		 * Traverses backwards in the list then returns the element at the current 
		 * position in the list.
		 * 
		 * @return previous element in the iteration.
		 */
		@Override
		public T previous() {
			T current = iteratorNode.getContent();
			
			if (hasPrevious()) {
				iteratorNode = iteratorNode.prev;
			}
			
			index--;
			return current;
		}
		
		/**
		 * Returns the current element in the iteration without traversing the list.
		 * 
		 * @return current element in the iteration.
		 */
		public T current() {
			return iteratorNode.getContent();
		}
		
		/**
		 * Returns the index of the element that would be returned by a subsequent 
		 * call to next. (Returns list size if the list iterator is at the end of the list.)
		 * 
		 * @return the index of the element that would be returned by a subsequent call to next.
		 */
		@Override
		public int nextIndex() {
			return this.index;
		}

		/**
		 * Returns the index of the element that would be returned by a subsequent
		 * call to previous(). (Returns -1 if the list iterator is at the beginning of the list.)
		 * 
		 * @return the index of the element that would be returned by a subsequent call to previous.
		 */
		@Override
		public int previousIndex() {
			return this.index;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void set(T e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void add(T e) {
			// TODO Auto-generated method stub
		}
	}
}
