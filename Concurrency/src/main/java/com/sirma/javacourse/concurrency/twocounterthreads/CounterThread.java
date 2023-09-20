package com.sirma.javacourse.concurrency.twocounterthreads;

import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CounterThread implements a counter that counts from a start number to an end number. Whenever multiple
 * instances of the class are created, an AtomicBoolean instance should be passed to the constructor, so that
 * they all share that flag. Whenever a thread finishes counting, it sets the flag to false, stopping all other
 * threads from counting.
 */
public class CounterThread extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(CounterThread.class);

	private int counter;
	private final int end;
	private final AtomicBoolean running;

	public CounterThread(int start, int end, AtomicBoolean running) {
		counter = start;
		this.end = end;
		this.running = running;
	}

	/**
	 * Starts the counter and counts until the end value is reached, or until the running flag gets set to false.
	 */
	@Override
	public void run() {
		while (counter < end && running.get()) {
			counter++;
			LOGGER.info("Counter value: {}", counter);
		}
		running.set(false);
	}

	public int getCounter() {
		return counter;
	}
}
