package com.sirma.javacourse.collections.pagebean;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Element;

import org.junit.Assert;
import org.junit.Test;

public class ElementPageManagerTest {

	private static final ArrayList<Object> ELEMENTS = new ArrayList<>(
			List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23));

	@Test
	public void testGetCurrentPageNumber() {
		ElementPageManager testObject = new ElementPageManager(5, ELEMENTS);

		testObject.next();
		int actual1 = testObject.getCurrentPageNumber();
		testObject.lastPage();
		int actual2 = testObject.getCurrentPageNumber();

		Assert.assertEquals(1, actual1);
		Assert.assertEquals(5, actual2);
	}

	@Test
	public void testNextCorrectPage() {
		Object[] expected1 = new Object[] {1,2,3,4,5};
		Object[] expected2 = new Object[] {6,7,8,9,10};
		ElementPageManager testObject = new ElementPageManager(5, ELEMENTS);

		ArrayList<Object> actual1 = testObject.next();
		ArrayList<Object> actual2 = testObject.next();

		Assert.assertArrayEquals(expected1, actual1.toArray());
		Assert.assertArrayEquals(expected2, actual2.toArray());
		Assert.assertNull(
				new ElementPageManager(5, null).next());
		Assert.assertNull(
				new ElementPageManager(5, new ArrayList<>()).next());
	}

	@Test
	public void testPreviousCorrectPage() {
		Object[] expected1 = new Object[] {6,7,8,9,10};
		Object[] expected2 = new Object[] {1,2,3,4,5};
		ElementPageManager testObject = new ElementPageManager(5, ELEMENTS);

		testObject.next();
		testObject.next();
		testObject.next();

		ArrayList<Object> actual1 = testObject.previous();
		ArrayList<Object> actual2 = testObject.previous();

		Assert.assertArrayEquals(expected1, actual1.toArray());
		Assert.assertArrayEquals(expected2, actual2.toArray());
		Assert.assertNull(
				new ElementPageManager(5, null).previous());
		Assert.assertNull(
				new ElementPageManager(5, new ArrayList<>()).previous());
	}

	@Test
	public void testFirstPageCorrectPage() {
		Object[] expected = new Object[] {1,2,3,4,5};
		ElementPageManager testObject = new ElementPageManager(5, ELEMENTS);

		ArrayList<Object> actual = testObject.firstPage();

		Assert.assertArrayEquals(expected, actual.toArray());
		Assert.assertNull(
				new ElementPageManager(5, null).firstPage());
		Assert.assertNull(
				new ElementPageManager(5, new ArrayList<>()).firstPage());
	}

	@Test
	public void testLastPageCorrectPage() {
		Object[] expected = new Object[] {21,22,23};
		ElementPageManager testObject = new ElementPageManager(5, ELEMENTS);

		ArrayList<Object> actual = testObject.lastPage();

		Assert.assertArrayEquals(expected, actual.toArray());
		Assert.assertNull(
				new ElementPageManager(5, null).lastPage());
		Assert.assertNull(
				new ElementPageManager(5, new ArrayList<>()).lastPage());
	}
}
