package com.sirma.javacourse.objects.treehomogeneous;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomogeneousTreeRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomogeneousTreeRunner.class);

	public static void main(String[] args) {
		HomogeneousTree<String> tree = new HomogeneousTree<>("root node");

		tree.getRoot().addChildNode(new Node<>("child node 1 of root"));
		LOGGER.info("Child nodes of root:");
		tree.getRoot().printChildNodes();

		LOGGER.info("Child nodes of root's first child node:");
		tree.getRoot().getChildNodes().get(0).printChildNodes();

		tree.getRoot().getChildNodes().get(0).addChildNode(new Node<>("child node 1"));
		LOGGER.info("Child nodes of root's first child node after adding a new child to it:");
		tree.getRoot().getChildNodes().get(0).printChildNodes();

		tree.getRoot().getChildNodes().get(0).addChildNode(new Node<>("child node 2"));
		LOGGER.info("Child nodes of root's first child node after adding another new child to it:");
		tree.getRoot().getChildNodes().get(0).printChildNodes();

		LOGGER.info("Parent of root's first child node:");
		tree.getRoot().getChildNodes().get(0).getParent().printData();

		LOGGER.info("Root child nodes:");
		tree.getRoot().printChildNodes();

		ArrayList<Node<String>> nodesToAdd = new ArrayList<>();
		nodesToAdd.add(new Node<>("child node 2 of root"));
		nodesToAdd.add(new Node<>("child node 3 of root"));
		nodesToAdd.add(new Node<>("child node 4 of root"));
		tree.getRoot().addChildNodes(nodesToAdd);
		LOGGER.info("Root child nodes after adding multiple nodes:");
		tree.getRoot().printChildNodes();

		LOGGER.info("Root child nodes after setting them to an empty array list:");
		tree.getRoot().setChildNodes(new ArrayList<>());
		tree.getRoot().printChildNodes();
	}
}


