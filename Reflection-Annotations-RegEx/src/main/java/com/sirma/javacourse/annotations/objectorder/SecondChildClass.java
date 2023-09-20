package com.sirma.javacourse.annotations.objectorder;

/**
 * This class has an orderIndex of 2, so that these objects will appear after objects of the {@code FirstChildClass} class in a sorted array.
 */
@Order(orderIndex = 2)
public class SecondChildClass
		extends ParentClass {
}
