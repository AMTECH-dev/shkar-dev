package com.company;

import com.company.handlers.MyHandler;
import com.company.handlers.MyHandlerForFilesFolder;
import com.company.handlers.MyHandlerForPage;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.createContext("/page", new MyHandlerForPage());
        server.createContext("/files", new MyHandlerForFilesFolder());
        server.start();


    }


}
