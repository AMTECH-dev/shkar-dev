package com.company;

import java.io.*;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import com.company.handlers.*;

public class Main {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/form", new FormHandler());
            server.createContext("/page", new PageHandler());
            server.createContext("/files", new FilesHandler());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
