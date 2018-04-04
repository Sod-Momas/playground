package cc.momas.java.demo.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class HelloHandler_v3 {

    public static void main(String[] args) throws Exception {

        ResourceHandler handler = new ResourceHandler();
        handler.setDirectoriesListed(true);
        handler.setWelcomeFiles(new String [] {"index.html"}); // 设置 welcome list
        handler.setResourceBase("./jetty/src/main/resources/webapp");  // 这个路径太邪门了...

        Server server = new Server(80); // 实例化一个服务器实例且设置监听端口80
        server.setHandler(handler);
        server.start();
        server.join();
    }



}
