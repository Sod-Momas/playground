package cc.momas.javase.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

/**
 * 加载类路径下的资源
 *
 * @author Sod-Momas
 * @since 2021.03.12
 */
public class ClassPathResourceIO {
    private static final ClassPathResourceIO THIS = new ClassPathResourceIO();

    public static void main(String[] args) throws IOException, URISyntaxException {
//        THIS.test1();
//        THIS.test2();
//        THIS.test3();
//        THIS.test4();
        String resourceName = "test.txt";
        loadClasspathResource(resourceName);
    }

    private static void loadClasspathResource(String resourceName) throws IOException {
        final var txt = ClassPathResourceIO.class.getClassLoader().getResourceAsStream(resourceName);
        if (txt == null) {
            return;
        }
        System.out.println(new String(txt.readAllBytes()));
        txt.close();
    }

    public void test1() throws IOException, URISyntaxException {
        String resPath = "/test/test.txt";
        InputStream in = this.getClass().getResourceAsStream(resPath);
        System.out.println("location = " + this.getClass().getResource("").toURI());
        int length = in.available();
        System.out.println(length);
    }

    public void test2() throws IOException, URISyntaxException {
        String resPath = "test.txt";
        InputStream in = this.getClass().getResourceAsStream(resPath);
        System.out.println("location = " + this.getClass().getResource("").toURI());
        int length = in.available();
        System.out.println(length);
    }

    public void test3() throws IOException, URISyntaxException {
        String resPath = "test.txt";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(resPath);
        System.out.println("location = " + this.getClass().getClassLoader().getResource("").toURI());
        int length = in.available();
        System.out.println(length);
    }

    public void test4() throws IOException, URISyntaxException {
        String resPath = "test/test.txt";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(resPath);
        System.out.println("location = " + this.getClass().getClassLoader().getResource("").toURI());
        int length = in.available();
        System.out.println(length);
    }
}
