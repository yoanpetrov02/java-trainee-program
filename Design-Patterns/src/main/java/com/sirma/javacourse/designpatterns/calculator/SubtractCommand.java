package com.sirma.javacourse.designpatterns.calculator;

/**
 * Concrete Command class that implements a subtract operation.
 */
public class SubtractCommand extends Command {

	public SubtractCommand() {}

	/**
	 * Returns the difference of the two values in the a and b fields of the calculator field.
	 * @param a the first value
	 * @param b the second value
	 * @return the difference of the two values.
	 */
	@Override
	public double execute(Double a, Double b) {
		return a - b;
	}
}
