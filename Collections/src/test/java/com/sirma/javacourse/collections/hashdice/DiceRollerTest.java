package com.sirma.javacourse.collections.hashdice;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class DiceRollerTest {

	@Test
	public void testDiceRollerRollCorrectly() {
		DiceRoller.roll(6, 10);

		DiceRoller.statistic.forEach((key, value) ->
			Assert.assertTrue(
					(key.getLeft() <= 6 && key.getLeft() > 0) &&
					(key.getRight() <= 6 && key.getRight() > 0)));
	}

	@Test
	public void testDiceRollerNumberOfThrows() {
		boolean isContained = false;

		for (ArrayList<Integer> value : DiceRoller.statistic.values()) {
			if (value.contains(DiceRoller.numberOfThrows)) {
				isContained = true;
				break;
			}
		}

		Assert.assertTrue(isContained);
	}
}
