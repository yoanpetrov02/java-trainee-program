package com.sirma.javacourse.io.directorybrowser;

import java.io.File;
import org.junit.Assert;
import org.junit.Test;

public class DirectoryBrowserTest {

	@Test
	public void testListContentValidPath() {
		String expected = "testfile.txttestfile2.txttestfoldertestfolder2";
		StringBuilder actual = new StringBuilder();

		for (File file : DirectoryBrowser.
				listContent("src/test/resources/directorybrowsertest")) {
			actual.append(file.getName());
		}

		Assert.assertEquals(expected, actual.toString());
	}

	@Test
	public void testListContentInvalidPath() {
		Assert.assertArrayEquals(new File[] {}, DirectoryBrowser.listContent("invalidpath"));
		Assert.assertArrayEquals(new File[] {}, DirectoryBrowser.listContent("invalid/path"));
		Assert.assertArrayEquals(new File[] {}, DirectoryBrowser.listContent(""));
		Assert.assertArrayEquals(new File[] {}, DirectoryBrowser.listContent(null));
		Assert.assertArrayEquals(new File[] {},
				DirectoryBrowser.listContent("src/test/resources/directorybrowsertest/testfile.txt"));
	}
}
