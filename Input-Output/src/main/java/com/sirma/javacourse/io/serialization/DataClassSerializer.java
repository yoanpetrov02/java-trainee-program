package com.sirma.javacourse.io.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class handles all the serialization logic for DataClass objects.
 * There is a method for saving objects to a file, and for getting saved objects from a file.
 */
public final class DataClassSerializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataClassSerializer.class);

	private DataClassSerializer() {
		// Utility class private constructor.
	}

	/**
	 * Saves the passed object to a file in the specified location.
	 * @param path the path where the object will be saved.
	 * @param o the object that will be saved.
	 */
	public static void saveObject(String path, DataClass o) {
		try (FileOutputStream fileStream = new FileOutputStream(path);
				ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
			objectStream.writeObject(o);
		} catch (IOException e) {
			LOGGER.error("Error while saving object", e);
		}
	}

	/**
	 * Retrieves a serialized object from a file.
	 * @param path the path that leads to the file containing the object.
	 * @return the object retrieved from the file.
	 * @throws IOException when an IO error occurs.
	 */
	public static DataClass getObject(String path) throws IOException {
		try (FileInputStream fileStream = new FileInputStream(path);
				ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
			return (DataClass) objectStream.readObject();
		} catch (ClassNotFoundException e) {
			LOGGER.error("Error while retrieving object", e);
		}
		return null;
	}
}
