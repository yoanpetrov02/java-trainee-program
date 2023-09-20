package com.sirma.javacourse.objects.treeheterogeneous;

public class HeterogeneousTreeRunner {

	public static void main(String[] args) {
		HeterogeneousTree tree = new HeterogeneousTree(new Node(1));

		tree.add("123");
		tree.add(new Cat("Tommy", 5, "black"));

		tree.printSorted();
	}
}