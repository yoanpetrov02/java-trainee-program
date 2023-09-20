package com.sirma.javacourse.intro.hangmangame;

import java.util.ArrayList;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains logic for a hangman game. To run the game, call the runGame() method.
 */
public class HangmanGame {

	private static final Logger LOGGER = LoggerFactory.getLogger(HangmanGame.class);

	private Scanner input;

	private static String[] parts = new String[] {"head", "body", "leg", "leg", "arm", "arm"};

	/**
	 * The Scanner passed should be constructed using a {@code ByteArrayInputStream}, so that the user input can be mocked.
	 * @param input Scanner constructed using a {@code ByteArrayInputStream}
	 */
	public HangmanGame(Scanner input) {
		if (input == null) {
			this.input = new Scanner(System.in);
		}
		else {
			this.input = input;
		}
	}

	/**
	 * This method is used to run the game.
	 * @return true if the player wins, false if they don't
	 */
	public boolean runGame() {
		boolean gameRunning = true;

		String wordToGuess = inputStartingWord();
		String wordToGuessLowercase = wordToGuess.toLowerCase();
		String wordHidden = wordToGuess.replaceAll("[^ ]", "_");

		String guess;
		String guessLowercase;
		int wrongTriesLeft = 6;

		ArrayList<Character> guesses = new ArrayList<>();

		while (gameRunning) {
			LOGGER.info("Current progress: {}", wordHidden);
			guess = inputGuess();
			guessLowercase = guess.toLowerCase();

			if (guess.length() != 1 || !Character.isLetter(guess.charAt(0))) {
				LOGGER.info("You can only guess letters! (This will not be counted towards your guesses)");
				continue;
			}
			if (guesses.contains(guessLowercase.charAt(0))) {
				LOGGER.info("You have already guessed that letter! (This will not be counted towards your guesses)");
				continue;
			}
			if (wordToGuessLowercase.contains(guessLowercase)) {
				wordHidden = replaceWithLetters(wordToGuessLowercase, wordHidden, guessLowercase.charAt(0));
				guesses.add(guessLowercase.charAt(0));

				if (wordHidden.equals(wordToGuessLowercase)) {
					LOGGER.info("You won! The word is: {}", wordToGuess);
					return true;
				}
				continue;
			}

			int randomIndex = (int)(Math.random() * wrongTriesLeft);
			wrongTriesLeft--;
			String part = parts[randomIndex];
			delete(parts, randomIndex);

			LOGGER.info("Wrong guess! Drawing {} of the hanging body! You have {} more wrong guesses left",
					part, wrongTriesLeft);

			if (wrongTriesLeft == 0) {
				LOGGER.info("You lose! The word was: {}", wordToGuess);
				gameRunning = false;
			}
		}

		input.close();
		return false;
	}

	/**
	 * Takes an array and an index, and shifts all the array's elements after that index to the left by one element, effectively deleting that element.
	 * @param array Input String array
	 * @param index Index of element to be deleted
	 */
	private void delete(String[] array, int index) {
		for (int i = index; i < array.length - 1; i++) {
			array[i] = array[i+1];
		}
	}

	/**
	 * Takes a word and a string that represents the word with underscores, and replaces the underscores string with the given letter where they should be in the original word.
	 * @param word The original word
	 * @param wordUnderscores The original word replaced with underscores in some places or everywhere
	 * @param letter The letter to be replaced in the underscore string
	 * @return The result of the replacement
	 */
	private String replaceWithLetters(String word, String wordUnderscores, char letter) {
		StringBuilder result = new StringBuilder(wordUnderscores);
		word = word.replace(letter, '_');
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == '_') {
				result.setCharAt(i, letter);
			}
		}
		return result.toString();
	}

	/**
	 * Lets the user enter the starting word/phrase to be guessed and returns it.
	 * @return The entered word/phrase as a {@code String}
	 */
	private String inputStartingWord() {
		LOGGER.info("Enter a word/phrase to be guessed: ");
		return input.nextLine();
	}

	/**
	 * Lets the user enter a guess and returns that guess.
	 * @return The guess as a String
	 */
	private String inputGuess() {
		LOGGER.info("Guess a letter: ");
		return input.nextLine();
	}
}