package com.sirma.javacourse.annotations.objectorder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Order annotation is used to sort objects by their orderIndex().
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Order {
	int orderIndex() default 0;
}