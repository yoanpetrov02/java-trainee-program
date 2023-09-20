package com.sirma.javacourse.networking.calculator.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorArgumentTest {

	private final CalculatorArgument argument = new CalculatorArgument();

	@Before
	public void resetArgument() {
		argument.reset();
	}

	@Test
	public void testArgumentAppendNumbers() {
		argument.append("1")
				.append("2")
				.append("3");
		assertEquals("123", argument.getString());
	}

	@Test
	public void testArgumentAppendTwoDecimalPoints() {
		argument.append("1")
				.append(".")
				.append(".");
		assertEquals("1.0", argument.getString());
	}

	@Test
	public void testArgumentPutZeroAfterDecimalPointAtEnd() {
		argument.append("1")
				.append(".");
		assertEquals("1.0", argument.getString());
	}

	@Test
	public void testArgumentAppendDecimalPointAtBeginning() {
		argument.append(".");
		assertEquals("0.0", argument.getString());
	}

	@Test
	public void testArgumentAppendTwoZerosInBeginning() {
		argument.append("0")
				.append("0");
		assertEquals("0", argument.getString());
	}

	@Test
	public void testArgumentAppendNumberAfterZero() {
		argument.append("0")
				.append("1");
		assertEquals("1", argument.getString());
	}

	@Test
	public void testArgumentDeleteLastChar() {
		argument.append("1")
				.append("2")
				.deleteLastChar();
		assertEquals("1", argument.getString());
	}
	
	@Test
	public void testArgumentDeleteDecimalPoint() {
		argument.append("1")
				.append(".")
				.append("2")
				.deleteLastChar()
				.deleteLastChar();
		assertEquals("1", argument.getString());
	}

	@Test
	public void testArgumentAppendDecimalPointAfterDeletingIt() {
		argument.append("1")
				.append(".")
				.append("2")
				.deleteLastChar()
				.deleteLastChar()
				.append(".");
		assertEquals("1.0", argument.getString());
	}

	@Test
	public void testArgumentGetDouble() {
		double delta = 0.1;
		argument.append("1")
				.append(".")
				.append("2");
		assertEquals(1.2, argument.getDouble(), delta);
	}

	@Test
	public void testArgumentNegativeNumber() {
		double delta = 0.1;
		argument.append("-")
				.append("1");
		assertEquals(-1.0, argument.getDouble(), delta);
	}

	@Test
	public void testArgumentOnlyMinus() {
		argument.append("-");
		assertEquals("0", argument.getString());
	}
}
