package com.sirma.javacourse.intro.arraysorting;

import java.util.Arrays;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArraySorterRunner {

	private static final Scanner INPUT = new Scanner(System.in);
	private static final Logger LOGGER = LoggerFactory.getLogger(ArraySorterRunner.class);

	public static void main(String[] args) {
		int length = INPUT.nextInt();
		int[] array = new int[length];

		for (int i = 0; i < length; i++) {
			array[i] = INPUT.nextInt();
		}

		LOGGER.info("Unsorted: {}", Arrays.toString(array));
		ArraySorter.quickSort(array);
		LOGGER.info("Sorted:   {}", Arrays.toString(array));
	}
}
