package cc.momas.jee.servlet.spi;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 第三方app, 这个类相当于一个war
 *
 * @author Sod-Momas
 * @since 2019.11.19
 **/
public class MomasWebStarter implements MomasWebContext {
    @Override
    public void start(ServletContext servletContext) {
        System.out.print("我是个第三方类, 作为一个独立上下文开启,类名为:[");
        System.out.print(getClass());
        System.out.print("] 上下文名为:[");
        System.out.print(servletContext.getClass());
        System.out.println(']');
    }
}
