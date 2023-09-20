package com.sirma.javacourse.objects.treebinary;

/**
 * A class that represents a Node in a binary tree data structure.
 */
public class Node {

	private int value;
	private Node left;
	private Node right;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	Node(int value) {
		this.value = value;
		left = null;
		right = null;
	}

	Node(int value, Node left, Node right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
}