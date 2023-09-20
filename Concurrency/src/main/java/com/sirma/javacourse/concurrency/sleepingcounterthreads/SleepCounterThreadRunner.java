package com.sirma.javacourse.concurrency.sleepingcounterthreads;

import java.util.concurrent.atomic.AtomicBoolean;

public class SleepCounterThreadRunner {

	public static void main(String[] args) throws InterruptedException {
		AtomicBoolean flag = new AtomicBoolean(true);
		SleepCounterThread counter1 = new SleepCounterThread(0, 10, flag);
		SleepCounterThread counter2 = new SleepCounterThread(0, 20, flag);
		counter1.setName("Counter 1");
		counter2.setName("Counter 2");

		counter1.startWithSleep();
		counter2.startWithSleep();

		counter2.join();

		AtomicBoolean flag2 = new AtomicBoolean(true);
		SleepCounterThread counter3 = new SleepCounterThread(0, 10, flag2);
		SleepCounterThread counter4 = new SleepCounterThread(0, 20, flag2);
		counter3.setName("Counter 3");
		counter4.setName("Counter 4");

		counter3.startWithWait();
		counter4.startWithWait();
	}
}
