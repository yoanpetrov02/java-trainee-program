package com.sirma.javacourse.objects.treehomogeneous;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that represents a node in a tree data structure. Its implementation is used in the {@code HomogeneousTree} class.
 * @param <T> Object of type T, the type of data stored in the node
 */
public class Node<T> {

	/**
	 * Used when printing information about nodes.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Node.class);

	private T data;
	private Node<T> parent = null;
	private ArrayList<Node<T>> childNodes = new ArrayList<>();

	/**
	 * Constructor that takes an object and sets it as the data field of the node.
	 * @param data Object of type T
	 */
	public Node(T data) {
		this.data = data;
	}

	/**
	 * Constructor that takes an object and a parent {@code Node} object and sets them as the data and parent fields of the node.
	 * @param data Object of type T
	 * @param parent Node of the same type, used as a reference to the node's parent node
	 */
	public Node(T data, Node<T> parent) {
		this.data = data;
		this.parent = parent;
	}

	/**
	 * Constructor that takes an object and an {@code ArrayList} of {@code Node} objects of type T and sets them as the data and childNodes fields of the node.
	 * @param data Object of type T
	 * @param childNodes {@code ArrayList} of {@code Node} objects of type T
	 */
	public Node(T data, ArrayList<Node<T>> childNodes) {
		this.data = data;
		this.childNodes = childNodes;
	}

	/**
	 * Constructor that takes an object, a parent {@code Node} object and an {@code ArrayList} of child nodes and sets them as the fields of the node.
	 * @param data Object of type T
	 * @param parent Node of the same type, used as a reference to the node's parent node
	 * @param childNodes {@code ArrayList} of {@code Node} objects of type T
	 */
	public Node(T data, Node<T> parent, ArrayList<Node<T>> childNodes) {
		this.data = data;
		this.parent = parent;
		this.childNodes = childNodes;
	}

	/**
	 * Getter for the data field of the object.
	 * @return Object of type T
	 */
	public T getData() {
		return this.data;
	}

	/**
	 * Getter for the parent {@code Node} field of the object.
	 * @return {@code Node} object
	 */
	public Node<T> getParent() {
		return this.parent;
	}

	/**
	 * Getter for the child nodes list of the object.
	 * @return {@code ArrayList} of the child nodes of the object
	 */
	public ArrayList<Node<T>> getChildNodes() {
		return this.childNodes;
	}

	/**
	 * Setter for the data field of the object.
	 * @param data Object of type T
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Setter for the parent {@code Node} field of the object.
	 * @param parent {@code Node} object
	 */
	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	/**
	 * Setter for the child nodes list of the object.
	 * @param childNodes {@code ArrayList} of {@code Node} objects
	 */
	public void setChildNodes(ArrayList<Node<T>> childNodes) {
		this.childNodes = childNodes;
	}

	/**
	 * Takes a {@code Node} object and adds it to the list of child nodes of the object.
	 * @param childToAdd {@code Node} child node to be added
	 */
	public void addChildNode(Node<T> childToAdd) {
		childToAdd.setParent(this);
		childNodes.add(childToAdd);
	}

	/**
	 * Takes an {@code ArrayList} of {@code Node} objects to add to the list of child nodes of the object.
	 * @param childNodesToAdd {@code ArrayList} of the child nodes to be added
	 */
	public void addChildNodes(ArrayList<Node<T>> childNodesToAdd) {
		for (Node<T> child:
				childNodesToAdd) {
			this.addChildNode(child);
		}
	}

	/**
	 * Prints the data field, using a {@code Logger}, after calling the toString() method on it.
	 */
	public void printData() {
		if (data != null) {
			LOGGER.info(data.toString());
		}
		else LOGGER.info("Data isn't set!");
	}

	/**
	 * Calls the printData() method on every {@code Node} object on the list of child nodes of the object.
	 */
	public void printChildNodes() {
		if (childNodes.size() > 0) {
			for (Node<T> node:
					childNodes) {
				node.printData();
			}
		}
		else LOGGER.info("This node has no child nodes!");
	}
}