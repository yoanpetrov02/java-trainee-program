package com.sirma.javacourse.io.directorybrowser;

import java.io.File;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This utility class contains a method that lists the contents of a directory.
 */
public final class DirectoryBrowser {

	private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryBrowser.class);

	private DirectoryBrowser() {
		// Utility class private constructor
	}

	/**
	 * Takes an absolute path and checks whether it is a file or a directory.
	 * If it is a directory, all contents of that directory are listed and a {@code File} array of them is returned.
	 * @param pathString The absolute path of the directory in the file system.
	 * @return A {@code File[]} with the sub files/directories, empty {@code File[]} if the directory does not exist, the path points to a file, or if an exception is caught.
	 */
	public static File[] listContent(String pathString) {
		if (StringUtils.isEmpty(pathString)) {
			return new File[] {};
		}

		File file = new File(pathString);

		try {
			if (!file.exists()) {
				LOGGER.info("The file does not exist!");
				return new File[] {};
			}
			if (file.isFile()) {
				LOGGER.info("{} is not a directory!", file.getName());
				return new File[] {};
			}

			File[] subFiles = file.listFiles();

			for (File sub : subFiles) {
				if (sub.isFile()) {
					LOGGER.info("File: {}", sub.getName());
					continue;
				}
				LOGGER.info("Directory: {}", sub.getName());
			}

			return subFiles;
		} catch (NullPointerException | SecurityException e) {
			LOGGER.error("Error while retrieving subdirectory/file information", e);
			return new File[] {};
		}
	}
}
