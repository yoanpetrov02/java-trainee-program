package com.sirma.javacourse.intro.arraymedian;

import com.sirma.javacourse.intro.arrayprocessing.ArrayProcessor;

/**
 * This class contains logic for the median of an array
 */
public final class MedianFinder {

	/**
	 * Utility class private constructor.
	 */
	private MedianFinder() {}

	/**
	 * Finds the index of the array's median and returns it. The median is the element, for which the sum of the elements to the left is the closest to the sum of the ones to the right.
	 * @param array Integer array
	 * @return The index of the median.
	 */
	public static int findMedianIndex(int[] array) {
		if (ArrayProcessor.isNullOrEmpty(array)) {
			return -1;
		}

		int bestDifference = Integer.MAX_VALUE;
		int bestIndex = 0;

		for (int i = 0; i < array.length; i++) {
			int leftSum = findSumInRange(array, 0, i - 1);
			int rightSum = findSumInRange(array, i + 1, array.length - 1);
			int difference = Math.max(leftSum, rightSum) - Math.min(leftSum, rightSum);

			if (difference < bestDifference) {
				bestDifference = difference;
				bestIndex = i;
			}
		}

		return bestIndex + 1;
	}

	/**
	 * Finds the sum of a given range of elements in an array.
	 * @param array Integer array
	 * @param start Start index
	 * @param end End index
	 * @return The sum of the elements in the interval set by start and end.
	 */
	private static int findSumInRange(int[] array, int start, int end) {
		int sum = 0;

		for (int i = start; i <= end; i++) {
			sum += array[i];
		}
		return sum;
	}
}
