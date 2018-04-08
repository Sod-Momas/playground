package cc.momas.demo.java.properties;

import java.io.IOException;
import java.util.Properties;

public class TestMain {

    public static void main(String[] args) {
        try {
            // 用绝对路径
            Properties defaultValues = new PropertyUtil().loadFileByAbsoludePath("C:\\Users\\sod\\Downloads\\gitDir\\javaDemo\\properties\\src\\main\\resources\\default.properties");
            System.out.println(defaultValues);
            // 用相对路径
            Properties pros = new PropertyUtil().loadFileByClassPath("/test.properties");
            System.out.println(pros);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
