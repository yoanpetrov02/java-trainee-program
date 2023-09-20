package com.sirma.javacourse.intro.arrayreverse;

import java.util.Arrays;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayReverserRunner {

	private static final Scanner INPUT = new Scanner(System.in);
	private static final Logger LOGGER = LoggerFactory.getLogger(ArrayReverserRunner.class);

	public static void main(String[] args) {
		int length = INPUT.nextInt();
		int[] array = new int[length];

		for (int i = 0; i < array.length; i++) {
			array[i] = INPUT.nextInt();
		}

		ArrayReverser.reverseArray(array);

		LOGGER.info("Reversed: {}", Arrays.toString(array));
	}
}
