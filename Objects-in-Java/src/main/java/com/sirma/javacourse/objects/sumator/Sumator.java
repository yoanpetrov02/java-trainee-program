package com.sirma.javacourse.objects.sumator;

import com.sirma.javacourse.intro.summinglargenumbers.LargeNumberSumator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Sumator {

	private static final Pattern IS_NOT_A_DIGIT_PATTERN = Pattern.compile("[^0-9]");

	/**
	 * Private static class constructor.
	 */
	private Sumator() {}

	/**
	 * Takes two integer values and returns their sum.
	 * @param firstNum Integer value
	 * @param secondNum Integer value
	 * @return the sum of firstNum and secondNum as an int value
	 */
	public static int sum(int firstNum, int secondNum) {
		return firstNum + secondNum;
	}

	/**
	 * Takes two long values and returns their sum.
	 * @param firstNum Long value
	 * @param secondNum Long value
	 * @return the sum of firstNum and secondNum as a long value
	 */
	public static long sum(long firstNum, long secondNum) {
		return firstNum + secondNum;
	}

	/**
	 * Takes two float values and returns their sum.
	 * @param firstNum Float value
	 * @param secondNum Float value
	 * @return the sum of firstNum and secondNum as a float value
	 */
	public static float sum(float firstNum, float secondNum) {
		return firstNum + secondNum;
	}

	/**
	 * Takes two double values and returns their sum.
	 * @param firstNum Double value
	 * @param secondNum Double value
	 * @return the sum of firstNum and secondNum as a double value
	 */
	public static double sum(double firstNum, double secondNum) {
		return firstNum + secondNum;
	}

	/**
	 * Takes two BigInteger objects and returns a new BigInteger with the sum of their values as its value.
	 * @param firstNum BigInteger object
	 * @param secondNum BigInteger object
	 * @return a BigInteger object which holds the value of the sum of firstNum and secondNum
	 */
	public static BigInteger sum(BigInteger firstNum, BigInteger secondNum) {
		return new BigInteger(
				String.valueOf(firstNum.add(secondNum)));
	}

	/**
	 * Takes two BigDecimal objects and returns a new BigDecimal with the sum of their values as its value.
	 * @param firstNum BigDecimal object
	 * @param secondNum BigDecimal object
	 * @return a BigDecimal object which holds the value of the sum of firstNum and secondNum
	 */
	public static BigDecimal sum(BigDecimal firstNum, BigDecimal secondNum) {
		return new BigDecimal(
				String.valueOf(firstNum.add(secondNum)));
	}

	/**
	 * Takes two String values, representing a number of any length, and returns their sum by calling the sumLarge() method from {@code LargeNumberSumator}.
	 * @param firstNum String value, can only contain numbers
	 * @param secondNum String value, can only contain numbers
	 * @return The sum of the numbers, represented by the strings, string value
	 * @throws IllegalArgumentException if any of the input strings contains characters that are not a number from 0 to 9
	 */
	public static String sum(String firstNum, String secondNum) {
		if (!isValidNumberString(firstNum) || !isValidNumberString(secondNum)) {
			throw new IllegalArgumentException(
					"Input string cannot contain characters, different from digits!");
		}

		return LargeNumberSumator.sumLarge(firstNum, secondNum);
	}

	/**
	 * Checks whether a String passed to sum(String, String) is a valid number String.
	 * @param number Number, represented by a String
	 * @return False if a character different from a digit is found, true otherwise
	 */
	private static boolean isValidNumberString(String number) {
		return !IS_NOT_A_DIGIT_PATTERN.matcher(number).find();
	}
}