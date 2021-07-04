package cc.momas.jee.servlet.spi;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Sod-Momas
 * @since 2019.11.19
 **/
@HandlesTypes(MomasWebContext.class)
public class MomasWebInitializer implements ServletContainerInitializer {

    public void onStartup(Set<Class<?>> applications, ServletContext servletContext) throws ServletException {
        System.out.println("初始化 SPI 程序");
        System.out.println("当前上下文:" + servletContext.getClass());
        if (applications != null) {
            applications.forEach(context -> System.out.println("处理上下文:" + context.getName()));
            // 执行业务代码
            process(applications, servletContext);
        }
    }

    private void process(Set<Class<?>> applications, ServletContext servletContext) throws ServletException {
        List<MomasWebContext> list = new ArrayList<>();
        for (Class<?> app : applications) {
            try {
                list.add((MomasWebContext) app.getConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        for (MomasWebContext webInit : list) {
            webInit.start(servletContext);
        }
    }
}
