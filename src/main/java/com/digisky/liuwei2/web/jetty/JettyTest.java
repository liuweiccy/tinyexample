package com.digisky.liuwei2.web.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * Jetty服务器
 *
 * @author liuwei2
 * @date 2019/10/22 18:16
 */
public class JettyTest {
    public static void main(String[] args) throws Exception {
        Server server = new Server(9632);
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setContextPath("/");
        server.setHandler(contextHandler);
        server.start();
        server.join();
    }
}
