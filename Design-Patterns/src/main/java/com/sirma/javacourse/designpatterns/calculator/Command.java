package com.sirma.javacourse.designpatterns.calculator;

/**
 * Abstract Command class. All commands should extend this class in order to be able to be used in {@code Calculator}.
 */
public abstract class Command {

	public Command() {}

	/**
	 * Executes the command, taking the values from the calculator field.
	 * @param a the first value
	 * @param b the second value
	 * @return the result of the execution of the command.
	 */
	public abstract double execute(Double a, Double b);
}
