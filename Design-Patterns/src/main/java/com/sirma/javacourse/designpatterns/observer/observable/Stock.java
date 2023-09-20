package com.sirma.javacourse.designpatterns.observer.observable;

/**
 * Represents a stock with a certain name.
 */
public class Stock {

	private final String name;

	public Stock(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
