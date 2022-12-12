package cc.momas.javase.io;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Sod-Momas
 * @since 2022/12/12
 */
public class StringPart {
    public static void main(String[] args) throws IOException {
        String str = getString();
        List<String> parts = part(str);
        for (String part : parts) {
            System.out.println(part.length() + "\t" + part);
        }
    }

    private static List<String> part(String str) throws IOException {
        int SIZE = 5;
        final List<String> ret = new ArrayList<>();
        final StringReader reader = new StringReader(str);
        int len;
//        final CharBuffer buffer = CharBuffer.allocate(SIZE);
//        while ((len = reader.read(buffer)) != -1) {
//            buffer.flip();
//            ret.add(new String(Arrays.copyOf(buffer.array(), len)));
//        }

        final char[] buffer = new char[SIZE];
        while ((len=reader.read(buffer))!=-1){
            ret.add(new String(buffer, 0, len));
        }

        return ret;
    }

    private static String getString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(UUID.randomUUID());
        }
        return sb.toString();
    }
}
