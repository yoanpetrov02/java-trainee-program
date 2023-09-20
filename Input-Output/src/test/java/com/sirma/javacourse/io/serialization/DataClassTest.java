package com.sirma.javacourse.io.serialization;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Assert;
import org.junit.Test;

public class DataClassTest {

	@Test
	public void testSaveObjectGetObject() throws IOException {
		DataClass testObject = new DataClass(2, "sample string", 3.14);
		String path = "src/test/resources/testobject.txt";

		DataClassSerializer.saveObject(path, testObject);

		DataClass result;
		result = DataClassSerializer.getObject(path);

		Assert.assertEquals(testObject, result);
		Files.delete(Path.of(path));
	}

	@Test
	public void testGetObjectIOException() {
		Assert.assertThrows(IOException.class, () -> DataClassSerializer.getObject(""));
	}
}
