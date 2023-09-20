package com.sirma.javacourse.intro.arraysorting;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class ArraySorterTest {

	@Test
	public void testQuickSortPositiveNumbers() {
		int[] initial = new int[] {5,1,3,4,6,2};
		int[] sorted = new int[] {1,2,3,4,5,6};
		ArraySorter.quickSort(initial);
		Assert.assertEquals(Arrays.toString(sorted), Arrays.toString(initial));
	}

	@Test public void testQuickSortNegativeNumbers() {
		int[] initial = new int[] {5,-1,-3,4,6,-2};
		int[] sorted = new int[] {-3,-2,-1,4,5,6};
		ArraySorter.quickSort(initial);
		Assert.assertEquals(Arrays.toString(sorted), Arrays.toString(initial));
	}

	@Test public void testQuickSortNull() {
		Assert.assertThrows(IllegalArgumentException.class, () -> ArraySorter.quickSort(null));
	}

	@Test public void testQuickSortEmpty() {
		int[] initial = new int[] {};
		ArraySorter.quickSort(initial);
		Assert.assertEquals(Arrays.toString(new int[] {}), Arrays.toString(initial));
	}
}
