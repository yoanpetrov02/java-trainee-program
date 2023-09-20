package com.sirma.javacourse.producerconsumer;

/**
 * Demonstrates the functionality of the trader, producer and storage classes.
 */
public class ProducerConsumerRunner {

	public static void main(String[] args) {
		Storage storage = new Storage(5);
		Thread producer1 = new Thread(new Producer(storage));
		Thread producer2 = new Thread(new Producer(storage));
		Thread consumer = new Thread(new Trader(storage));

		producer1.setName("Producer 1");
		producer2.setName("Producer 2");
		consumer.setName("Trader");

		producer1.start();
		producer2.start();
		consumer.start();
	}
}
