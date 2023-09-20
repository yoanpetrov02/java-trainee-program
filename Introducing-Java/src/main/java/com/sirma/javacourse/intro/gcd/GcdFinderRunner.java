package com.sirma.javacourse.intro.gcd;

import java.util.Scanner;
import org.slf4j.*;

public class GcdFinderRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(GcdFinderRunner.class);
	private static final Scanner INPUT = new Scanner(System.in);

	public static void main(String[] args) {
		int firstNumber = INPUT.nextInt();
		int secondNumber = INPUT.nextInt();

		LOGGER.info("GCD of the two numbers is {}", GcdFinder.findGcd(firstNumber, secondNumber));
	}
}