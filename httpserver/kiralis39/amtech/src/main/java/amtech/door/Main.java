package amtech.door;

import java.io.*;
import java.net.InetSocketAddress;

import amtech.handlers.HomePageHandler;
import amtech.handlers.RegFormHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/reg", new RegFormHandler());
            server.createContext("/page", new HomePageHandler());
//          server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException e) {e.printStackTrace();}
    }
}