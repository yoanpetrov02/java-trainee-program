package com.sirma.javacourse.io.consolereader;

import java.io.ByteArrayInputStream;
import java.util.Locale;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Test;

public class ConsoleReaderTest {

	private static final double DELTA = 1e-1;

	private static ConsoleReader TEST_OBJECT = null;

	private static String inputs = null;

	@Test
	public void testReadString() {
		inputs = "test string\n";
		TEST_OBJECT = new ConsoleReader(
				new Scanner(new ByteArrayInputStream(inputs.getBytes())));

		Assert.assertEquals("test string", TEST_OBJECT.readString());
	}

	@Test
	public void testReadIntValidInput() {
		inputs = "123\n-123\n0\n";
		TEST_OBJECT = new ConsoleReader(
				new Scanner(new ByteArrayInputStream(inputs.getBytes())));

		Assert.assertEquals(123, TEST_OBJECT.readInt().intValue());
		Assert.assertEquals(-123, TEST_OBJECT.readInt().intValue());
		Assert.assertEquals(0, TEST_OBJECT.readInt().intValue());
	}

	@Test
	public void testReadIntInvalidInput() {
		inputs = "123.\n12387126387123687";
		TEST_OBJECT = new ConsoleReader(
				new Scanner(new ByteArrayInputStream(inputs.getBytes())));

		Assert.assertNull(TEST_OBJECT.readInt());
		Assert.assertNull(TEST_OBJECT.readInt());
	}

	@Test
	public void testReadCharValidInput() {
		inputs = "1\nc\n.\n";
		TEST_OBJECT = new ConsoleReader(
				new Scanner(new ByteArrayInputStream(inputs.getBytes())));

		Assert.assertEquals('1', TEST_OBJECT.readChar().charValue());
		Assert.assertEquals('c', TEST_OBJECT.readChar().charValue());
		Assert.assertEquals('.', TEST_OBJECT.readChar().charValue());
	}

	@Test
	public void testReadCharInvalidInput() {
		inputs = "\n";
		TEST_OBJECT = new ConsoleReader(
				new Scanner(new ByteArrayInputStream(inputs.getBytes())));

		Assert.assertNull(TEST_OBJECT.readChar());
	}

	@Test
	public void testReadFloatValidInput() {
		inputs = "123.2\n-123.2\n0.0\n";
		TEST_OBJECT = new ConsoleReader(
				new Scanner(new ByteArrayInputStream(
						inputs.getBytes())).useLocale(Locale.getDefault()));

		Assert.assertEquals(123.2, TEST_OBJECT.readFloat(), DELTA);
		Assert.assertEquals(-123.2, TEST_OBJECT.readFloat(), DELTA);
		Assert.assertEquals(0.0, TEST_OBJECT.readFloat(), DELTA);
	}

	@Test
	public void testReadFloatInvalidInput() {
		inputs = "asd\n273821387126312867316287312683128312631827\n";
		TEST_OBJECT = new ConsoleReader(
				new Scanner(new ByteArrayInputStream(inputs.getBytes())));

		Assert.assertNull(TEST_OBJECT.readFloat());
		Assert.assertNull(TEST_OBJECT.readFloat());
	}
}
