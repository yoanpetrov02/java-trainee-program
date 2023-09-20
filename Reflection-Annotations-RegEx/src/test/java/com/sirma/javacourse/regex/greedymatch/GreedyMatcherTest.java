package com.sirma.javacourse.regex.greedymatch;

import org.junit.Assert;
import org.junit.Test;

public class GreedyMatcherTest {

	@Test
	public void testReplaceTagsValidInput() {
		String input = "<x><b></b><x>Hello world</x>\n"
				+ "<b>sdfsdf</b><x>Good\n"
				+ "morning</x><x>69</x><x>sdfsdfsdf</x>\n"
				+ "</x>";

		String expected = "<x><b></b><x/>\n"
				+ "<b>sdfsdf</b><x/><x/><x/>\n"
				+ "</x>";

		Assert.assertEquals(expected, GreedyMatcher.replaceTags(input));
	}

	@Test
	public void testReplaceTagsInvalidInput() {
		Assert.assertNull(GreedyMatcher.replaceTags(""));
		Assert.assertNull(GreedyMatcher.replaceTags(null));
	}
}
