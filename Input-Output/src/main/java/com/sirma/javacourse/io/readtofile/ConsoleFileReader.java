package com.sirma.javacourse.io.readtofile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains a method that reads lines from the console and writes them to a file.
 */
public class ConsoleFileReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleFileReader.class);

	private final Scanner input;

	/**
	 * Constructs an object with a default scanner.
	 */
	public ConsoleFileReader() {
		input = new Scanner(System.in);
	}

	/**
	 * Constructs an object with the specified scanner for input.
	 * @param input Scanner object
	 */
	public ConsoleFileReader(Scanner input) {
		this.input = input;
	}

	/**
	 * Scans lines from the console and writes them to a file. The file's name is taken from the first line of the input.
	 */
	public void readToFile() {
		String buffer;
		String name = input.nextLine();

		try (FileWriter out = new FileWriter(name)) {
			while (!".".equals((buffer = input.nextLine()))) {
				out.write(buffer + System.lineSeparator());
			}
		} catch (IOException e) {
			LOGGER.error("Error during operations with the file", e);
		} catch (SecurityException e) {
			LOGGER.error("Security error", e);
		}
	}
}
