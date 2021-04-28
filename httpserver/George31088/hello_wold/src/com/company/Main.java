package com.company;

import com.sun.net.httpserver.HttpServer;
import handlers.HandlerFiles;
import handlers.HandlerPage;
import handlers.HandlerTest;

import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new HandlerTest());
        server.createContext("/page", new HandlerPage());
        server.createContext("/files", new HandlerFiles());
        server.start();
    }

}