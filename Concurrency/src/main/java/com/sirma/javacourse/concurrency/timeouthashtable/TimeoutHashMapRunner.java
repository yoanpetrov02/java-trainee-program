package com.sirma.javacourse.concurrency.timeouthashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrates how {@code TimeoutHashMap} works.
 */
public class TimeoutHashMapRunner {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(TimeoutHashMapRunner.class);

	public static void main(String[] args) throws InterruptedException {
		TimeoutHashMap map = new TimeoutHashMap(2000);

		map.put("1", 123);

		LOGGER.info("{}", map.get("1")); // Prints 123
		Thread.sleep(1000);
		LOGGER.info("{}", map.get("1")); // Still prints 123
		Thread.sleep(5000);
		LOGGER.info("{}", map.get("1")); // Prints null because timeout has been exceeded
	}
}
