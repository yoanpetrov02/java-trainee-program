package com.sirma.javacourse.designpatterns.factorymethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements the abstract factory design pattern. There are two createInstance() methods - one uses
 * the {@code new} operator, and the other one uses the passed class name to instantiate the class.
 */
public class MyClassFactory implements ClassFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyClassFactory.class);

	/**
	 * Uses the {@code new} operator to return an instance.
	 * @return the newly created instance.
	 */
	@Override
	public MyClass createInstance() {
		return new MyClass();
	}

	/**
	 * Uses the given class name to return an instance, using reflection.
	 * @param className the full name of the class.
	 * @return the newly created instance.
	 */
	@Override
	public MyClass createInstance(String className) {
		Constructor<?> constructor;

		try {
			Class<?> classToInstantiate = Class.forName(className);
			constructor = classToInstantiate.getDeclaredConstructor(null);

			return (MyClass) constructor.newInstance(null);
		} catch (NoSuchMethodException | InvocationTargetException |
				InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			LOGGER.error("Error while constructing object", e);
			return null;
		}
	}
}
