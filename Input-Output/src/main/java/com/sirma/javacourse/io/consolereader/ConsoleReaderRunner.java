package com.sirma.javacourse.io.consolereader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleReaderRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleReaderRunner.class);

	public static void main(String[] args) {
		ConsoleReader reader = new ConsoleReader();

		String string;
		char character;
		int integer;
		float floating;

		try {
			string = reader.readString();
			character = reader.readChar();
			integer = reader.readInt();
			floating = reader.readFloat();

			LOGGER.info(string);
			LOGGER.info(String.valueOf(character));
			LOGGER.info(String.valueOf(integer));
			LOGGER.info(String.valueOf(floating));
		} catch (NullPointerException e) {
			LOGGER.info("Error while reading data", e);
		}
	}
}
