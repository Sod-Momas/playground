package cc.momas.javase.compress;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

/**
 * @author Sod-Momas
 * @since 2022/9/5
 */
public class ZipMain {
    public static void main(String[] args) throws IOException {
//        final Path out = Files.createTempFile("cpe", ".xml");
        final Path out = Paths.get(System.getProperty("java.io.tmpdir")).resolve("cpe" + System.currentTimeMillis() + ".xml");
        readGZipFile(out);

        Runtime.getRuntime().exec("explorer " + out.toAbsolutePath());
    }

    private static void readGZipFile(Path out) throws IOException {
        final Path path = Paths.get("C:\\Users\\sod\\Downloads\\official-cpe-dictionary_v2.3.xml.gz");
        final InputStream is = Files.newInputStream(path);
        Files.copy(new GZIPInputStream(is), out);
    }
}
