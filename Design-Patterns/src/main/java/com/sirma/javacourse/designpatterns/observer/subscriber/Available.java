package com.sirma.javacourse.designpatterns.observer.subscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sirma.javacourse.designpatterns.observer.observable.ObservableEvent;
import com.sirma.javacourse.designpatterns.observer.observable.Stock;

/**
 * Represents a list of available stocks which observes a {@code StockManager}.
 */
public class Available implements Subscriber {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(Available.class);

	private final List<Stock> availableList;

	public Available() {
		availableList = new ArrayList<>();
	}

	/**
	 * Updates the available list based on the type of event.
	 *
	 * @param stock the stock that was added or sold.
	 * @param event the type of the event.
	 */
	@Override
	public void update(Stock stock, ObservableEvent event) {
		if (event == ObservableEvent.ADD) {
			availableList.add(stock);
		} else {
			availableList.remove(stock);
		}
	}

	/**
	 * Prints the state of the subscriber.
	 */
	public void printState() {
		LOGGER.info("Available: {}",
				Arrays.toString(availableList.toArray()));
	}
}
