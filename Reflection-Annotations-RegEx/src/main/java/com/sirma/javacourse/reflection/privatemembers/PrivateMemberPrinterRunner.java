package com.sirma.javacourse.reflection.privatemembers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrivateMemberPrinterRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrivateMemberPrinterRunner.class);

	public static void main(String[] args) {
		SampleClass sampleObject = new SampleClass(123, 321);

		LOGGER.info("Value: {}", PrivateMemberPrinter.invokePrivateMethod(sampleObject));
		LOGGER.info("Private fields:");

		for (Object field : PrivateMemberPrinter.getPrivateFields(sampleObject)) {
			LOGGER.info(field.toString());
		}

		PrivateMemberPrinter.getPrivateFields(sampleObject);
	}
}
