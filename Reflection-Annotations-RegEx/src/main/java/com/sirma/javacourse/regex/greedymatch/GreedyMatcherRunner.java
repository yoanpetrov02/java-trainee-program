package com.sirma.javacourse.regex.greedymatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreedyMatcherRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(GreedyMatcherRunner.class);

	public static void main(String[] args) {
		String input = "<x><b></b><x>Hello world</x>\n"
				+ "<b>sdfsdf</b><x>Good\n"
				+ "morning</x><x>69</x><x>sdfsdfsdf</x>\n"
				+ "</x>";

		LOGGER.info("Input:");
		LOGGER.info("\n{}", input);
		LOGGER.info("Output: ");
		LOGGER.info("\n{}", GreedyMatcher.replaceTags(input));
	}
}
