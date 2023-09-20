package com.sirma.javacourse.concurrency.stopcounter;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrates how Counter works.
 */
public class CounterThreadRunner {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(CounterThreadRunner.class);

	private static final Scanner INPUT = new Scanner(System.in);

	/**
	 * Creates a Thread by passing a Counter instance to it and starts it. The user is prompted to enter a line from the
	 * keyboard to stop it from counting.
	 * @param args
	 */
	public static void main(String[] args) {
		Counter counter = new Counter(5);
		Thread thread = new Thread(counter);
		thread.start();
		LOGGER.info("The thread is counting. If you wish to stop it, enter any line from the keyboard.");

		INPUT.next();

		thread.interrupt();
	}
}
