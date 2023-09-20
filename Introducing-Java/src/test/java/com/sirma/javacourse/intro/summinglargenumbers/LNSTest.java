package com.sirma.javacourse.intro.summinglargenumbers;

import org.junit.Assert;
import org.junit.Test;

public class LNSTest {

	@Test
	public void testSumLargeBiggerFirst() {
		Assert.assertEquals("123444444", LargeNumberSumator.sumLarge("123123123", "321321"));
	}

	@Test
	public void testSumLargeSmallerFirst() {
		Assert.assertEquals("123444444", LargeNumberSumator.sumLarge("321321", "123123123"));
	}

	@Test
	public void testSumLargeEqualLength() {
		Assert.assertEquals("444444444", LargeNumberSumator.sumLarge("123123123", "321321321"));
	}

	@Test
	public void testSumLargeEqualLengthWithCarry() {
		Assert.assertEquals("1100000", LargeNumberSumator.sumLarge("500000", "600000"));
	}

	@Test
	public void testSumLargeNull() {
		Assert.assertNull(LargeNumberSumator.sumLarge(null, null));
	}

	@Test
	public void testSumLargeEmpty() {
		Assert.assertNull(LargeNumberSumator.sumLarge("", ""));
	}

	@Test
	public void testSumLargeLetters() {
		Assert.assertNull(LargeNumberSumator.sumLarge("123a", "123"));
	}
}
