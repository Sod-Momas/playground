package cc.momas.javase.io;

import java.io.*;

/**
 * 深复制示例
 *
 * @author Sod-Momas
 * @since 2021.03.12
 */
public class DeepCopy {

    @SuppressWarnings("unchecked")
    public static <T> T deepCopy(T t) {
        if (t == null) {
            return null;
        }
        try {

            // 输出内存缓冲区
            var outBuffer = new ByteArrayOutputStream();
            // 序列化器
            var serializer = new ObjectOutputStream(outBuffer);
            serializer.writeObject(t);
            // 输入内存缓冲区
            var inBuffer = new ByteArrayInputStream(outBuffer.toByteArray());
            // 反序列化器
            var deserializer = new ObjectInputStream(inBuffer);
            var newObj = (T) deserializer.readObject();
            outBuffer.close();
            serializer.close();
            inBuffer.close();
            deserializer.close();
            return newObj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        var oldObj = new SerialCtl("Test1", "Test2");
        System.out.println("Before: " + oldObj);
        SerialCtl newObj = deepCopy(oldObj);
        System.out.println("After: " + newObj);
    }
}

class SerialCtl implements Serializable {
    private static final long serialVersionUID = 1L;
    String a;
    // transient 节点不会被序列化
    transient String b;

    public SerialCtl(String aa, String bb) {
        a = "Not Transient: " + aa;
        b = "Transient: " + bb;
    }

    @Override
    public String toString() {
        return "SerialCtl{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }
}