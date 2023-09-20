package com.sirma.javacourse.designpatterns.singleton;

/**
 * This class implements the Singleton design pattern. The pattern's idea is to only have
 * one instance of the class in memory at a time. The constructor is private, and when the getInstance() method
 * is called, if the instance is already initialized, that same instance is returned every time. If the method is
 * called for the first time, a new instance is created.
 */
public final class Singleton {

	private static Singleton instance = null;

	private Singleton() {
		System.out.println("Singleton created");
	}

	/**
	 * Returns an instance of the class.
	 * The instance will always be the same, and if the method is being called for the first time,
	 * it will be created and saved in the instance static variable.
	 * @return the instance.
	 */
	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
