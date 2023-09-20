package com.sirma.javacourse.designpatterns.calculator;

/**
 * Concrete Command class that implements an add operation.
 */
public class AddCommand extends Command {

	public AddCommand() {}

	/**
	 * Returns the sum of the two values in the a and b fields of the calculator field.
	 * @param a the first value
	 * @param b the second value
	 * @return the sum of the two values.
	 */
	@Override
	public double execute(Double a, Double b) {
		return a + b;
	}
}
