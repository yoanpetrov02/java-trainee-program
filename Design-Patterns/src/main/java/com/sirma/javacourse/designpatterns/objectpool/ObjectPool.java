package com.sirma.javacourse.designpatterns.objectpool;

import java.util.List;
import java.util.Stack;

/**
 * This class represents a pool of objects. The pool has a capacity, and it limits the number of instances that can
 * exist/be used at a time. The available stack contains the available objects, and the used stack contains the
 * currently used ones. By acquiring an instance, that object, apart from being returned, gets popped from the available
 * stack and gets pushed to used stack, and by releasing an object it gets popped from the used stack and pushed to the
 * available one. If there are no available objects, null is returned instead.
 */
public class ObjectPool {

	private int capacity;
	private Stack<Object> available;
	private Stack<Object> used;

	/**
	 * Constructs an ObjectPool with the given capacity. The objects in this pool are instantiated automatically.
	 * @param capacity the capacity of the pool.
	 */
	public ObjectPool(int capacity) {
		this.capacity = capacity;
		available = new Stack<>();
		used = new Stack<>();

		for (int i = 0; i < capacity; i++) {
			available.push(new Object());
		}
	}

	/**
	 * Constructs an ObjectPool using the given array of objects. The pool's capacity is equal to the length of the array.
	 * @param instances an array containing the instances which will be available in the pool.
	 */
	public ObjectPool(Object[] instances) {
		available = new Stack<>();
		used = new Stack<>();

		available.addAll(List.of(instances));
		capacity = instances.length;
	}

	/**
	 * Returns an available object from the pool.
	 * @return an available object, null if there are no available objects.
	 */
	public Object acquire() {
		if (available.isEmpty()) {
			return null;
		}

		return used.push(available.pop());
	}

	/**
	 * Releases an object, returning it back to the pool of available objects.
	 * @param object the object to be released.
	 * @return true if the object was successfully released, false if it was not (for example, if the object was not among the currently used objects).
	 */
	public boolean release(Object object) {
		if (used.isEmpty()) {
			return false;
		}
		if (used.remove(object)) {
			available.push(object);
			return true;
		}
		return false;
	}

	/**
	 * Returns the amount of available objects.
	 */
	public int getAvailableAmount() {
		return available.size();
	}

	/**
	 * Returns the amount of objects that are being used.
	 */
	public int getUsedAmount() {
		return used.size();
	}

	public int getCapacity() {
		return capacity;
	}
}
