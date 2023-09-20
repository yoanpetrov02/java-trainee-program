package com.sirma.javacourse.exceptions.readnumbers;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberReaderTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(NumberReaderTest.class);

	@Test
	public void testReadNumberInRange() throws NumberOutOfRangeException {
		String input = "50";

		NumberReader testObject = new NumberReader(
				new Scanner(new ByteArrayInputStream(input.getBytes())));

		Assert.assertEquals(50, testObject.readNumber());
	}

	@Test
	public void testReadNumberOutOfRange() {
		String input = "101";

		NumberReader testObject = new NumberReader(
				new Scanner(new ByteArrayInputStream(input.getBytes())));

		Assert.assertThrows(NumberOutOfRangeException.class, testObject::readNumber);
	}
}
