package com.sirma.javacourse.designpatterns.calculator;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a console calculator. The commands are read from the user. The class uses the Command design pattern.
 * After the option has been chosen, the user enters the values for a and b, after which the result is printed.
 */
public class Calculator {

	private double a;
	private double b;
	private double result;

	public Calculator() {}

	/**
	 * Executes the given {@code Command}. The command parameter depends on the calculation option chosen by the user.
	 * @param command the command to be executed.
	 */
	public void executeCommand(Command command) {
		result = command.execute(a, b);
	}

	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	public void setA(double a) {
		this.a = a;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getResult() {
		return result;
	}
}
