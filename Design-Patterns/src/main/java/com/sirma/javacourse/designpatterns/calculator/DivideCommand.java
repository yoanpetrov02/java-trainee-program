package com.sirma.javacourse.designpatterns.calculator;

/**
 * Concrete Command class that implements a divide operation.
 */
public class DivideCommand extends Command {

	public DivideCommand() {}

	/**
	 * Returns the quotient of the two values in the a and b fields of the calculator field.
	 * @param a the first value
	 * @param b the second value
	 * @return the quotient of the two values.
	 */
	@Override
	public double execute(Double a, Double b) {
		if (b.equals(0.0)) {
			throw new IllegalArgumentException("Division by zero is not allowed!");
		}

		return a / b;
	}
}
