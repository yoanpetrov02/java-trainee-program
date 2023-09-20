package com.sirma.javacourse.reflection.showclassinfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class uses Reflection to print information about a class's fields and methods.
 */
public final class ClassInformationPrinter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassInformationPrinter.class);

	/**
	 * Utility class private constructor.
	 */
	private ClassInformationPrinter() {}

	/**
	 * Returns the information about a given object's class, under the form of an {@code ArrayList}, using reflection.
	 * @param object The object whose class's information will be returned.
	 * @return A String List with the information.
	 */
	public static List<String> getClassData(Object object) {
		Field[] fields = object.getClass().getDeclaredFields();
		Method[] methods = object.getClass().getDeclaredMethods();

		List<String> result = new ArrayList<>();

		boolean wasNotAccessible = false;

		for (Field field : fields) {
			String temp = Modifier.toString(field.getModifiers()) + " "
					+ field.getType() + " "
					+ field.getName();

			try {
				if (Modifier.isPrivate(field.getModifiers())) {
					field.setAccessible(true);
					wasNotAccessible = true;
				}
				temp += ": " + field.get(object);
			} catch (IllegalAccessException e) {
				LOGGER.info("Error while retrieving field value", e);
			}

			result.add(temp);

			if (wasNotAccessible) {
				field.setAccessible(false);
				wasNotAccessible = false;
			}
		}

		for (Method method : methods) {
			String temp = Modifier.toString((method.getModifiers())) + " "
					+ method.getReturnType() + " "
					+ method.getName() + " "
					+ Arrays.toString(method.getParameterTypes());

			result.add(temp);
		}

		return result;
	}
}
