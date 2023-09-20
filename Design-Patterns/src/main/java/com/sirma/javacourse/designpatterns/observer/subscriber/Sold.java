package com.sirma.javacourse.designpatterns.observer.subscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sirma.javacourse.designpatterns.observer.observable.ObservableEvent;
import com.sirma.javacourse.designpatterns.observer.observable.Stock;

/**
 * Represents a list of sold stocks which observes a {@code StockManager}.
 */
public class Sold implements Subscriber {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(Sold.class);

	private final List<Stock> soldList;

	public Sold() {
		soldList = new ArrayList<>();
	}

	/**
	 * Adds the {@code Stock} to the list if a sell event is passed.
	 *
	 * @param stock the stock that was sold.
	 * @param event the type of the event.
	 */
	@Override
	public void update(Stock stock, ObservableEvent event) {
		if (event == ObservableEvent.SELL) {
			soldList.add(stock);
		}
	}

	/**
	 * Prints the state of the subscriber.
	 */
	public void printState() {
		LOGGER.info("Sold: {}",
				Arrays.toString(soldList.toArray()));
	}
}
