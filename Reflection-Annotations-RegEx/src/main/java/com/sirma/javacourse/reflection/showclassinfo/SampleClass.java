package com.sirma.javacourse.reflection.showclassinfo;

/**
 * This is a sample class for Reflection demonstrations. It contains sample fields and methods with different access modifiers.
 */
public class SampleClass {

	private int fieldA;
	private int fieldB;

	/**
	 * Sample static field.
	 */
	private static final String staticField = "Sample static field value";

	/**
	 * Sample default constructor.
	 */
	public SampleClass() {}

	/**
	 * Sample parametrized constructor.
	 * @param fieldA value for fieldA
	 * @param fieldB value for fieldB
	 */
	public SampleClass(int fieldA, int fieldB) {
		this.fieldA = fieldA;
		this.fieldB = fieldB;
	}

	/**
	 * Getter for fieldA property.
	 * @return fieldA of the object
	 */
	public int getFieldA() {
		return fieldA;
	}

	/**
	 * Setter for fieldA property.
	 * @param fieldA new value for fieldA
	 */
	public void setFieldA(int fieldA) {
		this.fieldA = fieldA;
	}

	/**
	 * Getter for fieldB property.
	 * @return fieldB of the object
	 */
	public int getFieldB() {
		return fieldB;
	}

	/**
	 * Setter for fieldB property.
	 * @param fieldB new value for fieldB
	 */
	public void setFieldB(int fieldB) {
		this.fieldB = fieldB;
	}

	/**
	 * Sample public method. Returns the sum of the object's two fields.
	 * @param parameter Sample integer parameter
	 * @return The sum of fieldA and fieldB of the object
	 */
	public int publicMethod(int parameter) {
		return this.fieldA + this.fieldB;
	}

	/**
	 * Sample public method. Returns the difference of the object's two fields.
	 * @param parameterA Sample integer parameter
	 * @param parameterB Sample integer parameter
	 * @return The difference of fieldA and fieldB of the object
	 */
	private int privateMethod(int parameterA, int parameterB) {
		return this.fieldA - this.fieldB;
	}
}
