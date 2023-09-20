package com.sirma.javacourse.designpatterns.calculator;

/**
 * Concrete Command class that implements a multiply operation.
 */
public class MultiplyCommand extends Command {

	public MultiplyCommand() {}

	/**
	 * Returns the product of the two values in the a and b fields of the calculator field.
	 * @param a the first value
	 * @param b the second value
	 * @return the product of the two values.
	 */
	@Override
	public double execute(Double a, Double b) {
		return a * b;
	}
}
