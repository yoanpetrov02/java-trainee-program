package com.sirma.javacourse.exceptions.elementlist;

/**
 * This exception is thrown when an attempt to remove an element from an empty list is made.
 */
public class RemoveFromEmptyListException
		extends Exception {

	/**
	 * Constructs a new {@code RemoveFromEmptyListException} with an error message.
	 * @param errorMessage the detail message of the exception
	 */
	public RemoveFromEmptyListException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * Constructs a new {@code RemoveFromEmptyListException} with an error message and the exception which caused it.
	 * @param errorMessage the detail message of the exception
	 * @param cause the cause of the exception
	 */
	public RemoveFromEmptyListException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}
}
