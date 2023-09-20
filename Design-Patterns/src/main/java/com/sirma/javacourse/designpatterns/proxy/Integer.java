package com.sirma.javacourse.designpatterns.proxy;

/**
 * An Integer object holds a java.lang.Integer instance, which it can get or set to a new value.
 */
public class Integer implements Number {

	private java.lang.Integer instance;

	public Integer(java.lang.Integer instance) {
		this.instance = instance;
	}

	@Override
	public java.lang.Integer getValue() {
		return instance;
	}

	@Override
	public void setValue(java.lang.Number value) {
		this.instance = (java.lang.Integer) value;
	}
}
