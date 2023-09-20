package com.sirma.javacourse.exceptions.readnumbers;

/**
 * This exception is thrown when an input number is outside a specified range.
 */
public class NumberOutOfRangeException extends Exception{

	/**
	 * Constructs a new {@code NumberOutOfRangeException} with an error message.
	 * @param errorMessage the detail message of the exception
	 */
	public NumberOutOfRangeException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * Constructs a new {@code NumberOutOfRangeException} with an error message and the exception which caused it.
	 * @param errorMessage the detail message of the exception
	 * @param err the cause of the exception
	 */
	public NumberOutOfRangeException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
