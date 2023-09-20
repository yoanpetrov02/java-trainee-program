package com.sirma.javacourse.annotations.objectorder;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassSorterRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassSorterRunner.class);

	public static void main(String[] args) {
		ParentClass a = new ParentClass();
		ParentClass b = new ParentClass();
		ParentClass c = new FirstChildClass();
		ParentClass d = new FirstChildClass();
		ParentClass e = new SecondChildClass();
		ParentClass f = new ThirdChildClass();
		ParentClass g = new FourthChildClass();

		ParentClass[] array = new ParentClass[] {c, b, e, g, f, a, d};

		LOGGER.info("Before sorting: ");

		for (ParentClass obj : array) {
			if (obj.getClass().isAnnotationPresent(Order.class)) {
				LOGGER.info(String.valueOf(
						obj.getClass().getAnnotation(Order.class).orderIndex()));
				continue;
			}
			LOGGER.info("0"); // if the object's class is not annotated with @Order
		}

		LOGGER.info("After sorting: ");

		Arrays.sort(array);

		for (ParentClass obj : array) {
			if (obj.getClass().isAnnotationPresent(Order.class)) {
				LOGGER.info(String.valueOf(
						obj.getClass().getAnnotation(Order.class).orderIndex()));
				continue;
			}
			LOGGER.info("0"); // if the object's class is not annotated with @Order
		}
	}
}
