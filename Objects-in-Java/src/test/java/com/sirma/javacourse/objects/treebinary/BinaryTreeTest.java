package com.sirma.javacourse.objects.treebinary;

import org.junit.Assert;
import org.junit.Test;

public class BinaryTreeTest {

	private static final BinaryTree TEST_OBJECT = new BinaryTree(
			new Node(
				2, new Node(1), new Node(3)));

	@Test
	public void testBinaryTreeSearchMissing() {
		Assert.assertNull(TEST_OBJECT.search(4));
	}

	@Test
	public void testBinaryTreeSearchExisting() {
		Assert.assertEquals(TEST_OBJECT.getRoot(), TEST_OBJECT.search(2));
	}

	@Test
	public void testBinaryTreeSorting() {
		BinaryTree expected = new BinaryTree(
				new Node(
						2, new Node(1), new Node(3)));
		expected.getRoot().getRight().setRight(new Node(5));
		expected.getRoot().getRight().getRight().setLeft(new Node(4));
		expected.getRoot().getRight().getRight().setRight(new Node(6));

		BinaryTree actual = new BinaryTree(new Node(2));
		actual.add(1);
		actual.add(3);
		actual.add(5);
		actual.add(4);
		actual.add(6);

		Assert.assertEquals(actual.getRoot().getLeft().getValue(),
							expected.getRoot().getLeft().getValue());

		Assert.assertEquals(actual.getRoot().getRight().getRight().getValue(),
							expected.getRoot().getRight().getRight().getValue());

		Assert.assertEquals(actual.getRoot().getRight().getRight().getLeft().getValue(),
				expected.getRoot().getRight().getRight().getLeft().getValue());

		Assert.assertEquals(actual.getRoot().getRight().getRight().getRight().getValue(),
				expected.getRoot().getRight().getRight().getRight().getValue());
	}
}
