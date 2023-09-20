package com.sirma.javacourse.objects.treebinary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that implements a binary tree data structure. The class uses the {@code Node} class in order to function.
 */
public class BinaryTree {

	private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTree.class);

	private Node root;

	public BinaryTree() {}

	public BinaryTree(Node root) {
		this.root = root;
	}

	/**
	 * Calls the addInner() method, which inserts the given value into the tree.
	 * @param value Integer value
	 */
	public void add(int value) {
		root = addInner(root, value);
	}

	/**
	 * Calls the printSortedInner() method, which prints all elements of the tree in a sorted way.
	 */
	public void printSorted() {
		printSortedInner(root);
	}

	/**
	 * Calls the searchInner() method, which searches for the value in the tree, and returns the {@code Node} which contains it.
	 * @param value Integer value to be searched for
	 * @return The {@code Node} of the tree which contains the value
	 */
	public Node search(int value) {
		return searchInner(this.root, value);
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * Inserts a {@code Node} with the given value into the tree, if the value doesn't already exist in the tree.
	 * @param current Current node of the traversal for finding the right position for the new value
	 * @param value Value to be inserted into the tree
	 * @return The first Node passed to the function, with its child nodes updated after adding the new value
	 */
	private Node addInner(Node current, int value) {
		if (current == null) {
			return new Node(value);
		}

		if (value < current.getValue()) {
			current.setLeft(addInner(current.getLeft(), value));
		} else if (value > current.getValue()) {
			current.setRight(addInner(current.getRight(), value));
		} else {
			return current;
		}

		return current;
	}

	/**
	 * Prints all elements of the tree in a sorted way, using the Inorder Depth-first traversal algorithm.
	 * @param node Node whose children will be printed
	 */
	private void printSortedInner(Node node) {
		if (node == null) {
			return;
		}

		printSortedInner(node.getLeft());

		LOGGER.info(String.valueOf(node.getValue()));

		printSortedInner(node.getRight());
	}

	/**
	 * Uses depth-first search to find the {@code Node} in the tree, which contains the input value.
	 * @param root root of the tree that will be searched
	 * @param value int value to be searched for
	 * @return The {@code Node} which contains the input value, null if the value isn't present in the tree.
	 */
	private Node searchInner(Node root, int value) {
		if (root == null || root.getValue() == value) {
			return root;
		}

		if (root.getValue() < value) {
			return searchInner(root.getRight(), value);
		} else {
			return searchInner(root.getLeft(), value);
		}
	}
}