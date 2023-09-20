package com.sirma.javacourse.intro.summinglargenumbers;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LNSRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(LNSRunner.class);
	private static final Scanner INPUT = new Scanner(System.in);
	public static void main(String[] args) {
		String numberA = INPUT.next();
		String numberB = INPUT.next();

		LOGGER.info("Sum of the numbers is {}", LargeNumberSumator.sumLarge(numberA, numberB));
	}
}