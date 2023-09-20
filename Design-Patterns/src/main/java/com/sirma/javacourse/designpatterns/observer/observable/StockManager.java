package com.sirma.javacourse.designpatterns.observer.observable;

import java.util.ArrayList;
import java.util.List;
import com.sirma.javacourse.designpatterns.observer.subscriber.Subscriber;

/**
 * An observable stock manager. Each time a stock is added or sold, the manager notifies its subscribers
 * with the {@code Stock} that is connected to the event and the type of the event (add/sell).
 */
public class StockManager implements Observable {

	private final List<Subscriber> subscribers;

	public StockManager() {
		subscribers = new ArrayList<>();
	}

	/**
	 * Adds a new stock to the list of available stocks.
	 * @param stock the stock to be added.
	 */
	public void add(Stock stock) {
		notifySubscribers(stock, ObservableEvent.ADD);
	}

	/**
	 * Removes a stock from the list of available stocks and adds it to the list of sold stocks.
	 * @param stock the stock to be sold.
	 */
	public void sell(Stock stock) {
		notifySubscribers(stock, ObservableEvent.SELL);
	}

	/**
	 * Notifies the manager's subscribers with an add or sell event.
	 *
	 * @param stock the stock that was added or sold.
	 * @param event the type of the event.
	 */
	@Override
	public void notifySubscribers(Stock stock, ObservableEvent event) {
		subscribers.forEach(subscriber ->
				subscriber.update(stock, event));
	}

	/**
	 * Adds a {@code Subscriber} to the subscriber list of the manager.
	 *
	 * @param subscriber the new subscriber to be added.
	 */
	@Override
	public void addSubscriber(Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	/**
	 * Removes a {@code Subscriber} from the subscriber list of the manager.
	 *
	 * @param subscriber the subscriber to be removed.
	 */
	@Override
	public void removeSubscriber(Subscriber subscriber) {
		subscribers.remove(subscriber);
	}

	/**
	 * Prints the state of the manager's subscribers.
	 */
	public void printState() {
		subscribers.forEach((Subscriber::printState));
	}
}
