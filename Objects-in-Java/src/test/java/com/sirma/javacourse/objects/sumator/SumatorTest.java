package com.sirma.javacourse.objects.sumator;

import java.math.BigInteger;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class SumatorTest {

	private static final double DELTA = 1e-15;

	@Test
	public void testSumIntPositive() { Assert.assertEquals(100, Sumator.sum(23, 77)); }

	@Test
	public void testSumIntNegative() { Assert.assertEquals(-55, Sumator.sum(-23,-32)); }

	@Test
	public void testSumLongPositive() {
		Assert.assertEquals(20000000000L, Sumator.sum(10000000000L, 10000000000L));
	}

	@Test
	public void testSumLongNegative() {
		Assert.assertEquals(-20000000000L, Sumator.sum(-10000000000L, -10000000000L));
	}

	@Test
	public void testSumFloatPositive() {
		Assert.assertEquals(5.579, Sumator.sum(2.123, 3.456), DELTA);
	}

	@Test
	public void testSumFloatNegative() {
		Assert.assertEquals(-5.579, Sumator.sum(-2.123, -3.456), DELTA);
	}

	@Test
	public void testSumDoublePositive() {
		Assert.assertEquals(3.3580246803, Sumator.sum(1.1234567891, 2.2345678912), DELTA);
	}

	@Test
	public void testSumBigIntegerPositive() {
		BigInteger firstNum = new BigInteger("123456789123456789123");
		BigInteger secondNum = new BigInteger("123456789123456789123");
		Assert.assertEquals("246913578246913578246",
				Sumator.sum(firstNum, secondNum).toString());
	}

	@Test
	public void testSumBigIntegerNegative() {
		BigInteger firstNum = new BigInteger("-123456789123456789123");
		BigInteger secondNum = new BigInteger("-123456789123456789123");
		Assert.assertEquals("-246913578246913578246",
				Sumator.sum(firstNum, secondNum).toString());
	}

	@Test
	public void testSumBigDecimalPositive() {
		BigDecimal firstNum = new BigDecimal("1.23456789123456789123123123");
		BigDecimal secondNum = new BigDecimal("1.23456789123456789123123123");
		Assert.assertEquals("2.46913578246913578246246246",
				Sumator.sum(firstNum, secondNum).toString());
	}

	@Test
	public void testSumBigDecimalNegative() {
		BigDecimal firstNum = new BigDecimal("-1.23456789123456789123123123");
		BigDecimal secondNum = new BigDecimal("-1.23456789123456789123123123");
		Assert.assertEquals("-2.46913578246913578246246246",
				Sumator.sum(firstNum, secondNum).toString());
	}

	@Test
	public void testSumStringIllegalInput() {
		Assert.assertThrows(IllegalArgumentException.class,
				() -> Sumator.sum("123A", "123A"));
		Assert.assertThrows(IllegalArgumentException.class,
				() -> Sumator.sum("123", "123A"));
		Assert.assertThrows(IllegalArgumentException.class,
				() -> Sumator.sum("123A", "123 "));
	}
}