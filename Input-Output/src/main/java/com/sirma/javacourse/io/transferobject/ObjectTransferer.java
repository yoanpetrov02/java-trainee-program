package com.sirma.javacourse.io.transferobject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains a method that transfers bytes from an InputStream instance to an OutputStream instance.
 */
public class ObjectTransferer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectTransferer.class);

	private static final int MAX_BUFFER_SIZE = 8192;

	private final InputStream source;
	private final OutputStream destination;

	private int totalTransferred;

	public ObjectTransferer(InputStream source, OutputStream destination) {
		this.source = source;
		this.destination = destination;
		totalTransferred = 0;
	}

	/**
	 * Transfers the specified amount of bytes from the {@code InputStream} to the {@code OutputStream} of the object.
	 * @param numberOfBytes the amount of bytes to be transferred.
	 * @param offset the amount of bytes to be skipped from the beginning of the {@code InputStream}.
	 * @return the amount of transferred bytes, -1 if an exception occurs.
	 */
	public int transfer(int numberOfBytes, int offset) {
		try {
			if (offset > 0 && source.skip(offset) < offset) {
				return 0;
			}
			byte[] buffer = new byte[MAX_BUFFER_SIZE];
			int transferred = 0;
			int bytesRead;
			if (numberOfBytes < MAX_BUFFER_SIZE) {
				bytesRead = source.read(buffer, 0, numberOfBytes);
				destination.write(buffer, 0, bytesRead);
				transferred += bytesRead;
				totalTransferred += transferred;
				return transferred;
			}
			while ((bytesRead = source.read(buffer, 0, MAX_BUFFER_SIZE)) != -1 && numberOfBytes > 0) {
				destination.write(buffer, 0, bytesRead);
				transferred += bytesRead;
				numberOfBytes -= bytesRead;
				totalTransferred += bytesRead;
			}
			return transferred;
		} catch (IOException e) {
			LOGGER.error("Error while transferring bytes", e);
			return -1;
		}
	}

	/**
	 * Transfers all bytes from the {@code InputStream} to the {@code OutputStream} of the object.
	 * @return the amount of transferred bytes, -1 if an exception occurs.
	 */
	public int transferAll() {
		try {
			byte[] buffer = new byte[MAX_BUFFER_SIZE];
			int bytesRead;
			int transferred = 0;

			while ((bytesRead = source.read(buffer)) != -1) {
				destination.write(buffer, 0, bytesRead);
				transferred += bytesRead;
				totalTransferred += bytesRead;
			}
			return transferred;
		} catch (IOException e) {
			LOGGER.error("Error while transferring bytes", e);
			return -1;
		}
	}

	/**
	 * Returns the total amount of transferred bytes since the instance was created.
	 * 
	 * @return the total amount of transferred bytes.
	 */
	public int getTotalTransferred() {
		return totalTransferred;
	}
}