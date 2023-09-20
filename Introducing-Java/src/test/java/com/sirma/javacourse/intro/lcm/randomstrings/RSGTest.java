package com.sirma.javacourse.intro.lcm.randomstrings;

import org.junit.Assert;
import org.junit.Test;

import com.sirma.javacourse.intro.randomstrings.RandomStringGenerator;

public class RSGTest {

	@Test
	public void testGenerateRandomStringZero() {
		Assert.assertEquals("", RandomStringGenerator.generateRandomString(0));
	}

	@Test
	public void testGenerateRandomStringNegative() {
		Assert.assertThrows(IllegalArgumentException.class, () -> RandomStringGenerator.generateRandomString(-1));
	}
}
