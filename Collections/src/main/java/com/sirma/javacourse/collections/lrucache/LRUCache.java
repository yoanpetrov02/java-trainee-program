package com.sirma.javacourse.collections.lrucache;

import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the Least Recently Used cache data structure. The user can get() or put() key value pairs from/in the
 * cache. Each invocation of get() moves the retrieved pair to the head of the cache. Whenever the capacity is reached,
 * if put() is called with a new key value pair, to add that pair, the tail pair (the one that is least recently used)
 * gets deleted. The implementation uses a LinkedHashMap to be able to access the tail element and add elements to the
 * head.
 */
public class LRUCache {

	private static final Logger LOGGER = LoggerFactory.getLogger(LRUCache.class);

	private final int capacity;

	private Map<Object, Object> map;

	/**
	 * Constructs a new cache with the given capacity.
	 * @param capacity the max capacity of the cache.
	 */
	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new LinkedHashMap<>();
	}

	/**
	 * Returns the value that corresponds to the given key.
	 * The pair gets moved to the head of the cache, as it's the most recently used one.
	 * @param key the key of the pair.
	 * @return the value that corresponds to the key, or null if the key does not exist in the cache.
	 */
	public Object get(Object key) {
		if (map.containsKey(key)) {
			Object value = map.get(key);
			map.remove(key);
			map.put(key, value);
			return value;
		}
		return null;
	}

	/**
	 * Puts the key value pair in the cache.
	 * If the capacity is reached, the pair at the tail is deleted, as it's the least recently used one.
	 * @param key the key of the pair.
	 * @param value the value of the pair.
	 */
	public void put(Object key, Object value) {
		map.remove(key);
		if (map.size() == capacity) {
			map.remove(map.keySet().iterator().next());
		}
		map.put(key, value);
	}

	/**
	 * Prints the elements in the cache in a formatted way.
	 */
	public void print() {
		LOGGER.info(map.toString());
	}
}
