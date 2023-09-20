package com.sirma.javacourse.concurrency.sleepingcounterthreads;

import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SleepCounterThread implements a counter, which counts until it reaches a given value, and invokes {@code sleep()} or {@code wait()}
 * on every iteration of the counting, depending on the which method is called by the user - {@code startWithSleep()} or
 * {@code startWithWait()}. Whenever one of the threads finishes counting, the others stop.
 */
public class SleepCounterThread extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(SleepCounterThread.class);

	private int counter;
	private final int end;
	private AtomicBoolean running;
	private boolean sleep;
	private boolean wait;

	public SleepCounterThread(int start, int end, AtomicBoolean running) {
		counter = start;
		this.end = end;
		this.running = running;
		sleep = true; // By default, sleep will be used.
		wait = false; //
	}

	/**
	 * Starts the counter, using {@code sleep()} on every counting iteration.
	 */
	public void startWithSleep() {
		sleep = true;
		wait = false;
		start();
	}

	/**
	 * Starts the counter, using {@code wait()} on every counting iteration.
	 */
	public void startWithWait() {
		wait = true;
		sleep = false;
		start();
	}

	/**
	 * Starts the counter and counts until the end value is reached, or until the running flag gets set to false.
	 * The counter invokes {@code wait()} or {@code sleep()} on every counting iteration, depending on the invoked start method.
	 * @see #startWithSleep()
	 * @see #startWithWait()
	 */
	@Override
	public void run() {
		while (counter < end && running.get()) {
			try {
				if (sleep) {
					Thread.sleep(1000);
				} else if (wait) {
					synchronized (this) {
						wait(1000);
					}
				}
			} catch (InterruptedException e) {
				LOGGER.info("Counter interrupted.");
			}
			counter++;
			LOGGER.info("Counter value: {}", counter);
		}
		running.set(false);
	}
}
