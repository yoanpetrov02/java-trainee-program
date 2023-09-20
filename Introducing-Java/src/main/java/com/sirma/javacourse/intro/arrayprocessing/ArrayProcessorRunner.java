package com.sirma.javacourse.intro.arrayprocessing;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayProcessorRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArrayProcessorRunner.class);
	private static final Scanner INPUT = new Scanner(System.in);

	public static void main(String[] args) {
		int size = INPUT.nextInt();
		int[] arr = new int[size];

		for (int i = 0; i < size; i++) {
			arr[i] = INPUT.nextInt();
		}

		ArrayProcessor.print(arr);
		LOGGER.info("The sum of all elements in the array is {}", ArrayProcessor.sum(arr));
		LOGGER.info("The index of the smallest element in the array is {}", ArrayProcessor.getMinElementIndex(arr));
		LOGGER.info("The contiguous sub array with the best sum is {}", ArrayProcessor.subArrayBestSum(arr));
	}
}
