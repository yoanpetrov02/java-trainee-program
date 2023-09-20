package com.sirma.javacourse.exceptions.elementlist;

/**
 * This exception is thrown when an attempt to add an element to a full list is made.
 */
public class AddToFullListException
		extends Exception {

	/**
	 * Constructs a new {@code AddToFullListException} with an error message.
	 * @param errorMessage the detail message of the exception
	 */
	public AddToFullListException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * Constructs a new {@code AddToFullListException} with an error message and the exception which caused it.
	 * @param errorMessage the detail message of the exception
	 * @param cause the cause of the exception
	 */
	public AddToFullListException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}
}
