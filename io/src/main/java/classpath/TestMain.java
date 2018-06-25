package classpath;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class TestMain {

	private static final TestMain THIS = new TestMain();

	@Test

	public void test1() throws IOException, URISyntaxException {
		String resPath = "/test/test.txt";
		InputStream in = this.getClass().getResourceAsStream(resPath);
		System.out.println("location = " + this.getClass().getResource("").toURI());
		int length = in.available();
		System.out.println(length);
	}

	@Test
	public void test2() throws IOException, URISyntaxException {
		String resPath = "test.txt";
		InputStream in = this.getClass().getResourceAsStream(resPath);
		System.out.println("location = " + this.getClass().getResource("").toURI());
		int length = in.available();
		System.out.println(length);
	}

	@Test
	public void test3() throws IOException, URISyntaxException {
		String resPath = "test.txt";
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(resPath);
		System.out.println("location = " + this.getClass().getClassLoader().getResource("").toURI());
		int length = in.available();
		System.out.println(length);
	}

	@Test
	public void test4() throws IOException, URISyntaxException {
		String resPath = "test/test.txt";
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(resPath);
		System.out.println("location = " + this.getClass().getClassLoader().getResource("").toURI());
		int length = in.available();
		System.out.println(length);
	}
}
