package cc.momas.javase.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 测试properties工具类的入口
 *
 * @author Sod-Momas
 * @since 2021.03.04
 */
public class PropertiesApplication {

    public static void main(String[] args) {
        loadFromPath();
        loadFromXml();
        testI18n();
    }

    /**
     * 使用properties文件做i18n
     */
    private static void testI18n() {

    }

    private static void loadFromXml() {

        // 从classpath里加载文件
        InputStream in = PropertiesApplication.class.getClassLoader().getResourceAsStream("test.xml");
        Properties props = new Properties();
        try {
            props.loadFromXML(in); // 加载自xml文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object obj = props.get("one"); // 取某个键的值
        System.out.println("one : " + obj);

        // 遍历,是个map
        for (Object key : props.keySet()) {
            System.out.println(key + " : " + props.get(key));
        }
    }

    /**
     * 从路径加载properties
     */
    private static void loadFromPath() {

        try {
            // 用绝对路径
            String absPath = "C:\\Users\\sod\\Downloads\\git-dir\\javaDemo\\playground-javase\\playground-javase-properties\\src\\main\\resources\\default.properties";
            Properties defaultValues = PropertiesApplication.loadFileByAbsoludePath(absPath);
            System.out.println(defaultValues);
            // 用相对路径
            Properties pros = PropertiesApplication.loadFileByClassPath("/test.properties");
            System.out.println(pros);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 本方法要求输入绝对路径
     *
     * @param filePath 文件的绝对路径
     * @return properties文件的属性表
     * @throws IOException 经常发生于文件找不到
     */
    public static Properties loadFileByAbsoludePath(String filePath) throws IOException {
        // 输入流
        InputStream inputStream = new FileInputStream(filePath);
        // 属性表
        Properties pros = new Properties();
        pros.load(inputStream);
        return pros;
    }

    /**
     * 本方法要求输入项目classpath下的文件相对路径名,以获取该properties文件的属性表
     *
     * @param fileName classpath目录下的文件名
     * @return 属性表
     * @throws IOException 加载不到文件时
     */
    public static Properties loadFileByClassPath(String fileName) throws IOException {
        // 使用 class 属性的getResourceAsStream得到文件
        InputStream in = PropertyUtil.class.getResourceAsStream(fileName); // 获取相对路径
        Properties pros = new Properties();
        pros.load(in);
        return pros;
    }
}
