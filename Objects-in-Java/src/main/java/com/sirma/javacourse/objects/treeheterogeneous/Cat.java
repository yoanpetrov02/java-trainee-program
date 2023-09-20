package com.sirma.javacourse.objects.treeheterogeneous;

import java.util.Objects;

/**
 * Sample class to be used in the demonstration of {@code HeterogeneousTree}.
 */
public class Cat {
	private String name;
	private int age;
	private String color;

	public Cat(String name, int age, String color) {
		this.name = name;
		this.age = age;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Cat cat = (Cat) o;
		return age == cat.age && Objects.equals(name, cat.name) && Objects.equals(color, cat.color);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, age, color);
	}
}