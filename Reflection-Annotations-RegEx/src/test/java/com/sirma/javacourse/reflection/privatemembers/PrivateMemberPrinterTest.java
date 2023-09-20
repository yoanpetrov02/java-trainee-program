package com.sirma.javacourse.reflection.privatemembers;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class PrivateMemberPrinterTest {

	@Test
	public void testInvokePrivateMethodsNormalValues() {
		Assert.assertEquals(-1, (int)PrivateMemberPrinter.
				invokePrivateMethod(new SampleClass(4, 5)));

		Assert.assertEquals(0, (int)PrivateMemberPrinter.
				invokePrivateMethod(new SampleClass()));
	}

	@Test
	public void testInvokePrivateMethodsNull() {
		Assert.assertNull(PrivateMemberPrinter.invokePrivateMethod(null));
	}

	@Test
	public void testPrintPrivateFieldsNormalValues() {
		Assert.assertArrayEquals(new Object[] {1, 2, "Sample static field value"},
				PrivateMemberPrinter.getPrivateFields(new SampleClass(1, 2)).toArray());

		Assert.assertArrayEquals(new Object[] {0, 0, "Sample static field value"},
				PrivateMemberPrinter.getPrivateFields(new SampleClass()).toArray());
	}

	@Test
	public void testPrintPrivateFieldsNull() {
		Assert.assertArrayEquals(
				new ArrayList<Object>().toArray(),
				PrivateMemberPrinter.getPrivateFields(null).toArray());
	}
}
