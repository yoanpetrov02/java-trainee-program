package com.sirma.javacourse.objects.treeheterogeneous;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@code HeterogeneousTree} class represents a heterogeneous binary tree data structure. It uses the {@code Node} class to store the information about its nodes.
 */
public class HeterogeneousTree {

	/**
	 * Used for printing, for example in printSorted().
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(HeterogeneousTree.class);

	/**
	 * The root node of the tree.
	 */
	private Node root;

	/**
	 * Constructor that takes a {@code Node} object and sets it as the root of the newly created object.
	 * @param root {@code Node} object, the root of the new object
	 */
	public HeterogeneousTree(Node root) {
		this.root = root;
	}

	/**
	 * Calls the addInner() method, which inserts a new node with the given object as its value to the tree.
	 * @param value Object, the value of the new node
	 */
	public void add(Object value) {
		root = addInner(root, value);
	}

	/**
	 * Calls the printSortedInner() method, which prints all elements of the tree in a sorted way.
	 */
	public void printSorted() {
		printSortedInner(root);
	}

	/**
	 * Getter for the root property.
	 * @return The root of the tree
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * Setter for the root property.
	 * @param root New root Node of the tree
	 */
	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * Inserts a {@code Node} with the given value into the tree, if the value doesn't already exist in the tree.
	 * @param current Current node of the traversal for finding the right position for the new value
	 * @param value Object, which will be the value of the new node to be inserted into the tree
	 * @return The first Node passed to the function, with its child nodes updated after adding the new value
	 */
	private Node addInner(Node current, Object value) {
		if (current == null) {
			return new Node(value);
		}

		if (value.hashCode() < current.getValue().hashCode()) {
			current.setLeft(addInner(current.getLeft(), value));
		} else if (value.hashCode() > current.getValue().hashCode()) {
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

		LOGGER.info("{}, type: {}", node.getValue().hashCode(), node.getValue().getClass());

		printSortedInner(node.getRight());
	}
}