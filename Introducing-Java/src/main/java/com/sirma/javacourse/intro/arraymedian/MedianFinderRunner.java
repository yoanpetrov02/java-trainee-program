package com.sirma.javacourse.intro.arraymedian;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MedianFinderRunner {
	private static final Scanner INPUT = new Scanner(System.in);
	private static final Logger LOGGER = LoggerFactory.getLogger(MedianFinderRunner.class);

	public static void main(String[] args) {
		int size = INPUT.nextInt();
		int[] arr = new int[size];

		for (int i = 0; i < size; i++) {
			arr[i] = INPUT.nextInt();
		}

		LOGGER.info("The index of the median of the array is {}", MedianFinder.findMedianIndex(arr));
	}
}
