package cc.momas.java.demo.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 使用Servlet处理请求
 */
public class HelloHandler_v2 {

    private static final Logger LOG = Log.getLogger(HelloHandler_v2.class);

    public static void main(String[] args) throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setResourceBase("./src/main/resources/webapp");
        context.addServlet(new ServletHolder(new HelloServlet()),"/v2"); // 匹配访问地址/v2

        Server server = new Server(80);
        server.setHandler(context);
        server.start();
        server.join();

    }
}

class HelloServlet implements Servlet{

    private static final Logger LOG = Log.getLogger(HelloServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        LOG.info("请求来自 : " + req.getRemoteAddr());
        res.getWriter().println("hello v2 servlet");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}