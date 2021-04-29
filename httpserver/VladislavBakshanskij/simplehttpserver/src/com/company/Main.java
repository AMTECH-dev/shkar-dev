package com.company;

import com.company.handlers.*;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new RootHandler());
        server.createContext("/test", new TestHandler());
        server.createContext("/getForm", new ShowFormHandler());
        server.createContext("/postquery", new PostHandler());
        server.createContext("/static/"  , new StaticHandler());
        server.createContext("/api/urls/"  , new GetAllUrls());
        server.createContext("/urls"  , new ShowUrlPageHandler());
        server.start();
    }
}
