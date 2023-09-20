package com.sirma.javacourse.intro.arrayprocessing;

import org.junit.Assert;
import org.junit.Test;

public class ArrayProcessorTest {

	@Test
	public void testGetMinIndexPositive() {
		Assert.assertEquals(4, ArrayProcessor.getMinElementIndex(new int[] {5, 9, 10, 25, 2, 67, 13}));
	}

	@Test
	public void testGetMinIndexNegative() {
		Assert.assertEquals(5, ArrayProcessor.getMinElementIndex(new int[] {1, 7, -2, 6, -2, -5, 1, 2}));
	}

	@Test
	public void testGetMinIndexNull() {
		Assert.assertEquals(-1, ArrayProcessor.getMinElementIndex(null));
	}

	@Test
	public void testGetMinIndexEmpty() {
		Assert.assertEquals(-1, ArrayProcessor.getMinElementIndex(new int[] {}));
	}

	@Test
	public void testSum() {
		Assert.assertEquals(-6, ArrayProcessor.sum(new int[] {1, -4, 2, -10, 5}));
	}

	@Test
	public void testSumEmpty() {
		Assert.assertEquals(0, ArrayProcessor.sum(new int[] {}));
	}

	@Test
	public void testSumNull() {
		Assert.assertEquals(0, ArrayProcessor.sum(null));
	}

	@Test
	public void testPrintNull() {
		Assert.assertThrows(IllegalArgumentException.class, () -> ArrayProcessor.print(null));
	}

	@Test
	public void testSubArrayBestSum() {
		Assert.assertEquals(6, ArrayProcessor.subArrayBestSum(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
	}

	@Test
	public void testSubArrayBestSumNull() {
		Assert.assertThrows(IllegalArgumentException.class, () -> ArrayProcessor.subArrayBestSum(null));
	}
}