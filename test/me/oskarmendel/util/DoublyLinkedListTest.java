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

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * @author Oskar
 * @version 0.00.00
 * @name DoublyLinkedListTest.java
 */
public class DoublyLinkedListTest {
	DoublyLinkedList<Integer> list;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new DoublyLinkedList<Integer>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		list.clear();
		list = null;
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#size()}.
	 */
	@Test
	public final void testSize() {
		assertEquals("Size is currently empty.", 0, list.size());
		list.add(20);
		assertEquals("Size is currently one.", 1, list.size());
		list.add(30);
		list.add(40);
		assertEquals("Size is currently one.", 3, list.size());
		list.clear();
		assertEquals("Size is currently empty.", 0, list.size());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#isEmpty()}.
	 */
	@Test
	public final void testIsEmpty() {
		assertEquals("Size is currently empty.", true, list.isEmpty());
		list.add(10);
		assertEquals("Size is currently not empty.", false, list.isEmpty());
		list.clear();
		assertEquals("Size is currently empty.", true, list.isEmpty());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#clear()}.
	 */
	@Test
	public final void testClear() {
		list.add(10);
		assertEquals("Size is currently not empty.", false, list.isEmpty());
		list.clear();
		assertEquals("Size is currently empty.", true, list.isEmpty());
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#addFirst(java.lang.Object)}.
	 */
	@Test
	public final void testAddFirst() {
		int first = 0;
		
		list.add(20);
		list.add(40);
		
		list.addFirst(10);
		first = list.get(0);
		assertEquals("First item in list is 10.", 10, first);
		
		list.addFirst(5);
		first = list.get(0);
		assertEquals("First item in list is 5.", 5, first);
		
		list.addFirst(1);
		first = list.get(0);
		assertEquals("First item in list is 1.", 1, first);
		
		list.clear();
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#add(java.lang.Object)}.
	 */
	@Test
	public final void testAddT() {
		int element = 0;
		
		assertEquals("Successfully added item to the list.", true, list.add(20));
		
		element = list.get(0);
		assertEquals("Item within the list is 20", 20, element);
		
		assertEquals("Successfully added item to the end of the list.", true, list.add(500));
		element = list.get(list.size()-1);
		assertEquals("Last item within the list is 500", 500, element);
		
		assertEquals("Successfully added item to the end of the list.", true, list.add(1000));
		element = list.get(list.size()-1);
		assertEquals("Last item within the list is 1000", 1000, element);
		
		list.clear();
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#add(int, java.lang.Object)}.
	 */
	@Test
	public final void testAddIntT() {
		int element = 0;
		
		//Cannot add an element to an index that doesnt exist. 
		try {
			list.add(0, 10);
			fail("Expected an IndexOutOfBoundsException to be thrown");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(e.getMessage().equals("Attempt to add element to index that does not exist."));
		}
		
		list.add(10);
		
		list.add(0, 20);
		element = list.get(0);
		assertEquals("First item in list is 20.", 20, element);
		
		list.add(1, 500);
		element = list.get(1);
		assertEquals("Item in list at index 1 is 500.", 10, element);
		
		list.clear();
	}
	
	/**
	 * Test method for exception from {@link me.oskarmendel.util.DoublyLinkedList#add(int, java.lang.Object)}.
	 */
	@Test
    public void testAddIntTThrowsIndexOutOfBoundsException() {
        thrown.expect(IndexOutOfBoundsException.class);
        //Test the exception message
        thrown.expectMessage("Attempt to add element to index that does not exist.");
        list.add(0, 10);
    }

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#removeFirst()}.
	 */
	@Test
	public final void testRemoveFirst() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#remove()}.
	 */
	@Test
	public final void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#remove(int)}.
	 */
	@Test
	public final void testRemoveInt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#remove(java.lang.Object)}.
	 */
	@Test
	public final void testRemoveObject() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#displayList()}.
	 */
	@Test
	public final void testDisplayList() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#contains(java.lang.Object)}.
	 */
	@Test
	public final void testContainsObject() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#iterator()}.
	 */
	@Test
	public final void testIterator() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#listIterator(int)}.
	 */
	@Test
	public final void testListIteratorInt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#getIterator()}.
	 */
	@Test
	public final void testGetIterator() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#getIterator(boolean)}.
	 */
	@Test
	public final void testGetIteratorBoolean() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#toArray()}.
	 */
	@Test
	public final void testToArray() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#get(int)}.
	 */
	@Test
	public final void testGetInt() {
		int element = 0;
		
		list.add(20);
		element = list.get(0);
		assertEquals("Item in list at index 1 is 20.", 20, element);
		
		list.addFirst(30);
		element = list.get(0);
		assertEquals("Item in the list as index 0 is 30", 30, element);
		element = list.get(1);
		assertEquals("Item in the list as index 1 is 20", 20, element);
		
		list.add(1, 200);
		element = list.get(1);
		assertEquals("Item in the list as index 1 is 200", 200, element);	
		element = list.get(2);
		assertEquals("Item in the list as index 1 is 20", 20, element);
		
		list.clear();
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#set(int, java.lang.Object)}.
	 */
	@Test
	public final void testSetIntT() {
		fail("Not yet implemented"); // TODO
	}

}
