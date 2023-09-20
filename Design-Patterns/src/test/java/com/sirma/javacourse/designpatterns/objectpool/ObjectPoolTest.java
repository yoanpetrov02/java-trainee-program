package com.sirma.javacourse.designpatterns.objectpool;

import static org.junit.Assert.*;

import org.junit.Test;

public class ObjectPoolTest {

	@Test
	public void testObjectPoolAcquire() {
		ObjectPool pool = new ObjectPool(5);
		for (int i = 0; i < 5; i++) {
			assertNotNull(pool.acquire());
		}
		assertNull(pool.acquire());
	}

	@Test
	public void testObjectPoolRelease() {
		ObjectPool pool = new ObjectPool(2);
		Object acquired = pool.acquire();
		assertEquals(1, pool.getAvailableAmount());
		assertEquals(1, pool.getUsedAmount());
		pool.release(acquired);
		assertEquals(2, pool.getAvailableAmount());
		assertEquals(0, pool.getUsedAmount());
	}
}
