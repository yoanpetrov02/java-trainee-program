package com.sirma.javacourse.concurrency.twocounterthreads;

import java.util.concurrent.atomic.AtomicBoolean;

public class CounterThreadRunner {

	public static void main(String[] args) {
		AtomicBoolean flag = new AtomicBoolean(true);
		CounterThread counter1 = new CounterThread(0, 100, flag);
		CounterThread counter2 = new CounterThread(0, 1000, flag);

		counter1.setName("Counter 1");
		counter2.setName("Counter 2");

		counter1.start();
		counter2.start();
	}
}
