package com.sirma.javacourse.intro.randomstrings;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RSGRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(RSGRunner.class);
	private static final Scanner INPUT = new Scanner(System.in);
	public static void main(String[] args) {
		int length = INPUT.nextInt();

		LOGGER.info("Generating 10 random strings...");

		for (int i = 0; i < 10; i++) {
			LOGGER.info("Random string " + (i + 1) + ": {}", RandomStringGenerator.generateRandomString(length));
		}
	}
}
