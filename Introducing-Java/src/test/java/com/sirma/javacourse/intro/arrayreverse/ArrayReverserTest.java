package com.sirma.javacourse.intro.arrayreverse;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class ArrayReverserTest {

	@Test
	public void testReverseArrayEvenLength() {
		int[] testArray = new int[]{1,2,3,4,5,6};
		ArrayReverser.reverseArray(testArray);
		Assert.assertEquals(Arrays.toString(new int[]{6,5,4,3,2,1}),
				Arrays.toString(testArray));
	}

	@Test
	public void testReverseArrayOddLength() {
		int[] testArray = new int[]{1,2,3,4,5};
		ArrayReverser.reverseArray(testArray);
		Assert.assertEquals(Arrays.toString(new int[]{5,4,3,2,1}),
				Arrays.toString(testArray));
	}

	@Test
	public void testReverseArrayEmpty() {
		int[] testArray = new int[]{};
		ArrayReverser.reverseArray(testArray);
		Assert.assertEquals(Arrays.toString(new int[]{}),
				Arrays.toString(testArray));
	}

	@Test
	public void testReverseArrayNull() {
		Assert.assertThrows(IllegalArgumentException.class, () -> ArrayReverser.reverseArray(null));
	}
}
