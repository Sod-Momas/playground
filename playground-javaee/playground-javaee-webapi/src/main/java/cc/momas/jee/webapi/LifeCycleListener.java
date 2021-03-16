package cc.momas.jee.webapi;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 应用级别监听器，监听应用启动与销毁
 *
 * @author Sod-Momas
 * @since 2021-03-16
 */
@WebListener
public class LifeCycleListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("application startup.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("application shutdown.");
    }
}
