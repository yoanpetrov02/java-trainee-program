package com.sirma.javacourse.annotations.objectorder;

/**
 * This class has an orderIndex of 0, so that these objects will be first in an array of objects sorted by their orderIndex.
 * The class implements {@code Comparable} as the compareTo() method needs to be overridden for the purpose of using the already
 * implemented sort() method in the Arrays class.
 */
@Order(orderIndex = 0)
public class ParentClass
	implements Comparable<ParentClass> {

	/**
	 * Compares this object with another object of the same class by their orderIndex. An object's orderIndex is assumed to be 0 if the class isn't annotated with @Order.
	 * @param o the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(ParentClass o) {
		int a, b;

		if (!this.getClass().isAnnotationPresent(Order.class)) {
			a = 0;
		} else {
			a = this.getClass().getAnnotation(Order.class).orderIndex();
		}

		if (!o.getClass().isAnnotationPresent(Order.class)) {
			b = 0;
		} else {
			b = o.getClass().getAnnotation(Order.class).orderIndex();
		}

		return a - b;
	}
}
