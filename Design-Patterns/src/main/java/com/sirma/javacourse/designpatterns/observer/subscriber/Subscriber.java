package com.sirma.javacourse.designpatterns.observer.subscriber;

import com.sirma.javacourse.designpatterns.observer.observable.ObservableEvent;
import com.sirma.javacourse.designpatterns.observer.observable.Stock;

/**
 * Classes that implement this interface can observe an {@code Observable} object.
 * Whenever an event occurs in the {@code Observable} object, it notifies all of its subscribers about the event.
 */
public interface Subscriber {

	/**
	 * Updates the subscriber based on the type of event.
	 *
	 * @param stock the stock that was added or sold.
	 * @param event the type of the event.
	 */
	void update(Stock stock, ObservableEvent event);

	/**
	 * Prints the state of the subscriber.
	 */
	void printState();
}
