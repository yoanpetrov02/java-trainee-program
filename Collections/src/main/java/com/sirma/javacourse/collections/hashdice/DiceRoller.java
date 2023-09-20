package com.sirma.javacourse.collections.hashdice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class contains logic for rolling dice. The roll() method is used to roll, where the user has to pass the number of the sides of the dice and the amount of times the dice will be thrown.
 * A statistic of when a certain combination of numbers has been rolled is kept in the statistic HashMap static variable.
 */
public final class DiceRoller {

	private static final Random random = new Random();

	public static int numberOfThrows = 0;

	/**
	 * The map where the statistic is kept.
	 */
	public static final Map<Pair<Integer,Integer>, ArrayList<Integer>>
			statistic = new HashMap<>();

	private DiceRoller() {
		// Utility class private constructor.
	}

	public static void roll(int numberOfSides, int rollCount) {
		numberOfThrows += rollCount;

		for (int i = 1; i <= rollCount; i++) {
			int diceARoll = random.nextInt(numberOfSides) + 1;
			int diceBRoll = random.nextInt(numberOfSides) + 1;

			Pair<Integer, Integer> rolls = new Pair<>(diceARoll, diceBRoll);

			statistic.computeIfAbsent(rolls, k -> new ArrayList<>()).add(i);
		}
	}
}
