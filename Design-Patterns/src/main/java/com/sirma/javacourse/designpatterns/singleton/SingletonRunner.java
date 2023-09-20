package com.sirma.javacourse.designpatterns.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingletonRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(SingletonRunner.class);

	public static void main(String[] args) {
		LOGGER.info("If you only see the message \"Singleton created\" once, then all objects are the same object");

		Singleton instanceA = Singleton.getInstance();
		Singleton instanceB = Singleton.getInstance();
		Singleton instanceC = Singleton.getInstance();
		Singleton instanceD = Singleton.getInstance();
		Singleton instanceE = Singleton.getInstance();
	}
}
