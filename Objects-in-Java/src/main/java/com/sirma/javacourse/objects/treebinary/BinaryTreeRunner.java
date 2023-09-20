package com.sirma.javacourse.objects.treebinary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryTreeRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTree.class);

	static BinaryTree tree = new BinaryTree();

	public static void main(String[] args) {
		tree.add(6);
		tree.add(4);
		tree.add(8);
		tree.add(3);
		tree.add(5);
		tree.add(7);
		tree.add(9);

		tree.printSorted();
		LOGGER.info("------------");

		Node valueToFindExisting = tree.search(4);
		Node valueToFindMissing = tree.search(120);

		LOGGER.info("Searched value: 4");
		LOGGER.info("Node which contains the value: {}", valueToFindExisting);

		LOGGER.info("Searched value: 120");
		LOGGER.info("Node which contains the value: {} (such node does not exist in the tree)", valueToFindMissing);
	}
}