package com.sirma.javacourse.exceptions.elementlist;

/**
 * This class represents a list of objects. It has methods for adding, removing elements and printing all elements.
 * The add() and remove() methods demonstrate throwing custom exceptions.
 */
public class ElementList {

	private final Object[] list;
	private int size;

	/**
	 * Constructs a new {@code ElementList} with the specified capacity.
	 * @param capacity the maximum capacity of the list
	 */
	public ElementList(int capacity) {
		list = new Object[capacity];
		this.size = 0;
	}

	/**
	 * Returns the element at the specified index in the list.
	 * @param index The index of the element in the list.
	 * @return The element at the specified index.
	 */
	public synchronized Object getElementAt(int index) {
		return this.list[index];
	}

	/**
	 * Adds a new element to the list, if the list isn't full. Otherwise, throws an {@code AddToFullListException}.
	 * @param newElement new object to be added to the list
	 * @throws AddToFullListException if the list is already full
	 */
	public void add(Object newElement) throws AddToFullListException {
		if (this.size >= this.list.length) {
			throw new AddToFullListException("Element cannot be added because the list is full!");
		}

		this.list[size] = newElement;
		size++;
	}

	/**
	 * Removes the last element from the list, if the list isn't empty. Otherwise, throws a {@code RemoveFromEmptyListException}.
	 * @throws RemoveFromEmptyListException if the list is empty
	 */
	public void remove() throws RemoveFromEmptyListException {
		if (this.size == 0) {
			throw new RemoveFromEmptyListException("Element cannot be removed because the list is empty!");
		}

		this.size--;
		list[size] = null;
	}

	@Override
	public String toString() {
		if (size == 0) {
			return "[]";
		}
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for (int i = 0; i < size - 1; i++) {
			builder.append(list[i] + ", ");
		}
		builder.append(list[size - 1] + "]");
		return builder.toString();
	}
}
