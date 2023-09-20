package com.sirma.javacourse.networking.downloadagent.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sirma.javacourse.io.transferobject.ObjectTransferer;

/**
 * A download agent, used to download files from a URL.
 */
public class DownloadAgent {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(DownloadAgent.class);

	private final URLConnection connection;
	private ObjectTransferer transferer;
	private File destinationFile;

	public DownloadAgent(String URL)
			throws IOException {
		connection = new URL(URL).openConnection();
	}

	/**
	 * Downloads the file from the url to the given destination file.
	 *
	 * @param destination the path to the destination file.
	 * @return the newly created file under a {@code File} object if the download is successful, null if the destination file was not successfully created.
	 */
	public File downloadFile(String destination) {
		createDestinationFile(destination);

		if (destinationFile != null) {
			downloadContent();
			return destinationFile;
		}
		return null;
	}

	public int getTotalTransferred() {
		return transferer.getTotalTransferred();
	}

	private void createDestinationFile(String destination) {
		try {
			destinationFile = Files.createFile(Paths.get(destination)).toFile();
		} catch (IOException e) {
			LOGGER.error("An i/o error occurred while creating file", e);
		}
	}

	/**
	 * Downloads the content from the given url.
	 */
	private void downloadContent() {
		try (InputStream in = connection.getInputStream();
				OutputStream out = new FileOutputStream(destinationFile)) {
			transferer = new ObjectTransferer(in, out);
			transferer.transferAll();
		} catch (IOException e) {
			LOGGER.error("An i/o error occurred while downloading content", e);
		}
	}
}
