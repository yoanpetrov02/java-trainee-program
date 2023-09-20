package com.sirma.javacourse.producerconsumer.exceptions;

/**
 * Thrown when an attempt to call add() on a full Storage instance is made.
 */
public class AddToFullStorageException
		extends Exception {

	/**
	 * Constructs a new {@code AddToFullStorageException} with an error message.
	 * @param errorMessage the detail message of the exception.
	 */
	public AddToFullStorageException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * Constructs a new {@code AddToFullStorageException} with an error message and the exception which caused it.
	 * @param errorMessage the detail message of the exception.
	 * @param cause the cause of the exception.
	 */
	public AddToFullStorageException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}
}
