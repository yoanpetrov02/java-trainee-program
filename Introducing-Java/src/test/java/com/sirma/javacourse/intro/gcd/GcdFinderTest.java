package com.sirma.javacourse.intro.gcd;
import org.junit.Assert;
import org.junit.Test;

public class GcdFinderTest {

	@Test
	public void testFindPositiveNumbersGCD() {
		Assert.assertEquals(2, GcdFinder.findGcd(744, 782));
	}

	@Test
	public void testFindNegativeNumbersGCD() { Assert.assertEquals(4, GcdFinder.findGcd(-20,16)); }

	@Test
	public void testFindZerosGCD() {
		Assert.assertThrows(IllegalArgumentException.class, () -> GcdFinder.findGcd(0, 10));
	}
}