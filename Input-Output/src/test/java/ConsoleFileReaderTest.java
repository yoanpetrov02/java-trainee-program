import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Test;

import com.sirma.javacourse.io.readtofile.ConsoleFileReader;

public class ConsoleFileReaderTest {

	@Test
	public void testFileReaderOutput() throws IOException {
		String inputs = "testfile.txt\ntest line 1\ntest line 2\n.";
		String expected = "test line 1test line 2";
		ConsoleFileReader reader = new ConsoleFileReader(
				new Scanner(new ByteArrayInputStream(inputs.getBytes())));

		reader.readToFile();

		StringBuilder actual = new StringBuilder();
		BufferedReader outputReader = new BufferedReader(
				new FileReader("testfile.txt"));
		String line;

		while ((line = outputReader.readLine()) != null) {
			actual.append(line);
		}
		Assert.assertEquals(expected, actual.toString());
	}
}
