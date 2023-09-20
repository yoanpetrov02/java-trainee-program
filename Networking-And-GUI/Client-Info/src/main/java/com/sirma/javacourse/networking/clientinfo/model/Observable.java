package com.sirma.javacourse.networking.clientinfo.model;

/**
 * An Observable object can have subscribers. Whenever a change in the state of the observed object happens, it notifies
 * its subscribers by calling their update() method.
 */
public interface Observable {

	/**
	 * Adds a subscriber to this object's subscriber list.
	 *
	 * @param s the new subscriber.
	 */
	void addSubscriber(Subscriber s);

	/**
	 * Notifies all subscribers with the given message by calling their update() method.
	 *
	 * @param message the message that will be sent to all subscribers.
	 */
	void notifySubscribers(String message);
}
