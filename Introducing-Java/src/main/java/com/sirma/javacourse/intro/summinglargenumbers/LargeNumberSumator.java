package com.sirma.javacourse.intro.summinglargenumbers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains logic for summing large numbers, represented by strings.
 */
public final class LargeNumberSumator {

	private static final Logger LOGGER = LoggerFactory.getLogger(LargeNumberSumator.class);

	/**
	 * Utility class private constructor.
	 */
	private LargeNumberSumator() {}

	/**
	 * Finds the sum of two large numbers.
	 * @param firstNumber String value
	 * @param secondNumber String value
	 * @return The sum of the two numbers as a string, null if an exception is caught during execution or empty strings are passed
	 */
	public static String sumLarge(String firstNumber, String secondNumber) {
		try {
			if (firstNumber.length() < secondNumber.length()) {
				String t = firstNumber;
				firstNumber = secondNumber;
				secondNumber = t;
			}
		} catch(NullPointerException e) {
			LOGGER.info("Null has been passed as an argument", e);
			return null;
		}

		if (firstNumber.length() == 0 && secondNumber.length() == 0) {
			return null;
		}

		StringBuilder tempAppendZeros = new StringBuilder(secondNumber);

		while (tempAppendZeros.length() < firstNumber.length()) {
			tempAppendZeros.insert(0, '0');
		}

		secondNumber = tempAppendZeros.toString();

		StringBuilder result = new StringBuilder();

		int carry = 0;
		int sum;

		for (int i = 0; i < firstNumber.length(); i++) {
			try {
				sum = (Integer.parseInt(String.valueOf(firstNumber.charAt(i))) +
						Integer.parseInt(String.valueOf(secondNumber.charAt(i)))) + carry;
			} catch(NumberFormatException e) {
				LOGGER.error("The input string contains a character, different than a number", e);
				return null;
			}

			int temp = sum;

			while (temp > 9) {
				temp %= 10;
			}

			result.append(temp);
			carry = sum / 10;
		}

		if (carry != 0) {
			result.append(carry);
		}

		/* Reverse again before returning */
		return result.toString();
	}
}
