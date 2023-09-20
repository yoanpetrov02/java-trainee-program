package com.sirma.javacourse.concurrency.timeouthashtable;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A hash map with a timeout. The timeout, in milliseconds, represents the amount of time after which the key gets deleted
 * from the map if it hasn't been updated in any way (by calling put() or get()). Whenever a key-value pair gets updated,
 * an expiration check is scheduled for when the key is supposed to expire. That way, each expired key can be removed automatically
 * whenever it expires.
 */
public class TimeoutHashMap {

	private final Map<String, Object> elements;
	private final Map<String, Long> timers;
	private final long timeout;
	private final ScheduledRemover remover;

	public TimeoutHashMap(long timeoutMs) {
		elements = new ConcurrentHashMap<>();
		timers = new LinkedHashMap<>();
		timeout = timeoutMs;
		remover = new ScheduledRemover();
	}

	/**
	 * Puts the key-value pair in the map if it doesn't exist,
	 * or updates the existing value for the key, if it already exists.
	 * Schedules an expiration check for the key.
	 *
	 * @param key the key to associate the given value with.
	 * @param value the value to be associated with the given key.
	 */
	public synchronized void put(String key, Object value) {
		elements.put(key, value);
		timers.put(key, System.currentTimeMillis());
		remover.scheduleCheckFor(key);
	}

	/**
	 * Returns the value, associated with the given key, if it exists in the map.
	 * Schedules an expiration check for the key if it exists.
	 *
	 * @param key the key to get the value for.
	 * @return the value, associated with the given key, or null if the key does not have a mapping.
	 */
	public synchronized Object get(String key) {
		long currentTime = System.currentTimeMillis();
		Object result = elements.get(key);

		if (result != null) {
			timers.put(key, currentTime);
			remover.scheduleCheckFor(key);
		}
		return result;
	}

	/**
	 * Removes the mapping for the given key.
	 *
	 * @param key the key to remove the mapping for.
	 * @return the value previously associated with the key.
	 */
	public synchronized Object remove(String key) {
		timers.remove(key);
		return elements.remove(key);
	}

	private boolean isExpired(String key) {
		return (timers.get(key) + timeout) <= System.currentTimeMillis();
	}

	/**
	 * Executes the scheduled checks for key expiration and removes keys from the map if they have expired.
	 */
	private class ScheduledRemover {
		private final ScheduledExecutorService service =
				Executors.newSingleThreadScheduledExecutor();

		private final Queue<String> keyQueue = new LinkedList<>();

		private final Runnable expirationCheck = () -> {
			if (!keyQueue.isEmpty()) {
				String key = keyQueue.poll();
				if (isExpired(key)) {
					remove(key);
				}
			}
		};

		public void scheduleCheckFor(String key) {
			keyQueue.add(key);
			service.schedule(expirationCheck, timeout, TimeUnit.MILLISECONDS);
		}
	}
}