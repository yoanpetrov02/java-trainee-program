package com.sirma.javacourse.io.consolereader;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains methods for reading different data types from the console.
 * Note: the read methods return a wrapper object of the primitive type that they read.
 */
public class ConsoleReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleReader.class);

	private Scanner input;

	/**
	 * Constructs a new {@code ConsoleReader} object with a default scanner.
	 */
	public ConsoleReader() {
		input = new Scanner(System.in).useLocale(Locale.getDefault());
	}

	/**
	 * Constructs a new {@code ConsoleReader} object using the specified scanner.
	 * @param input Scanner for the newly constructed {@code ConsoleReader}
	 */
	public ConsoleReader(Scanner input) {
		this.input = input;
	}

	/**
	 * Reads a string from the console.
	 * @return The read string
	 */
	public String readString() {
		return input.nextLine();
	}

	/**
	 * Reads an integer from the console.
 	 * @return An {@code Integer} object with the read number, null if {@code InputMismatchException} occurs
	 */
	public Integer readInt() {
		try {
			return input.nextInt();
		} catch (InputMismatchException e) {
			LOGGER.error("The entered integer is not in the expected format or out of bounds", e);
			return null;
		}
	}

	/**
	 * Reads the first character from the entered line.
	 * @return A {@code Character} object with the read character, null if {@code IndexOutOfBoundsException} occurs
	 */
	public Character readChar() {
		try {
			return input.nextLine().charAt(0);
		} catch (IndexOutOfBoundsException e) {
			LOGGER.error("An empty string has been entered", e);
			return null;
		}
	}

	/**
	 * Reads a float from the console.
	 * @return A {@code Float} object with the read number, null if {@code InputMismatchException} occurs
	 */
	public Float readFloat() {
		try {
			return input.nextFloat();
		} catch (InputMismatchException e) {
			LOGGER.error("The entered float is not in the expected format or out of bounds", e);
			return null;
		}
	}
}
