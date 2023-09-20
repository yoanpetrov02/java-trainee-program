package com.sirma.javacourse.collections.exceptionsmanager;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ExceptionMessageManagerTest {

	@Test
	public void testAddStringMessageAddCorrectly() {
		ExceptionMessageManager testObject = new ExceptionMessageManager();

		testObject.addStringMessage("Invalid card number");
		Assert.assertEquals("Invalid card number", testObject.getMessage());

		testObject.addStringMessage("Wrong PID");
		Assert.assertEquals("Invalid card number;Wrong PID", testObject.getMessage());
	}

	@Test
	public void testAddStringMessageThrow() {
		ExceptionMessageManager testObject = new ExceptionMessageManager();

		Assert.assertThrows(IllegalArgumentException.class, () -> testObject.addStringMessage("invalid message"));
		Assert.assertThrows(IllegalArgumentException.class, () -> testObject.addStringMessage(""));
		Assert.assertThrows(IllegalArgumentException.class, () -> testObject.addStringMessage(null));
	}

	@Test
	public void testAddExceptionMessageUsingCodeAddCorrectly() {
		ExceptionMessageManager testObject = new ExceptionMessageManager();

		testObject.addExceptionMessageUsingCode("0");
		Assert.assertEquals("Invalid card number", testObject.getMessage());

		testObject.addExceptionMessageUsingCode("1");
		Assert.assertEquals("Invalid card number;Wrong PID", testObject.getMessage());
	}

	@Test
	public void TestAddExceptionMessageUsingCodeThrow() {
		ExceptionMessageManager testObject = new ExceptionMessageManager();

		Assert.assertThrows(IllegalArgumentException.class, () -> testObject.addExceptionMessageUsingCode("3"));
		Assert.assertThrows(IllegalArgumentException.class, () -> testObject.addExceptionMessageUsingCode("-1"));
		Assert.assertThrows(IllegalArgumentException.class, () -> testObject.addExceptionMessageUsingCode(null));
	}

	@Test
	public void testGetMessages() {
		String testMessage = "Invalid card number;Wrong PID;Invalid postal code";

		ArrayList<String> expected = new ArrayList<>(
				List.of("Invalid card number", "Wrong PID", "Invalid postal code"));

		Assert.assertEquals(expected, ExceptionMessageManager.getMessages(testMessage));
	}
}
