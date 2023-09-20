package com.sirma.javacourse.designpatterns.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegerProxyRunner {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(IntegerProxyRunner.class);

	public static void main(String[] args) {
		Integer integer = new Integer(123);
		IntegerProxy proxy = new IntegerProxy(integer);

		sampleMethod(integer); // Can be called with an Integer object.
		sampleMethod(proxy);   // Can be called with an IntegerProxy object.


	}

	/**
	 * Prints information about the given Number instance.
	 * @param number object whose information will be printed.
	 */
	public static void sampleMethod(Number number) {
		LOGGER.info("{}: {}", number.getClass(), number.getValue());
	}
}
