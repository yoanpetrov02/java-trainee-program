package com.sirma.javacourse.regex.emailvalidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains a method that finds whether a given
 * String is a valid email address by using regular expressions.
 */
public final class EmailValidator {

	/**
	 * Regex pattern used in isValidEmail().
	 */
	private static final Pattern emailPattern =
			Pattern.compile("[a-zA-Z0-9-.]+@[a-zA-Z0-9-.]+");

	/**
	 * Utility class private constructor.
	 */
	private EmailValidator() {}

	/**
	 * Returns a boolean value based on whether the given String is a valid email address.
	 * @param emailString String to be validated
	 * @return true if the String is a valid email address, false if it's not, or if the input is null
	 */
	public static boolean isValidEmail(String emailString) {
		if (emailString == null) {
			return false;
		}

		Matcher emailMatcher = emailPattern.matcher(emailString);

		return emailMatcher.matches();
	}
}
