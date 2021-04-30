package com.company;

import com.sun.net.httpserver.HttpServer;
import handlers.HandlerFiles;
import handlers.HandlerPage;
import handlers.HandlerTest;
import handlers.HandlerUrl;

import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static Logger log = Testing.createLogger("logMain.log");

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new HandlerTest());
        server.createContext("/page", new HandlerPage());
        server.createContext("/files", new HandlerFiles());
        server.createContext("/Cat.jpg", new WaterMarkImage());
        server.createContext("/files/urls.txt", new HandlerUrl());
        server.start();


        log.log(Level.SEVERE, "Test OK");

    }
}

