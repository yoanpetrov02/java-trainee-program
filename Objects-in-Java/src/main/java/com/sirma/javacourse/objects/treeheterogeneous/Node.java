package com.sirma.javacourse.objects.treeheterogeneous;

/**
 * The {@code Node} class represents a node in a tree data structure. The node's value can be any object, which effectively makes the data structure heterogeneous.
 */
public class Node {

	private Object value;
	private Node left;
	private Node right;

	/**
	 * Getter for the value property.
	 * @return The value, stored in the node
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Setter for the value property.
	 * @param value The new value to be stored in the node
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Getter for the left child node property.
	 * @return The left child node of the current node
	 */
	public Node getLeft() {
		return left;
	}

	/**
	 * Setter for the left child node property.
	 * @param left The new left child node of the node
	 */
	public void setLeft(Node left) {
		this.left = left;
	}

	/**
	 * Getter for the right child node property.
	 * @return The right child node of the current node
	 */
	public Node getRight() {
		return right;
	}

	/**
	 * Setter for the right child node property.
	 * @param right The new right child node of the node
	 */
	public void setRight(Node right) {
		this.right = right;
	}

	/**
	 * Constructor that takes an {@code Object} and sets it as the value field for the newly created object.
	 * @param value Object, which will be the value of the node
	 */
	Node(Object value) {
		this.value = value;
		left = null;
		right = null;
	}

	/**
	 * Constructor that takes an {@code Object}, a left child {@code Node} and a right child {@code Node} and sets them as the fields for the newly created object.
	 * @param value Object, which will be the value of the node
	 * @param left Left child node of the newly created node
	 * @param right Right child node of the newly created node
	 */
	Node(Object value, Node left, Node right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
}