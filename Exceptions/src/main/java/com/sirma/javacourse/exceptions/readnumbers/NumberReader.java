package com.sirma.javacourse.exceptions.readnumbers;

import java.util.Scanner;

/**
 * This class contains a method that reads an integer number. The purpose of the class is to demonstrate exception throwing and custom exceptions.
 */
public class NumberReader {

	private Scanner input = new Scanner(System.in);

	public NumberReader() {}

	public NumberReader(Scanner input) {
		this.input = input;
	}

	/**
	 * This method reads an integer number from 0 to 100 and returns it.
	 * @return the read number
	 * @throws NumberOutOfRangeException if the input number is less than 0 or larger than 100
	 */
	public int readNumber() throws NumberOutOfRangeException {
		int value = input.nextInt();

		if (value < 0 || value > 100) {
			throw new NumberOutOfRangeException("The input number must be in the range [0, 100]!");
		}

		return value;
	}
}
