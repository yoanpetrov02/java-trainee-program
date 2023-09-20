package com.sirma.javacourse.concurrency.stopcounter;

import org.junit.Assert;
import org.junit.Test;

public class CounterTest {

	@Test
	public void testCounterFullTime() throws InterruptedException {
		Counter testCounter = new Counter(5);
		Thread testThread = new Thread(testCounter);

		testThread.start();
		testThread.join();

		Assert.assertEquals(5, testCounter.getCounter());
	}

	@Test
	public void testCounterInterrupt() {
		Counter testCounter = new Counter(5);
		Thread testThread = new Thread(testCounter);

		testThread.start();
		testThread.interrupt();

		Assert.assertTrue(
				testCounter.getCounter() < 5 && testThread.isInterrupted());
	}
}
