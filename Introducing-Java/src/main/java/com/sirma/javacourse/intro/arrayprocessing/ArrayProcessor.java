package com.sirma.javacourse.intro.arrayprocessing;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains methods for array processing.
 */
public final class ArrayProcessor {

	/**
	 * Used for printing arrays.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ArrayProcessor.class);

	/**
	 * Utility class private constructor.
	 */
	private ArrayProcessor() {}

	/**
	 * Returns the index of the smallest element in a given array.
	 * @param array Integer array
	 * @return The index of the smallest element in the array, -1 if the array is empty or null
	 */
	public static int getMinElementIndex(int[] array) {
		if (isNullOrEmpty(array)) {
			return -1;
		}

		int smallest = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] < array[smallest]) {
				smallest = i;
			}
		}
		return smallest;
	}

	/**
	 * Returns the sum of the elements in the given array.
	 * @param array Integer array
	 * @return The sum of all elements in the array
	 */
	public static int sum(int[] array) {
		if (isNullOrEmpty(array)) {
			return 0;
		}

		int sum = 0;

		for (int i : array) {
			sum += i;
		}
		return sum;
	}

	/**
	 *  Prints the array's elements.
	 * @param array Integer array
	 */
	public static void print(int[] array) {
		if (array == null) {
			throw new IllegalArgumentException("Array cannot be null!");
		}

		LOGGER.info(Arrays.toString(array));
	}

	/**
	 * Finds the contiguous sub array with the highest sum using Kadane's Algorithm.
	 * @param array Integer array
	 * @return The sum of the highest contiguous sub array
	 */
	public static int subArrayBestSum(int[] array) {
		if (array == null) {
			throw new IllegalArgumentException("Array cannot be null!");
		}
		if (array.length == 0) {
			return 0;
		}

		int localMax = 0;
		int globalMax = Integer.MIN_VALUE;

		for (int j : array) {
			localMax = Math.max(j, j + localMax);
			if (localMax > globalMax) {
				globalMax = localMax;
			}
		}

		return globalMax;
	}

	/**
	 * Checks if the given array is null or empty.
	 * @param array Integer array
	 * @return Boolean value
	 */
	public static boolean isNullOrEmpty(int[] array) {
		return (array == null || array.length == 0);
	}
}
