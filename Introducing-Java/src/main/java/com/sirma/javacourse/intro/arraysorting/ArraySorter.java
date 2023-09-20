package com.sirma.javacourse.intro.arraysorting;

/**
 * This class contains logic for array sorting.
 */
public final class ArraySorter {

	/**
	 * Utility class private constructor.
	 */
	private ArraySorter() {}

	/**
	 * Validates the input and calls quickSortInner if it's valid.
	 * @param array Integer array
	 */
	public static void quickSort(int[] array) {
		if (array == null) {
			throw new IllegalArgumentException("Array cannot be null!");
		}

		quickSortInner(array, 0, array.length - 1);
	}

	/**
	 * Sorts an integer array using the quick sort algorithm.
	 * @param array Integer array
	 * @param startIndex Lowest index
	 * @param endIndex Highest index
	 */
	private static void quickSortInner(int[] array, int startIndex, int endIndex) {
		if (startIndex < endIndex) {
			int pivot = partition(array, startIndex, endIndex);

			quickSortInner(array, startIndex, pivot - 1);
			quickSortInner(array, pivot + 1, endIndex);
		}
	}

	/**
	 * Rearranges the array so that elements smaller than the pivot are put on the left and elements larger than it are put on the right.
	 * @param array Integer array
	 * @param low Lowest index
	 * @param high Highest index
	 * @return the position from where the partitioning is done
	 */
	private static int partition(int[] array, int low, int high) {
		int pivot = array[high];

		int i = (low - 1);

		for (int j = low; j < high; j++) {
			if (array[j] <= pivot) {
				i++;

				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}

		int temp = array[i + 1];
		array[i + 1] = array[high];
		array[high] = temp;

		return (i + 1);
	}
}
