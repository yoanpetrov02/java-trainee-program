package com.sirma.javacourse.designpatterns.proxy;

/**
 * Interface for all objects that derive from Number. Every number can have its value retrieved or set.
 */
public interface Number {

	java.lang.Number getValue();

	void setValue(java.lang.Number value);
}
