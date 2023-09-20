package com.sirma.javacourse.objects.treehomogeneous;

/**
 * A class that represents a homogeneous tree data structure. This class is used to encapsulate a {@code Node}, which represents the root of the tree. All methods related to the nodes are implemented in the {@code Node} class.
 * @param <T> Object of type T, the type of data stored in the tree's nodes.
 */
public class HomogeneousTree<T> {

	/**
	 * {@code Node} object that represents the root of the tree.
	 */
	private Node<T> root;

	/**
	 * Getter for the root property.
	 * @return The root Node of the tree
	 */
	public Node<T> getRoot() {
		return root;
	}

	/**
	 * Setter for the root property.
	 * @param root New root of the tree
	 */
	public void setRoot(Node<T> root) {
		this.root = root;
	}

	/**
	 * Constructor that allows to set the data of the root node when creating an object.
	 * @param rootData Object of type T
	 */
	public HomogeneousTree(T rootData) {
		root = new Node<>(rootData);
	}
}