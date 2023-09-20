package com.sirma.javacourse.concurrency.synchronizedthreads;

import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CounterThread extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(CounterThread.class);
	private static final Object lock = new Object();

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
			if (counter >= end) {
				synchronized (lock) {
					lock.notifyAll();
				}
				break;
			}
			try {
				synchronized (lock) {
					lock.notifyAll();
					lock.wait();
				}
			} catch (InterruptedException e) {
				LOGGER.error("Thread interrupted.", e);
			}
		}
		running.set(false);
	}
}
