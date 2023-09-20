package com.sirma.javacourse.io.filereverser;

import java.io.File;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrates how the FileReverser class works.
 */
public class FileReverserRunner {

	private static final Scanner INPUT = new Scanner(System.in);

	private static final Logger LOGGER = LoggerFactory.getLogger(FileReverserRunner.class);

	public static void main(String[] args) {
		LOGGER.info("Enter the path to the file: ");
		String path = INPUT.nextLine();

		File file = new File(path);

		FileReverser.reverseFile(file);
	}
}
