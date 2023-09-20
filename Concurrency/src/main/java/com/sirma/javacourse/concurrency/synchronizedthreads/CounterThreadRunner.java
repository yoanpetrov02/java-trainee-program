package com.sirma.javacourse.concurrency.synchronizedthreads;

import java.util.concurrent.atomic.AtomicBoolean;

public class CounterThreadRunner {

	public static void main(String[] args) {
		AtomicBoolean flag = new AtomicBoolean(true);
		CounterThread counter1 = new CounterThread(0, 100, flag);
		CounterThread counter2 = new CounterThread(0, 100, flag);
		counter1.setName("1");
		counter2.setName("2");

		counter1.start();
		counter2.start();
	}
}
