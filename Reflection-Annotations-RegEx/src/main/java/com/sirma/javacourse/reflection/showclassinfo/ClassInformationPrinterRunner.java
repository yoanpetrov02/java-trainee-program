package com.sirma.javacourse.reflection.showclassinfo;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassInformationPrinterRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassInformationPrinterRunner.class);

	public static void main(String[] args) {
		SampleClass a = new SampleClass(1, 2);
		List<String> result = ClassInformationPrinter.getClassData(a);

		LOGGER.info("Class information:");

		for (String member : result) {
			LOGGER.info(member);
		}
	}
}
