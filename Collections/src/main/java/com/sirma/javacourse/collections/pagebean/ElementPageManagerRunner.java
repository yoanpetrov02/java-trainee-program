package com.sirma.javacourse.collections.pagebean;

import java.util.ArrayList;
import java.util.List;

public class ElementPageManagerRunner {

	public static void main(String[] args) {
		ArrayList<Object> elements = new ArrayList<>(
				List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23));

		ElementPageManagerPrinter a = new ElementPageManagerPrinter(
				new ElementPageManager(5, elements));
		a.start();
	}
}
