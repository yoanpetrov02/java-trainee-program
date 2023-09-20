package com.sirma.javacourse.exceptions.elementlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An {@code ElementListRemover} instance continuously removes values from an {@code ElementList},
 * until the list is empty, which makes the remover wait until new elements are added.
 */
public class ElementListRemover
		implements Runnable {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(ElementListRemover.class);

	private final ElementList list;

	public ElementListRemover(ElementList list) {
		this.list = list;
	}

	/**
	 * Removes elements from the list until it's empty. Whenever that happens, waits until new elements
	 * are added to the list.
	 */
	@Override
	public void run() {
		while (true) {
			synchronized (list) {
				try {
					list.remove();
					LOGGER.info("Elements in the list: {}", list);
					list.notifyAll();
				} catch (RemoveFromEmptyListException e) {
					try {
						LOGGER.info("Waiting for an element to be added.");
						list.wait();
					} catch (InterruptedException ex) {
						LOGGER.error("Remove thread interrupted.");
						return;
					}
				}
			}
		}
	}
}
