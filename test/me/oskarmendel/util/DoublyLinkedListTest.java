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
import org.junit.Test;

/**
 * 
 * @author Oskar
 * @version 0.00.00
 * @name DoublyLinkedListTest.java
 */
public class DoublyLinkedListTest {
	DoublyLinkedList<Integer> list;
	
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
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#clear()}.
	 */
	@Test
	public final void testClear() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#addFirst(java.lang.Object)}.
	 */
	@Test
	public final void testAddFirst() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#add(java.lang.Object)}.
	 */
	@Test
	public final void testAddT() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#add(int, java.lang.Object)}.
	 */
	@Test
	public final void testAddIntT() {
		fail("Not yet implemented"); // TODO
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
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link me.oskarmendel.util.DoublyLinkedList#set(int, java.lang.Object)}.
	 */
	@Test
	public final void testSetIntT() {
		fail("Not yet implemented"); // TODO
	}

}
