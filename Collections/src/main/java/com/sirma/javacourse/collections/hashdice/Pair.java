package com.sirma.javacourse.collections.hashdice;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents a pair of values. The values can be of any type. If two pairs are compared, the order of their values does not matter,
 * so if a Pair has the values (1,2) and another pair has the values (2,1) their hash codes would be the same and equals() would return true.
 * @param <L>
 * @param <R>
 */
public class Pair<L, R> {

	private L left;
	private R right;

	public Pair(L left, R right) {
		this.left = left;
		this.right = right;
	}

	public L getLeft() {
		return this.left;
	}

	public R getRight() {
		return this.right;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Pair<?, ?> pair = (Pair<?, ?>) o;
		Object[] a = new Object[] {this.getLeft(), this.getRight()};
		Object[] b = new Object[] { ((Pair<?, ?>) o).getLeft(), ((Pair<?, ?>) o).getRight()};
		Arrays.sort(a);
		Arrays.sort(b);

		return Arrays.equals(a, b);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.getLeft()) ^ Objects.hashCode(this.getRight());
	}
}
