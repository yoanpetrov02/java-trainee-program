package com.sirma.javacourse.annotations.objectorder;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class ClassSorterTest {

	@Test
	public void testSortByOrder() {
		ParentClass a = new ParentClass();
		ParentClass b = new FirstChildClass();
		ParentClass c = new SecondChildClass();
		ParentClass d = new ThirdChildClass();
		ParentClass e = new FourthChildClass();

		ParentClass[] expected = new ParentClass[] {a, e, b, c, d};
		ParentClass[] actual = new ParentClass[] {c, d, a, b, e};
		Arrays.sort(actual);

		Assert.assertArrayEquals(expected, actual);
	}
}
