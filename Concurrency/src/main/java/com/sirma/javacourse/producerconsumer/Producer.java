package com.sirma.javacourse.producerconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sirma.javacourse.producerconsumer.exceptions.AddToFullStorageException;

/**
 * A Producer adds products to a Storage.
 */
public class Producer
		implements Runnable {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(Producer.class);

	private static final long DEFAULT_DELAY = 1000L;

	private final Storage storage;
	private final long actionDelayMs;

	/**
	 * Constructs a {@code Producer} with the default action delay.
	 *
	 * @param storage the storage the producer will add products to.
	 */
	public Producer(Storage storage) {
		this.storage = storage;
		actionDelayMs = DEFAULT_DELAY;
	}

	/**
	 * Constructs a {@code Producer} with the specified action delay.
	 *
	 * @param storage the storage the producer will add products to.
	 * @param actionDelayMs the action delay in milliseconds.
	 */
	public Producer(Storage storage, long actionDelayMs) {
		this.storage = storage;
		this.actionDelayMs = actionDelayMs;
	}

	/**
	 * Run method of a producer. The producer will add products to the storage indefinitely.
	 * If the storage is full, the producer will wait until there is free space before adding a new product.
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(actionDelayMs);
				synchronized (storage) {
					storage.add(new Product());
					LOGGER.info("Added a product!");
					storage.print();
					storage.notifyAll();
				}
			} catch (AddToFullStorageException e) {
				LOGGER.info("Storage is full! Waiting for products to be taken...");
				try {
					synchronized (storage) {
						storage.wait();
					}
				} catch (InterruptedException ex) {
					LOGGER.info("Producer thread interrupted.");
				}
			} catch (InterruptedException e) {
				LOGGER.info("Producer thread interrupted.");
			}
		}
	}
}
