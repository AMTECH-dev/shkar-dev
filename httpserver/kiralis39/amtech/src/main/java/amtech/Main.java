package amtech;

import java.io.*;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class Main {

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/test", new MyHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException e) {e.printStackTrace();}
    }
}