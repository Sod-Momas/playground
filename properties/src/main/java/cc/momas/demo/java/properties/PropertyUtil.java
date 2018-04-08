package cc.momas.demo.java.properties;

import java.io.*;
import java.util.Properties;

/**
 * 本工具类用于读取Properties文件
 */
public class PropertyUtil {

    /**
     * 本方法要求输入绝对路径
     * @param filePath 文件的绝对路径
     * @return properties文件的属性表
     * @throws IOException 经常发生于文件找不到
     */
    public Properties loadFileByAbsoludePath(String filePath) throws IOException {
        // 输入流
        InputStream  inputStream = new FileInputStream(filePath);
        // 属性表
        Properties pros = new Properties();
        pros.load(inputStream);
        return pros;
    }

    /**
     * 本方法要求输入项目classpath下的文件相对路径名,以获取该properties文件的属性表
     * @param fileName classpath目录下的文件名
     * @return 属性表
     * @throws IOException
     */
    public Properties loadFileByClassPath(String fileName) throws IOException {
        // 使用 class 属性的getResourceAsStream得到文件
        InputStream in = Class.class.getResourceAsStream(fileName); // 获取相对路径
        Properties pros = new Properties();
        pros.load(in);
        return pros;
    }

}
