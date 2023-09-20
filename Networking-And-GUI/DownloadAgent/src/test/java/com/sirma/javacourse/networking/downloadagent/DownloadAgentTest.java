package com.sirma.javacourse.networking.downloadagent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sirma.javacourse.networking.downloadagent.model.DownloadAgent;

public class DownloadAgentTest {

	private static DownloadAgent testAgent;

	private File localFile;
	private File destinationFile;

	@Before
	public void setup() throws IOException {
		localFile = Files.createFile(
				Paths.get("src/test/resources/local.txt")).toFile();
		testAgent = new DownloadAgent("file:///" + localFile.getAbsolutePath());
		PrintWriter writer = new PrintWriter(
				new FileWriter(localFile));
		writer.println("test content 123.");
		writer.close();
	}

	@After
	public void cleanUp() throws IOException {
		Files.delete(localFile.toPath());
		Files.delete(destinationFile.toPath());
	}

	@Test
	public void testDownloadFileLocalFile() throws IOException {
		destinationFile = testAgent.
				downloadFile("src/test/resources/downloaded.txt");

		Assert.assertEquals(-1, Files.mismatch(
				localFile.toPath(), destinationFile.toPath()));
	}
}
