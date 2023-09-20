package com.sirma.javacourse.annotations.objectorder;

/**
 * This class has an orderIndex of 1, so that these objects will appear after objects of the {@code ParentClass} class in a sorted array.
 */
@Order(orderIndex = 1)
public class FirstChildClass
		extends ParentClass {
}
