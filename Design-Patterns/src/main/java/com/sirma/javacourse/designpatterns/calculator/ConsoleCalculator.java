package com.sirma.javacourse.designpatterns.calculator;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleCalculator {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleCalculator.class);

	private static final Scanner INPUT = new Scanner(System.in);

	private final Calculator calculator;
	private final CommandFactory factory;

	public ConsoleCalculator() {
		this(new Calculator());
	}

	public ConsoleCalculator(Calculator calculator) {
		this.calculator = calculator;
		factory = new CommandFactory();
	}

	/**
	 * Starts the calculator in the console. The user is continuously prompted to enter a calculation option and
	 * arguments, after which those options are passed to the {@code ConsoleCalculator}'s {@code Calculator}, which
	 * does the calculation, and the result gets printed in the console.
	 */
	public void start() {
		String input = "";

		while (!"exit".equals(input)) {
			printMenu();
			input = INPUT.nextLine();

			if ("exit".equals(input)) {
				continue;
			}

			enterValues();
			Command command = factory.createCommand(input);
			if (command == null) {
				LOGGER.error("Invalid command!");
				continue;
			}
			calculator.executeCommand(command);
			printResult();
		}
	}

	/**
	 * Prints the menu of options in the console in a formatted way.
	 */
	private void printMenu() {
		LOGGER.info("--- CALCULATOR ---");
		LOGGER.info("");
		LOGGER.info("Choose an option:");
		LOGGER.info("| Add (+) | Subtract (-) | Multiply (*) | Divide (/) | Power (^) | Exit (exit) |");
		LOGGER.info("> ");
	}

	/**
	 * Prompts the user to enter values for the a and b fields and sets them to those.
	 */
	private void enterValues() {
		LOGGER.info("Enter A and B: ");
		calculator.setA(Double.parseDouble(INPUT.nextLine()));
		calculator.setB(Double.parseDouble(INPUT.nextLine()));
	}

	/**
	 * Prints the result of executing the command chosen by the user, which is saved in the result field.
	 */
	private void printResult() {
		LOGGER.info("Result: {}", calculator.getResult());
		LOGGER.info("");
	}
}
