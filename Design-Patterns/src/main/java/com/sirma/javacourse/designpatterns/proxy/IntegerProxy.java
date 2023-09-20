package com.sirma.javacourse.designpatterns.proxy;

/**
 * Proxy class for Integer. An IntegerProxy object holds an Integer instance and can get or set its value.
 * IntegerProxy objects can be passed to methods that take a Number object as a substitute to an Integer.
 */
public class IntegerProxy implements Number {

	private Integer instance;

	public IntegerProxy(Integer instance) {
		this.instance = instance;
	}

	@Override
	public java.lang.Number getValue() {
		return instance.getValue();
	}

	@Override
	public void setValue(java.lang.Number value) {
		instance.setValue(value);
	}
}
