package com.sirma.javacourse.networking.clientinfo.model;

/**
 * A Subscriber object can observe an {@code Observable} object. Whenever a change in the state of the {@code Observable}
 * occurs, it notifies its subscribers by calling their update() method.
 */
public interface Subscriber {

	/**
	 * Notifies the subscriber by sending the given message to it.
	 *
	 * @param message the update message.
	 */
	void update(String message);
}
