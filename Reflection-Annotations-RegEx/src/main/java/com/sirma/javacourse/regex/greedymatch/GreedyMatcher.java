package com.sirma.javacourse.regex.greedymatch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains a method that utilizes greedy regex quantifiers to match text between tags.
 */
public class GreedyMatcher {

	private static final Pattern GREEDY_PATTERN = Pattern.compile("<x>[^<>]*</x>");

	/**
	 * Matches all substrings in an {@code <x></x>} tag and replaces them with {@code <x/>}.
	 * @param inputString The input string that contains different tags and strings between them.
	 * @return The input string with the matched substrings replaced, null if inputString is null or empty.
	 */
	public static String replaceTags(String inputString) {
		if (inputString == null ||
			inputString.isEmpty()) {
			return null;
		}

		StringBuilder inputStringBuilder = new StringBuilder(inputString);
		Matcher matcher = GREEDY_PATTERN.matcher(inputString);

		while (matcher.find()) {
			inputStringBuilder.replace(matcher.start(), matcher.end(), "<x/>");

			matcher = GREEDY_PATTERN.matcher(inputStringBuilder.toString());
		}

		return inputStringBuilder.toString();
	}
}