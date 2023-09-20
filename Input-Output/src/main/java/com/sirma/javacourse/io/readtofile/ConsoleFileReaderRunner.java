package com.sirma.javacourse.io.readtofile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleFileReaderRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleFileReaderRunner.class);

	public static void main(String[] args) {
		ConsoleFileReader reader = new ConsoleFileReader();

		reader.readToFile();
	}
}
