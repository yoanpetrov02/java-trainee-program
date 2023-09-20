package com.sirma.javacourse.io.filereverser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import org.junit.Assert;
import org.junit.Test;

public class FileReverserTest {

	private static final String TEST_EXPECTED_FILE_PATH = "src/test/resources/expected.txt";

	private static final String TEST_ACTUAL_FILE_PATH = "src/test/resources/actual.txt";

	private static final String SEPARATOR = System.lineSeparator();

	private static final String TEST_INPUT = "123" + SEPARATOR + "123123" + SEPARATOR + "123123123";

	private static final String TEST_EXPECTED_OUTPUT = "321321321" + SEPARATOR + "321321" + SEPARATOR + "321";

	@Test
	public void testReverseFileOutput() throws IOException {
		File expectedFile = new File(TEST_EXPECTED_FILE_PATH);
		File actualFile = new File(TEST_ACTUAL_FILE_PATH);
		try (PrintWriter writerExpected = new PrintWriter(expectedFile);
			 PrintWriter writerActual = new PrintWriter(actualFile)) {
			writerExpected.println(TEST_EXPECTED_OUTPUT);
			writerActual.println(TEST_INPUT);
		}
		FileReverser.reverseFile(actualFile);
		try {
			Assert.assertEquals(-1, Files.mismatch(expectedFile.toPath(), actualFile.toPath()));
		} finally {
			Files.delete(expectedFile.toPath());
			Files.delete(actualFile.toPath());
		}
	}
}
