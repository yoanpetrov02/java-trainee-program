package com.sirma.javacourse.producerconsumer;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sirma.javacourse.producerconsumer.exceptions.AddToFullStorageException;
import com.sirma.javacourse.producerconsumer.exceptions.TakeFromEmptyStorageException;

/**
 * Stores products. A Producer can put products in the storage, and a Consumer can take them from it.
 */
public class Storage {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(Storage.class);

	private Product[] products;
	private int size;

	/**
	 * Constructs a new {@code Storage} with the specified capacity.
	 *
	 * @param capacity the maximum capacity of the storage.
	 */
	public Storage(int capacity) {
		products = new Product[capacity];
		this.size = 0;
	}

	/**
	 * Adds a new product to the storage, if the storage isn't full.
	 * Otherwise, throws an {@code AddToFullStorageException}.
	 *
	 * @param product the new product to be added to the storage.
	 * @throws AddToFullStorageException if the storage is already full.
	 */
	public void add(Product product)
			throws AddToFullStorageException {
		if (size >= products.length) {
			throw new AddToFullStorageException(
					"Product cannot be added because the storage is full!");
		}

		products[size] = product;
		size++;
	}

	/**
	 * Removes the last element from the storage, if the storage isn't empty, and returns it.
	 * Otherwise, throws a {@code RemoveFromEmptyListException}.
	 *
	 * @return the product which was removed.
	 * @throws TakeFromEmptyStorageException if the list is empty.
	 */
	public Product take()
			throws TakeFromEmptyStorageException {
		if (size == 0) {
			throw new TakeFromEmptyStorageException(
					"Product cannot be removed because the storage is empty!");
		}

		size--;
		return products[size];
	}

	public void print() {
		LOGGER.info("Amount of products in storage: {}", size);
	}
}
