package com.sirma.javacourse.reflection.listparents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This utility class contains methods for instantiating objects by providing their full class name, and for getting
 * the class's parent classes/interfaces, using Reflection.
 */
public final class ClassUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

	private ClassUtil() {
		// Utility class private constructor.
	}

	/**
	 * Returns an instance of the given class.
	 * @param className the full name of the class (including package).
	 * @return the newly created instance, null if an error occurs.
	 */
	public static Object instantiateByClassName(String className) {
		Class<?> classForName;
		Constructor<?> constructor;

		try {
			classForName = Class.forName(className);
			constructor = classForName.getDeclaredConstructor();
		} catch (ClassNotFoundException | NoSuchMethodException e) {
			LOGGER.error("Error while instantiating object", e);
			return null;
		}
		return getInstanceByConstructor(constructor);
	}

	/**
	 * Creates a String List of the class's superclass and implemented interfaces, and returns it.
	 * @param className the full name of the class (including package).
	 * @return the List of parent classes/interfaces of the class, empty List if an arror occurs.
	 */
	public static List<String> getParentClasses(String className) {
		Class<?> classForName;

		try {
			classForName = Class.forName(className);
		} catch (ClassNotFoundException e) {
			LOGGER.error("Error while creating list", e);
			return new ArrayList<>();
		}

		Class<?> parentClass = classForName.getSuperclass();
		Class<?>[] interfaces = classForName.getInterfaces();

		List<String> result = new ArrayList<>();

		result.add(parentClass.toString());

		for (Class<?> element : interfaces) {
			result.add(element.toString());
		}

		return result;
	}

	/**
	 * Returns an instance of an object, using the provided constructor.
	 * @param constructor a constructor of the class.
	 * @return the newly created instance, null if an error occurs.
	 */
	private static Object getInstanceByConstructor(Constructor<?> constructor) {
		if (constructor == null) {
			return null;
		}
		Object result;
		boolean wasAccessible = true;

		if (!constructor.canAccess(null)) {
			wasAccessible = false;
			constructor.setAccessible(true);
		}

		try {
			result = constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException |
				 InvocationTargetException e) {
			LOGGER.error("Error while creating a new instance with the given constructor", e);
			return null;
		}
		if (!wasAccessible) {
			constructor.setAccessible(false);
		}
		return result;
	}
}
