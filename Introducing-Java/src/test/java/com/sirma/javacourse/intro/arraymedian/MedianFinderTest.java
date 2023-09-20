package com.sirma.javacourse.intro.arraymedian;

import org.junit.Assert;
import org.junit.Test;

public class MedianFinderTest {

	@Test
	public void testFindMedianIndexSorted() {
		Assert.assertEquals(4,
				MedianFinder.findMedianIndex(new int[] {1, 2, 3, 4, 5}));
	}

	@Test
	public void testFindMedianIndexUnsorted() {
		Assert.assertEquals(3,
				MedianFinder.findMedianIndex(new int[] {4, 5, 99, -1, 5, 6}));
	}

	@Test
	public void testFindMedianIndexZeros() {
		Assert.assertEquals(1,
				MedianFinder.findMedianIndex(new int[] {0, 0, 0}));
	}

	@Test
	public void testFindMedianIndexOne() {
		Assert.assertEquals(1,
				MedianFinder.findMedianIndex(new int[] {1}));
	}

	@Test
	public void testFindMedianIndexTwoOnes() {
		Assert.assertEquals(1,
				MedianFinder.findMedianIndex(new int[] {1, 1}));
	}

	@Test
	public void testFindMedianIndexEmpty() {
		Assert.assertEquals(-1,
				MedianFinder.findMedianIndex(new int[] {}));
	}

	@Test
	public void testFindMedianIndexNull() {
		Assert.assertEquals(-1, MedianFinder.findMedianIndex(null));
	}
}
