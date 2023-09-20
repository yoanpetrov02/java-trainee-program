package com.sirma.javacourse.objects.treeheterogeneous;

import org.junit.Assert;
import org.junit.Test;

public class HeterogeneousTreeTest {

	private static Node firstNode = new Node("120");
	private static Node secondNode = new Node(new Cat("Tommy", 5, "black"));
	private static Node thirdNode = new Node("123", firstNode, secondNode);

	private static final HeterogeneousTree TEST_OBJECT =
			new HeterogeneousTree(new Node(2, new Node(1), thirdNode));

	@Test
	public void testHeterogeneousTreeAddSorting() {
		HeterogeneousTree actual = new HeterogeneousTree(new Node(2));
		actual.add(1);
		actual.add("123");
		actual.add("120");
		actual.add(new Cat("Tommy", 5, "black"));

		Assert.assertEquals(actual.getRoot().getValue(),
							TEST_OBJECT.getRoot().getValue());

		Assert.assertEquals(actual.getRoot().getLeft().getValue(),
							TEST_OBJECT.getRoot().getLeft().getValue());

		Assert.assertEquals(actual.getRoot().getRight().getValue(),
							TEST_OBJECT.getRoot().getRight().getValue());

		Assert.assertEquals(actual.getRoot().getRight().getLeft().getValue(),
							TEST_OBJECT.getRoot().getRight().getLeft().getValue());

		Assert.assertEquals(actual.getRoot().getRight().getRight().getValue(),
							TEST_OBJECT.getRoot().getRight().getRight().getValue());
	}
}