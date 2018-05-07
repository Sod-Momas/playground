package cc.momas.demo.java.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadFromXml {
    public static void main(String[] args) {
        // 从classpath里加载文件
        InputStream in = LoadFromXml.class.getClassLoader().getResourceAsStream("test.xml");
        Properties props = new Properties();
        try {
            props.loadFromXML(in); // 加载自xml文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object obj = props.get("one"); // 取某个键的值
        System.out.println("one : " + obj);

        // 遍历,是个map
        for (Object key :props.keySet()) {
            System.out.println(key + " : " + props.get(key));
        }
    }
}
