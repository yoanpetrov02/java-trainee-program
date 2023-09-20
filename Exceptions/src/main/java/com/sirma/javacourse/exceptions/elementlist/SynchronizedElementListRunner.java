package com.sirma.javacourse.exceptions.elementlist;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrates how {@code ElementListAdder} and {@code ElementListRemover} work.
 */
public class SynchronizedElementListRunner {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(SynchronizedElementListRunner.class);

	private static final Scanner INPUT = new Scanner(System.in);

	public static void main(String[] args) {
		ElementList list = new ElementList(5);
		Thread addThread =
				new Thread(new ElementListAdder(list));
		Thread removeThread =
				new Thread(new ElementListRemover(list));

		addThread.setName("Add");
		removeThread.setName("Remove");
		addThread.start();
		removeThread.start();

		INPUT.nextLine();
		addThread.interrupt();
		removeThread.interrupt();
	}
}
