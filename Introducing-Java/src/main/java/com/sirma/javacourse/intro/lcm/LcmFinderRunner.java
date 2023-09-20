package com.sirma.javacourse.intro.lcm;

import java.util.Scanner;
import org.slf4j.*;

public class LcmFinderRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(LcmFinderRunner.class);
	private static final Scanner INPUT = new Scanner(System.in);

	public static void main(String[] args) {
		int firstNumber = INPUT.nextInt();
		int secondNumber = INPUT.nextInt();

		LOGGER.info("LCM of the two numbers is {}", LcmFinder.findLcm(firstNumber, secondNumber));
	}
}