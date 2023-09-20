package com.sirma.javacourse.collections.hashdice;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiceRollerRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DiceRollerRunner.class);

	public static void main(String[] args) {
		DiceRoller.roll(6, 10);
		DiceRoller.statistic.forEach((key, value) -> LOGGER.info("{}, {}: {}",
				key.getLeft(),
				key.getRight(),
				Arrays.toString(value.toArray())));
	}
}
