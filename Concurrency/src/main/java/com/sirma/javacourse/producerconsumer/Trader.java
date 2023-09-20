package com.sirma.javacourse.producerconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sirma.javacourse.producerconsumer.exceptions.TakeFromEmptyStorageException;

/**
 * A Consumer takes products from a Storage.
 */
public class Trader
		implements Runnable {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(Trader.class);

	private static final long DEFAULT_DELAY = 1000L;

	private final Storage storage;
	private final long actionDelayMs;

	/**
	 * Constructs a {@code Trader} with the default action delay.
	 *
	 * @param storage the storage the trader will take products from.
	 */
	public Trader(Storage storage) {
		this.storage = storage;
		actionDelayMs = DEFAULT_DELAY;
	}

	/**
	 * Constructs a {@code Trader} with the specified action delay.
	 *
	 * @param storage the storage the trader will take products from.
	 * @param actionDelayMs the action delay in milliseconds.
	 */
	public Trader(Storage storage, long actionDelayMs) {
		this.storage = storage;
		this.actionDelayMs = actionDelayMs;
	}

	/**
	 * Run method of a trader. The trader will take products from the storage indefinitely.
	 * If the storage is empty, the trader will wait until there are products to take.
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(actionDelayMs);
				synchronized (storage) {
					storage.take();
					LOGGER.info("Took a product!");
					storage.print();
					storage.notifyAll();
				}
			} catch (InterruptedException e) {
				LOGGER.info("Trader thread interrupted.");
			} catch (TakeFromEmptyStorageException e) {
				LOGGER.info("Storage is empty! Waiting for products to be added...");
				try {
					synchronized (storage) {
						storage.wait();
					}
				} catch (InterruptedException ex) {
					LOGGER.info("Trader thread interrupted.");
				}
			}
		}
	}
}
