package com.sirma.javacourse.reflection.listparents;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassUtilRunner {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(ClassUtil.class);

	public static void main(String[] args) {
		Object stringBuilder = ClassUtil.instantiateByClassName("java.lang.StringBuilder");

		LOGGER.info(stringBuilder.getClass().toString());
		((StringBuilder)stringBuilder).append("TEST STRING"); // To demonstrate that the object is indeed a StringBuilder instance.
		LOGGER.info(stringBuilder.toString());

		List<String> parents = ClassUtil.getParentClasses("java.lang.StringBuilder");
		LOGGER.info(Arrays.toString(parents.toArray()));
	}
}
