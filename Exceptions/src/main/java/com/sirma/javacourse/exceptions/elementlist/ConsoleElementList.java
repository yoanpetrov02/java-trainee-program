package com.sirma.javacourse.exceptions.elementlist;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@code ConsoleElementList} class provides a console interface for the user to interact with an {@code ElementList}.
 */
public class ConsoleElementList {

	private static final Scanner INPUT = new Scanner(System.in);
	private static final Logger LOGGER =
				LoggerFactory.getLogger(ConsoleElementList.class);

	private final ElementList list;
	private boolean running;

	public ConsoleElementList(int listCapacity) {
		list = new ElementList(listCapacity);
	}

	public ConsoleElementList(ElementList list) {
		this.list = list;
	}

	/**
	 * Start method. Continuously accepts input from the user, parses it and executes the specified commands.
	 */
	public void start() {
		String input;
		running = true;
		printMenu();
		while (running) {
			input = INPUT.nextLine().toLowerCase();
			parseInput(input);
		}
	}

	private void printMenu() {
		LOGGER.info("Menu:");
		LOGGER.info("| add | remove (last element) | print (list content) | menu | exit |");
	}

	/**
	 * Parses the given input, executing the corresponding command.
	 * @param input the input from the user.
	 */
	private void parseInput(String input) {
		switch (input) {
			case "add" -> addToList();
			case "remove" -> removeFromList();
			case "print" -> printList();
			case "menu" -> printMenu();
			case "exit" -> running = false;
			default -> LOGGER.error("Invalid command!");
		}
	}

	/**
	 * Adds the next line from the user as an element in the list. Displays an error message if the list is already full.
	 */
	private void addToList() {
		LOGGER.info("Enter value to add:");
		try {
			list.add(INPUT.nextLine());
			LOGGER.info("Element added!");
		} catch (AddToFullListException e) {
			LOGGER.info("The list is full!");
		}
	}

	/**
	 * Removes the last element from the list. Displays an error message if the list is empty.
	 */
	private void removeFromList() {
		try {
			list.remove();
			LOGGER.info("Last element removed!");
		} catch (RemoveFromEmptyListException e) {
			LOGGER.info("The list is empty!");
		}
	}

	/**
	 * Prints all elements in the list.
	 */
	private void printList() {
		LOGGER.info("Elements: {}", list);
	}
}
