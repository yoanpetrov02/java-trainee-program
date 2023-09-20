package com.sirma.javacourse.collections.pagebean;

import java.util.ArrayList;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains logic for using user input to invoke an ElementPageManager instance's methods.
 */
public class ElementPageManagerPrinter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ElementPageManagerPrinter.class);

	private ElementPageManager instance;

	public ElementPageManagerPrinter(ElementPageManager instance) {
		this.instance = instance;
	}

	/**
	 * This method should be used to start the program. The instance's first page is printed,
	 * then a menu of options is printed and depending on the user's input,
	 * different {@code ElementPageManager} methods are invoked.
	 */
	public void start() {
		printPage(instance.next());

		final Scanner INPUT = new Scanner(System.in);

		String input = "";

		while (!"0".equals(input)) {
			LOGGER.info("| '<' previous page "
					+ "| '>' next page "
					+ "| 'c' current page "
					+ "| 'f' first page "
					+ "| 'l' last page "
					+ "| 'p' set page size "
					+ "| '0' exit |");
			LOGGER.info("Choice: ");
			input = INPUT.nextLine();

			switch (input) {
				case "<" -> printPage(instance.previous());
				case ">" -> printPage(instance.next());
				case "c", "C" -> LOGGER.info("Current page is " + instance.getCurrentPageNumber());
				case "f", "F" -> printPage(instance.firstPage());
				case "l", "L" -> printPage(instance.lastPage());
				case "p", "P" -> {
					LOGGER.info("Enter a new page size: ");
					instance.setPageSize(Integer.parseInt(INPUT.nextLine()));
					printPage(instance.next());
				}
				case "0" -> LOGGER.info("Exiting...");
				default -> LOGGER.info("Invalid operation code!");
			}
		}
	}

	/**
	 * Prints the contents of the given page in a formatted way.
	 * @param page the page to be printed.
	 */
	private void printPage(ArrayList<Object> page) {
		if (page == null) {
			return;
		}
		LOGGER.info("---------- PAGE {} ----------", instance.getCurrentPageNumber());

		for (Object element : page) {
			LOGGER.info(element.toString());
		}
	}
}
