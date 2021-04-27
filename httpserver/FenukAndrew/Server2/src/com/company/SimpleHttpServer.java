package com.company;

import com.company.handlers.*;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class SimpleHttpServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new RootHandler());
        server.createContext("/test", new TestHandler());
        server.createContext("/getForm", new ShowFormHandler());
        server.createContext("/postquery", new PostHandler());

        server.createContext("/page", new PageHandler());
        server.createContext("/static", new StaticHandler());

        server.setExecutor(null);
        server.start();

    }
}
