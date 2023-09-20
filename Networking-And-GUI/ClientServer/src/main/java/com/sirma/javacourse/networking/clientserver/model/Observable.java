package com.sirma.javacourse.networking.clientserver.model;

/**
 * An Observable object can be "observed" by a {@code Subscriber}. Whenever a change in the state of the observable
 * object happens, it notifies its subscribers using one of the notify methods.
 */
public interface Observable {

	void addSubscriber(Subscriber subscriber);

	void notifySubscribers(String message);

	void notifySubscribersError(String errorMessage, Throwable e);
}
