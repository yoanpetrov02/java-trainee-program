package com.sirma.javacourse.collections.lrucache;

public class LRUCacheRunner {

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(5);

		cache.put(1,1);
		cache.put(2,2);
		cache.put(3,3);
		cache.put(4,4);
		cache.put(5,5); // All elements added.
		cache.put(6,6); // Deletes (1,1).
		cache.get(2);	// Puts (2,2) in front. Cache should look like: {(3,3),(4,4),(5,5),(6,6),(2,2)}.
		cache.print();
	}
}
