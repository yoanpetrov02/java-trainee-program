package com.sirma.javacourse.intro.hangmangame;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Test;

public class HangmanGameTest {

	private static HangmanGame TEST_OBJECT = null;

	@Test
	public void testGameWin() {
		String inputs = "apple\na\np\nl\ne";
		TEST_OBJECT = new HangmanGame(
				new Scanner(new ByteArrayInputStream(inputs.getBytes())));

		Assert.assertTrue(TEST_OBJECT.runGame());
	}

	@Test
	public void testGameLose() {
		String inputs = "apple\nq\nw\nr\nt\ny\nu";
		TEST_OBJECT = new HangmanGame(
				new Scanner(new ByteArrayInputStream(inputs.getBytes())));

		Assert.assertFalse(TEST_OBJECT.runGame());
	}
}