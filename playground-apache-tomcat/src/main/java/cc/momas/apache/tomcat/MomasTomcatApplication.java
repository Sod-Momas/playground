package cc.momas.apache.tomcat;

import org.apache.catalina.*;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Objects;

/**
 * 启动容器, 入口主类
 *
 * @author Sod-Momas
 * @since 2019.11.19
 **/
public class MomasTomcatApplication {
    public static void main(String[] args) throws LifecycleException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        preCheck();
        startTomcat();
    }

    private static void preCheck() {
        URL classpathUrl = MomasTomcatApplication.class.getClassLoader().getResource("static");
        System.out.println(classpathUrl.getPath());
    }

    private static void startTomcat() throws LifecycleException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8081);

        // 配置一个任意目录
        URL classpathUrl = MomasTomcatApplication.class.getClassLoader().getResource("static");

        Context context = tomcat.addContext("/", System.getProperty("java.io.tmpdir"));
//        Context context = tomcat.addContext("/", Objects.requireNonNull(classpathUrl).getPath());
        // 添加生命周期
        String configClassName = tomcat.getHost().getConfigClass();
        LifecycleListener listener = (LifecycleListener) Class.forName(configClassName).getConstructor().newInstance();
        context.addLifecycleListener(listener);
        context.addWelcomeFile("index.html");

        tomcat.start();
        System.out.println("tomcat start at http://localhost:" + tomcat.getServer().getPort());
        tomcat.getServer().await();
    }
}
