package com.sirma.javacourse.collections.lrucache;

import org.junit.Assert;
import org.junit.Test;

public class LRUCacheTest {

	@Test
	public void testLRUCacheGet() {
		LRUCache testObject = new LRUCache(5);
		testObject.put(1,1);
		testObject.put(2,2);
		testObject.put(3,3);
		testObject.put(4,4);
		testObject.put(5,5);

		Assert.assertEquals(1, testObject.get(1));
		Assert.assertEquals(2, testObject.get(2));
		Assert.assertEquals(3, testObject.get(3));
		Assert.assertEquals(4, testObject.get(4));
		Assert.assertEquals(5, testObject.get(5));
	}

	@Test
	public void testLRUCachePut() {
		LRUCache testObject = new LRUCache(5);
		testObject.put(1,1);
		Assert.assertEquals(1, testObject.get(1));
		testObject.put(1,2);
		Assert.assertEquals(2, testObject.get(1));
	}

	@Test
	public void testLRUCacheDeletion() {
		LRUCache testObject = new LRUCache(5);

		testObject.put(1,1);
		testObject.put(2,2);
		testObject.put(3,3);
		testObject.put(4,4);
		testObject.put(5,5);

		testObject.put(6,6);
		Assert.assertNull(testObject.get(1));

		testObject.get(2);
		testObject.put(7,7);
		Assert.assertNull(testObject.get(3));
	}
}
