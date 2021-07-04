package cc.momas.jee.servlet.spi;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 实现我这个接口就可以成为一个独立第三方context啦!
 *
 * @author Sod-Momas
 * @since 2019.11.19
 **/
public interface MomasWebContext {
    void start(ServletContext servletContext) throws ServletException;
}
