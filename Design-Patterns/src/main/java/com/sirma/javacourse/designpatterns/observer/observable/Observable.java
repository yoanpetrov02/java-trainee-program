package com.sirma.javacourse.designpatterns.observer.observable;

import com.sirma.javacourse.designpatterns.observer.subscriber.Subscriber;

/**
 * Classes that implement this interface can be observed by {@code Subscriber} objects.
 * Whenever an event occurs in the {@code Observable} object, it notifies all of its subscribers about the event.
 */
public interface Observable {

	/**
	 * Notifies all the object's subscribers about the new event.
	 *
	 * @param stock the stock that was added or sold.
	 * @param event the type of the event.
	 */
	void notifySubscribers(Stock stock, ObservableEvent event);

	/**
	 * Adds a {@code Subscriber} to the subscriber list of the object.
	 *
	 * @param subscriber the new subscriber to be added.
	 */
	void addSubscriber(Subscriber subscriber);

	/**
	 * Removes a {@code Subscriber} from the subscriber list of the object.
	 *
	 * @param subscriber the subscriber to be removed.
	 */
	void removeSubscriber(Subscriber subscriber);
}
