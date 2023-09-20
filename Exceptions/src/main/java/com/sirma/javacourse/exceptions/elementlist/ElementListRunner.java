package com.sirma.javacourse.exceptions.elementlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementListRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ElementListRunner.class);

	public static void main(String[] args) {
		ConsoleElementList list = new ConsoleElementList(5);
		list.start();
	}
}
