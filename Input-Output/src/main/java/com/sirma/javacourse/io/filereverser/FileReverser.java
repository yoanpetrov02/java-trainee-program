package com.sirma.javacourse.io.filereverser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains logic for file reversal.
 */
public class FileReverser {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileReverser.class);

	/**
	 * Reverses the given file's content and writes it in the same file.
	 *
	 * @param file the file to reverse the content of.
	 */
	public static void reverseFile(File file) {
		LinkedList<StringBuilder> result = new LinkedList<>();
		readFileContent(result, file);
		for (StringBuilder line : result) {
			line.reverse();
		}
		writeFileContent(result, file);
	}

	/**
	 * Reads the lines from the given {@code File} into the given {@code LinkedList}.
	 *
	 * @param dest the destination list to store the lines in.
	 * @param file the file to read the lines from.
	 */
	private static void readFileContent(LinkedList<StringBuilder> dest, File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = reader.readLine();
			while (line != null) {
				dest.add(new StringBuilder(line));
				line = reader.readLine();
			}
		} catch (IOException e) {
			LOGGER.error("Error during file reversal", e);
		}
	}

	/**
	 * Writes the already reversed lines from the {@code LinkedList} to the given {@code File}.
	 *
	 * @param lines the list containing the reversed lines to write.
	 * @param file the file to write the lines to.
	 */
	private static void writeFileContent(LinkedList<StringBuilder> lines, File file) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
			Iterator<StringBuilder> iterator = lines.descendingIterator();
			while (iterator.hasNext()) {
				writer.println(iterator.next());
			}
		} catch (IOException e) {
			LOGGER.error("Error during file reversal", e);
		}
	}
}
