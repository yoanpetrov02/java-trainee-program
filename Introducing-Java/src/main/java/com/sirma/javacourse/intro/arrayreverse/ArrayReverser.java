package com.sirma.javacourse.intro.arrayreverse;

/**
 * This class contains logic for reversing arrays.
 */
public final class ArrayReverser {

	/**
	 * Utility class private constructor.
	 */
	private ArrayReverser() {}

	/**
	 * Reverses a given integer array.
	 * @param array Integer array
	 */
	public static void reverseArray(int[] array) {
		if (array == null) {
			throw new IllegalArgumentException("Input array cannot be null!");
		}

		for (int i = 0; i < array.length / 2; i++) {
			int temp = array[i];
			array[i] = array[array.length - i - 1];
			array[array.length - i - 1] = temp;
		}
	}
}
