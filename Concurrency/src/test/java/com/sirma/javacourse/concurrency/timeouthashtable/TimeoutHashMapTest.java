package com.sirma.javacourse.concurrency.timeouthashtable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class TimeoutHashMapTest {

	private TimeoutHashMap testMap;

	@Before
	public void initialize() {
		testMap = new TimeoutHashMap(2000);
	}

	@Test
	public void testTimeoutMapExpireAfterPut() throws InterruptedException {
		testMap.put("test", 123);
		Thread.sleep(2100);
		assertNull(testMap.get("test"));
	}

	@Test
	public void testTimeoutMapUpdateTimer() throws InterruptedException {
		testMap.put("test", 123);
		Thread.sleep(1000);
		testMap.get("test");
		Thread.sleep(1500);
		assertEquals(123, testMap.get("test"));
	}
}
