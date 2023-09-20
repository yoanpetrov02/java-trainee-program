package com.sirma.javacourse.intro.lcm;

import com.sirma.javacourse.intro.gcd.GcdFinder;

/**
 * This class contains least common multiple logic.
 */
public final class LcmFinder {

	/**
	 * Utility class private constructor.
	 */
	private LcmFinder() {}

	/**
	 * This method calculates the least common multiple of two numbers.
	 * @param firstNumber Integer value
	 * @param secondNumber Integer value
	 * @return The LCM of firstNumber and secondNumber
	 */
	public static int findLcm(int firstNumber, int secondNumber) {
		if (firstNumber == 0 || secondNumber == 0) {
			throw new IllegalArgumentException("Number cannot be 0.");
		}
		return Math.abs(firstNumber * secondNumber) / GcdFinder.findGcd(firstNumber, secondNumber);
	}
}