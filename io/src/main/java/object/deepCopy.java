package object;

import java.io.*;
import java.util.Objects;

public class deepCopy {

    public static <T> T deepCopy(T t) throws IOException, ClassNotFoundException {
        Objects.requireNonNull(t);
        // 内存缓冲区
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        // 写出
        ObjectOutputStream out = new ObjectOutputStream(buf);
        out.writeObject(t);
        // 读入
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
        T copy = (T) in.readObject();
        return copy;
    }

    public static void main(String[] args) {
        SerialCtl sc = new SerialCtl("Test1", "Test2");
        System.out.println("Before:\n" + sc);
        try {
            SerialCtl sc2 = deepCopy(sc);
            System.out.println("After:\n" + sc2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

class SerialCtl implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    String a;
    transient String b;

    public SerialCtl(String aa, String bb) {
        a = "Not Transient: " + aa;
        b = "Transient: " + bb;
    }

    public String toString() {
        return a + "\n" + b;
    }
}