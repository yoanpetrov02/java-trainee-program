package com.sirma.javacourse.producerconsumer.exceptions;

/**
 * Thrown when an attempt to call take() on an empty Storage instance is made.
 */
public class TakeFromEmptyStorageException
		extends Exception {

	/**
	 * Constructs a new {@code TakeFromEmptyStorageException} with an error message.
	 * @param errorMessage the detail message of the exception.
	 */
	public TakeFromEmptyStorageException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * Constructs a new {@code TakeFromEmptyStorageException} with an error message and the exception which caused it.
	 * @param errorMessage the detail message of the exception.
	 * @param cause the cause of the exception.
	 */
	public TakeFromEmptyStorageException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}
}
