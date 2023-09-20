package com.sirma.javacourse.reflection.listparents;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class ClassUtilTest {

	@Test
	public void testListParentInfoStringClass() {
		Object[] expected = {
				"class java.lang.Object",
				"interface java.io.Serializable",
				"interface java.lang.CharSequence",
				"interface java.lang.Comparable",
				"interface java.lang.constant.Constable",
				"interface java.lang.constant.ConstantDesc"
		};

		Object[] actual = ClassUtil.
				getParentClasses("java.lang.String").toArray();

		Arrays.sort(actual);

		Assert.assertArrayEquals(expected, actual);
	}

	@Test
	public void testListParentInfoStringBuilderClass() {
		Object[] expected = {
				"class java.lang.AbstractStringBuilder",
				"interface java.io.Serializable",
				"interface java.lang.CharSequence",
				"interface java.lang.Comparable"
		};

		Object[] actual = ClassUtil.
				getParentClasses("java.lang.StringBuilder").toArray();

		Arrays.sort(actual);

		Assert.assertArrayEquals(expected, actual);
	}
}
