package com.sirma.javacourse.reflection.privatemembers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains logic for invoking private methods and retrieving values from the private fields of an object (Specifically, SampleClass objects).
 */
public class PrivateMemberPrinter {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrivateMemberPrinter.class);

	/**
	 * Calls the privateMethod() method of SampleClass.
	 * @param object SampleClass object
	 * @return An Integer with the value, returned by the method, null if an exception is thrown or null has been passed as an argument
	 */
	public static Integer invokePrivateMethod(SampleClass object) {
		try {
			Method privateMethod = object.getClass().getDeclaredMethod(
					"privateMethod", int.class, int.class);

			boolean wasAccessible = true;

			if (!privateMethod.canAccess(object)) {
				privateMethod.setAccessible(true);
				wasAccessible = false;
			}

			int result = (int) privateMethod.invoke(object, object.getFieldA(), object.getFieldB());

			if (!wasAccessible) {
				privateMethod.setAccessible(false);
			}

			return result;
		} catch (NoSuchMethodException | NullPointerException |
				 InvocationTargetException | IllegalAccessException e) {
			LOGGER.error("Error while retrieving private method from object", e);
			return null;
		}
	}

	/**
	 * Returns the values of all private fields of the object under the form of an ArrayList.
	 * @param object SampleClass object
	 * @return An ArrayList of the values of the object's private fields, null if an exception occurs or null has been passed as an argument
	 */
	public static List<Object> getPrivateFields(SampleClass object) {
		List<Object> values = new ArrayList<>();
		Field[] fields;

		try {
			fields = object.getClass().getDeclaredFields();
		} catch (NullPointerException e) {
			LOGGER.error("Null has been passed as an argument", e);
			return new ArrayList<>();
		}

		for (Field field : fields) {
			if (Modifier.isPrivate(field.getModifiers())) {
				field.setAccessible(true);

				try {
					values.add(field.get(object));
				} catch (IllegalAccessException e) {
					LOGGER.error("Error while retrieving field value", e);
					return new ArrayList<>();
				}
			}
		}
		return values;
	}
}
