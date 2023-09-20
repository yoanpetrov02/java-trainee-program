package com.sirma.javacourse.concurrency.stopcounter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An instance of this class can be passed to a Thread object. The object will then count up every 1 second,
 * until it reaches the given value, or stop counting if interrupted.
 */
public class Counter implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(Counter.class);

	private final int maxCount;
	private int counter;

	public Counter(int maxCount) {
		this.maxCount = maxCount;
		counter = 0;
	}

	/**
	 * Method responsible for the count loop.
	 */
	@Override
	public void run() {
		while (counter < maxCount) {
			try {
				Thread.sleep(1000);
				counter++;
			} catch (InterruptedException e) {
				LOGGER.info("Counter interrupted. Value: {}", counter);
				return;
			}
		}
		LOGGER.info("Finished counting! Counter value: {}", counter);
	}

	public int getCounter() {
		return counter;
	}
}
