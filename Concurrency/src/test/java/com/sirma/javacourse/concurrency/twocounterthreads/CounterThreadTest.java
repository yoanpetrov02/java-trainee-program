package com.sirma.javacourse.concurrency.twocounterthreads;

import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.Assert;
import org.junit.Test;

public class CounterThreadTest {

	@Test
	public void testTwoThreads() {
		AtomicBoolean flag = new AtomicBoolean();
		CounterThread testCounter1 = new CounterThread(0, 5, flag);
		CounterThread testCounter2 = new CounterThread(0, 10, flag);

		testCounter1.start();
		testCounter2.start();

		Assert.assertTrue(testCounter2.getCounter() < 10);
	}
}
