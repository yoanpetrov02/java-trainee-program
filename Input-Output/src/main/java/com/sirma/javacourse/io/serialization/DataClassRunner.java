package com.sirma.javacourse.io.serialization;

import java.io.IOException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataClassRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataClassRunner.class);

	private static final Scanner INPUT = new Scanner(System.in);

	public static void main(String[] args) {
		DataClass obj = new DataClass(1, "1", 1.2);

		LOGGER.info("Object info: ");
		obj.printObjectInfo();

		LOGGER.info("Enter the path of the file where you want the object to be saved: ");
		String path = INPUT.nextLine();

		DataClassSerializer.saveObject(path, obj);

		try {
			DataClass retrieved = DataClassSerializer.getObject(path);
			retrieved.printObjectInfo();
		} catch (IOException e) {
			LOGGER.error("Error while getting object", e);
		}
	}
}
