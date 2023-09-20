package com.sirma.javacourse.regex.ibanvalidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * This class contains logic for validating IBANs using regular expressions.
 */
public final class IbanValidator {

	/**
	 * Pattern that matches valid bulgarian IBANs.
	 */
	private static final Pattern IBAN_PATTERN = Pattern.compile(
			"BG[0-9]{2} [A-Z0-9]{4} [A-Z0-9]{4} [A-Z0-9]{4} [A-Z0-9]{4} [A-Z0-9]{4}(?=</iban>)");

	/**
	 * Takes the input string and replaces all valid bulgarian IBANs in it, which are between an <iban></iban> tag, with "****", leaving only the last 4 digits of it unhidden.
	 * @param ibanString The input string, containing the IBANs
	 * @return The same string with hidden IBANs, null if the input is empty or null
	 */
	public static String validateIbans(String ibanString) {
		if (StringUtils.isEmpty(ibanString)) {
			return null;
		}

		StringBuilder ibanStringBuilder = new StringBuilder(ibanString);

		Matcher matcher = IBAN_PATTERN.matcher(ibanStringBuilder.toString());

		while (matcher.find()) {
			ibanStringBuilder.replace(matcher.start(), matcher.end() - 4, "****");
			matcher = IBAN_PATTERN.matcher(ibanStringBuilder.toString());
		}

		return ibanStringBuilder.toString();
	}
}
