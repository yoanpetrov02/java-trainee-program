package com.sirma.javacourse.reflection.showclassinfo;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class ClassInformationPrinterTest {

	@Test
	public void testPrintClassInfo() {
		SampleClass testObject = new SampleClass();

		Object[] expected = new String[] {
				"private int fieldA: 0",
				"private int fieldB: 0",
				"private int privateMethod [int, int]",
				"private static final class java.lang.String staticField: Sample static field value",
				"public int getFieldA []",
				"public int getFieldB []",
				"public int publicMethod [int]",
				"public void setFieldA [int]",
				"public void setFieldB [int]"
		};

		Object[] actual =
				ClassInformationPrinter.getClassData(testObject).toArray();

		Arrays.sort(actual);

		Assert.assertArrayEquals(expected, actual);
	}
}
