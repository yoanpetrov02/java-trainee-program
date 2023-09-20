package com.sirma.javacourse.intro.lcm;

import org.junit.Assert;
import org.junit.Test;

public class LcmFinderTest {

	@Test
	public void testFindPositiveNumbersLCM() {
		Assert.assertEquals(600, LcmFinder.findLcm(120, 50));
	}

	@Test
	public void testFindNegativeNumbersLCM() {
		Assert.assertEquals(60, LcmFinder.findLcm(-12, 5));
	}

	@Test
	public void testFindZerosLCM() {
		Assert.assertThrows(IllegalArgumentException.class, () -> LcmFinder.findLcm(0, 0));
	}
}