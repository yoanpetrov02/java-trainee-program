package com.sirma.javacourse.designpatterns.calculator;

/**
 * Concrete Command class that implements a power operation.
 */
public class PowerCommand extends Command {

	public PowerCommand() {}

	/**
	 * Returns the first field of the calculator to the power of the second field.
	 * @param a the value
	 * @param b the power
	 * @return the first value to the power of the second value
	 */
	@Override
	public double execute(Double a, Double b) {
		double power = b.intValue();

		return Math.pow(a, power);
	}
}
