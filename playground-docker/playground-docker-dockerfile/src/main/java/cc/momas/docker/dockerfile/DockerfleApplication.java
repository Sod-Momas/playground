package cc.momas.docker.dockerfile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Sod-Momas
 * @since 2021/11/23
 */
public class DockerfleApplication {
    public static void main(String[] args) throws IOException {
        System.out.println("hello world");

        System.out.println("java version: " + System.getProperty("java.version"));

        String config = "application.properties";
        String configName = "application.name";

        final InputStream is = DockerfleApplication.class.getClassLoader().getResourceAsStream(config);
        final Properties props = new Properties();
        props.load(is);
        final String appName = props.getProperty(configName);
        System.out.println("app name: " + appName);

    }
}
