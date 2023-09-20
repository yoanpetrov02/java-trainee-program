package com.sirma.javacourse.designpatterns.factorymethod;

/**
 * Abstract class factory.
 */
public interface ClassFactory {

	/**
	 * Creates a new instance and returns it.
	 * @return the newly created instance.
	 */
	Object createInstance();

	/**
	 * Creates a new instance using the given class name.
	 * @param className the full name of the class.
	 * @return the newly created instance.
	 */
	Object createInstance(String className);
}
