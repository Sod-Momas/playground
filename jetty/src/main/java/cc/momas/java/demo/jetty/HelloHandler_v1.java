package cc.momas.java.demo.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 使用Handler处理请求
 */
public class HelloHandler_v1 extends AbstractHandler {

    private static final Logger LOG = Log.getLogger(HelloHandler_v1.class);

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("请求来自:" + target);
        response.getWriter().println("Hello world");
        baseRequest.setHandled(true);
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(80); // 实例化一个服务器实例且设置监听端口80
        server.setHandler(new HelloHandler_v1());
        server.start();
        server.join();
    }
}
