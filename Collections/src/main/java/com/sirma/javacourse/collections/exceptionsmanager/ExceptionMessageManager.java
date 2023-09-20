package com.sirma.javacourse.collections.exceptionsmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class contains logic for adding exception messages to a string.
 * The supported exceptions are kept inside a HashMap, in which the keys are codes from 0 to 2,
 * and the values are the exception messages. By calling the getMessage() method, the user can
 * retrieve the added exception messages, with ';' as a separator between them, under the form of a String.
 * The messages can also be retrieved as an ArrayList using getMessages().
 */
public class ExceptionMessageManager {

	private static final String separator = ";";

	private HashMap<String, String> exceptions;
	private StringBuilder message;

	ExceptionMessageManager() {
		message = new StringBuilder();
		exceptions = new HashMap<>();
		exceptions.put("0", "Invalid card number");
		exceptions.put("1", "Wrong PID");
		exceptions.put("2", "Invalid postal code");
	}

	/**
	 * Appends the given exception message to the message field of the object.
	 * @param message the exception message to be appended.
	 * @throws IllegalArgumentException when the exception message does not exist in the available messages.
	 */
	public void addStringMessage(String message) {
		if (!exceptions.containsValue(message)) {
			throw new IllegalArgumentException("The exception message does not exist!");
		}
		if (this.message.isEmpty()) {
			this.message.append(message);
			return;
		}
		this.message.append(separator).append(message);
	}

	/**
	 * Appends the given exception message to the message field of the object. The message is passed by its message code, or the key corresponding to that message.
	 * @param messageCode the code of the message to be appended.
	 * @throws IllegalArgumentException when the exception message code is not a valid message code.
	 */
	public void addExceptionMessageUsingCode(String messageCode) {
		if (!exceptions.containsKey(messageCode)) {
			throw new IllegalArgumentException("The message code does not correspond to any exception message!");
		}

		addStringMessage(exceptions.get(messageCode));
	}

	public String getMessage() {
		return this.message.toString();
	}

	/**
	 * Splits the message field into an ArrayList of strings and returns it.
	 * @param messagesCombination the message string where the exception messages are split by a separator.
	 * @return An ArrayList containing the different messages.
	 */
	public static ArrayList<String> getMessages(String messagesCombination) {
		String[] splitMessages = messagesCombination.split(separator);
		return new ArrayList<>(List.of(splitMessages));
	}
}
