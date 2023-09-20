package com.sirma.javacourse.producerconsumer;

/**
 * A product made by a {@code Producer}. Can be added to a {@code Storage}.
 */
public class Product {

	private static int lastProductNumber = -1;

	private int productNumber;

	public Product() {
		productNumber = ++lastProductNumber;
	}

	public int getProductNumber() {
		return productNumber;
	}

	@Override
	public String toString() {
		return "Product " + productNumber;
	}
}
