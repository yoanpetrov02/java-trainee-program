package com.sirma.javacourse.annotations.objectorder;

/**
 * This class has an orderIndex of 3, so that these objects will appear after objects of the {@code SecondChildClass} class in a sorted array.
 */
@Order(orderIndex = 3)
public class ThirdChildClass
		extends ParentClass {
}
