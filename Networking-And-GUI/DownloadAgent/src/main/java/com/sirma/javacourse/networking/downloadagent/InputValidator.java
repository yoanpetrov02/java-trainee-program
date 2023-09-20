package com.sirma.javacourse.networking.downloadagent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;

/**
 * Utility class used to validate input from the user of the application.
 */
public final class InputValidator {

	private InputValidator() {
		// Utility class private constructor
	}

	/**
	 * Tries to create a {@code URL} instance using the given input, and throws an exception if the url isn't valid.
	 *
	 * @param input the input url.
	 * @throws MalformedURLException if the url is invalid.
	 */
	public static void validateURL(String input) throws MalformedURLException {
		try {
			URL url = new URL(input);
		} catch (MalformedURLException e) {
			throw new MalformedURLException("Invalid URL!");
		}
	}

	/**
	 * Tries to create a {@code File} at the given destination, and throws an exception the path isn't valid.
	 *
	 * @param input the input destination path.
	 * @throws IOException if the file already exists or the directory doesn't exist.
	 */
	public static void validateFilePath(String input) throws IOException {
		Path path = Paths.get(input);
		try {
			Files.createFile(path);
			Files.deleteIfExists(path);
		} catch (FileAlreadyExistsException e) {
			throw new FileAlreadyExistsException("A file with the same name already exists!");
		} catch (AccessDeniedException e) {
			throw new AccessDeniedException("Access for creating files in the given directory is denied.");
		} catch (IOException e) {
			throw new IOException("An error occurred or the file path is invalid!");
		}
	}
}
