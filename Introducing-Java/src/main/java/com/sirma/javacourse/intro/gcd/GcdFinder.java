package com.sirma.javacourse.intro.gcd;

/**
 * This class contains logic for greatest common divisors of numbers.
 */
public final class GcdFinder {

	/**
	 * Utility class private constructor.
	 */
	private GcdFinder() {}

	/**
	 * Validates the input values and calls the findGcdInternal() method.
	 * @param firstNumber integer value
	 * @param secondNumber integer value
	 * @return The GCD of firstNumber and secondNumber
	 */
	public static int findGcd(int firstNumber, int secondNumber) {
		if (firstNumber == 0 || secondNumber == 0) {
			throw new IllegalArgumentException("Number cannot be 0.");
		}
		return findGcdInternal(Math.abs(firstNumber), Math.abs(secondNumber));
	}

	/**
	 * This method returns the greatest common divisor of two given numbers using the Euclidean algorithm.
	 * @param firstNumber integer value
	 * @param secondNumber integer value
	 * @return The GCD of firstNumber and secondNumber
	 */
	private static int findGcdInternal(int firstNumber, int secondNumber) {
		int remainder = firstNumber % secondNumber;

		if (remainder == 0) {
			return secondNumber;
		}
		return findGcdInternal(secondNumber, remainder);
	}
}