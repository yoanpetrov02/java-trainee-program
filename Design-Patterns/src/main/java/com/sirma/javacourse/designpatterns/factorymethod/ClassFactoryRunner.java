package com.sirma.javacourse.designpatterns.factorymethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassFactoryRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassFactoryRunner.class);

	public static void main(String[] args) {
		ClassFactory factory = new MyClassFactory();

		MyClass instanceNormal = (MyClass) factory.createInstance();
		MyClass instanceReflection = (MyClass) factory.createInstance(
				"com.sirma.javacourse.designpatterns.factorymethod.MyClass");

		LOGGER.info("{}", instanceNormal.toString()); 	  //
		LOGGER.info("{}", instanceReflection.toString()); // To show that an object has successfully been constructed
	}
}
