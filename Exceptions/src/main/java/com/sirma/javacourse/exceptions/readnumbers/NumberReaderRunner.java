package com.sirma.javacourse.exceptions.readnumbers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberReaderRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(NumberReaderRunner.class);
	private static final NumberReader READER = new NumberReader();

	public static void main(String[] args) {
		LOGGER.info("Enter a number from 0 to 100:");

		int number;

		try {
			number = READER.readNumber();
		} catch (NumberOutOfRangeException e) {
			LOGGER.error("Error while running number reader", e);
		}
	}
}
