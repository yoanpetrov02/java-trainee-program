package com.sirma.javacourse.libraries.packageintro;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sirma.javacourse.intro.gcd.GcdFinder;

/**
 * This class's purpose is to demonstrate the usage of the reusable .jar created from the Introducing-Java module.
 * The main method of the class calls the GcdFinder class to show that its methods can be accessed.
 */
public class TestClass {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestClass.class);
	private static final Scanner INPUT = new Scanner(System.in);

	public static void main(String[] args) {
		LOGGER.info("Enter two integer numbers to find their GCD: ");
		int a = INPUT.nextInt();
		int b = INPUT.nextInt();

		LOGGER.info("The GCD of the two given numbers is: {}", GcdFinder.findGcd(a, b));
	}
}
