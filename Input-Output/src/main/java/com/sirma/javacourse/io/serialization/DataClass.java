package com.sirma.javacourse.io.serialization;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents a class with some data that can be serialized.
 * An instance of this class has an equals() and a hashCode() method, and the class has a serialVersionUID, as it
 * implements the Serializable interface.
 */
public class DataClass implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataClass.class);

	@Serial
	private static final long serialVersionUID = 6160043177860799246L;

	private int a;
	private String b;
	private double c;

	public DataClass(int a, String b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DataClass dataClass = (DataClass) o;
		return a == dataClass.a && Double.compare(dataClass.c, c) == 0 && b.equals(dataClass.b);
	}

	@Override
	public int hashCode() {
		return Objects.hash(a, b, c);
	}

	/**
	 * Prints the fields of the object in a formatted way.
	 */
	public void printObjectInfo() {
		LOGGER.info("a = {}, b = {}, c = {}", this.a, this.b, this.c);
	}
}
