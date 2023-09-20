package com.sirma.javacourse.designpatterns.objectpool;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectPoolRunner {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(ObjectPoolRunner.class);

	public static void main(String[] args) {
		ArrayList<Object> list = new ArrayList<>();
		ObjectPool pool = new ObjectPool(10);

		LOGGER.info("Capacity of the pool: {}", pool.getCapacity());

		for (int i = 0; i <= 10; i++) { // Calling acquire() 11 times, where on the 11th time it will return null.
			LOGGER.info("Available: {}", pool.getAvailableAmount());
			LOGGER.info("Used: {}", pool.getUsedAmount());
			list.add(pool.acquire());
		}

		LOGGER.info(list.toString()); // Last element of the list is null as there was no available instance.

		for (int i = 0; i <= 10; i++) { // The first iteration will remove the null element at the end, so it won't change the pool.
			LOGGER.info("Available: {}", pool.getAvailableAmount());
			LOGGER.info("Used: {}", pool.getUsedAmount());
			pool.release(list.remove(list.size() - 1));
		}

		LOGGER.info(list.toString());
	}
}
