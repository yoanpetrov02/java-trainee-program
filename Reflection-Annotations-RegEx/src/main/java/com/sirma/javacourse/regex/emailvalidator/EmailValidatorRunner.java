package com.sirma.javacourse.regex.emailvalidator;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailValidatorRunner {

	private static final Scanner INPUT = new Scanner(System.in);
	private static final Logger LOGGER =
			LoggerFactory.getLogger(EmailValidatorRunner.class);

	public static void main(String[] args) {
		String emailString = INPUT.nextLine();

		LOGGER.info("Output: {}",
				EmailValidator.isValidEmail(emailString));
	}
}
