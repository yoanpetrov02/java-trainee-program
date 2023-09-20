package com.sirma.javacourse.exceptions.elementlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An {@code ElementListAdder} instance continuously adds values to an {@code ElementList},
 * until the list is full, which makes the adder wait until elements are removed.
 */
public class ElementListAdder
		implements Runnable {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(ElementListAdder.class);

	private final ElementList list;

	public ElementListAdder(ElementList list) {
		this.list = list;
	}

	/**
	 * Adds elements to the list until it reaches max size. Whenever max size is reached, waits until elements
	 * are removed from the list.
	 */
	@Override
	public void run() {
		int number = 0;
		while (true) {
			synchronized (list) {
				try {
					list.add(number++);
					LOGGER.info("Elements in the list: {}", list);
					list.notifyAll();
				} catch (AddToFullListException e) {
					try {
						LOGGER.info("Waiting for an element to be removed.");
						list.wait();
					} catch (InterruptedException ex) {
						LOGGER.error("Add thread interrupted.");
						return;
					}
				}
			}
		}
	}
}
