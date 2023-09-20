package com.sirma.javacourse.intro.randomstrings;

/**
 * This class contains logic for generating random strings.
 */
public final class RandomStringGenerator {

	/**
	 * Contains all characters that can be used by generateRandomString().
	 */
	private static final String allowedChars =
			"QWERTYUIOPASDFGHJKLZXCVBNM" +
			"qwertyuiopasdfghjklzxcvbnm" +
			"0123456789";

	/**
	 * Utility class private constructor.
	 */
	private RandomStringGenerator() {}

	/**
	 * Generates a random string that consists of only letters (uppercase and lowercase) and numbers. The letters are generated using the Math.random() method, where the number returned by it corresponds to the index of a char in allowedChars.
	 * @param length length of the output string
	 * @return Randomly generated string with the given length
	 */
	public static String generateRandomString(int length) {
		if (length < 0) {
			throw new IllegalArgumentException("Length cannot be negative!");
		}

		StringBuilder result = new StringBuilder();
		int next;

		while (result.length() < length) {
			next = (int)(Math.floor(Math.random() * 62));

			result.append(allowedChars.charAt(next));
		}
		return result.toString();
	}
}
