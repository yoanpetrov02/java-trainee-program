package com.sirma.javacourse.io.directorybrowser;

import java.util.Scanner;

public class DirectoryBrowserRunner {

	private static final Scanner INPUT = new Scanner(System.in);

	public static void main(String[] args) {
		String path = INPUT.nextLine();

		DirectoryBrowser.listContent(path);
	}
}
