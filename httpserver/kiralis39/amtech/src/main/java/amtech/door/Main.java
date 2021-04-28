package amtech.door;

import java.io.*;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import amtech.handlers.HomePageHandler;
import amtech.handlers.RegFormHandler;
import amtech.handlers.ResourcesHandler;
import amtech.tools.LogConfigurator;
import com.sun.net.httpserver.HttpServer;

public class Main {
    private static final Logger LOGGER = LogConfigurator.getLogger(Main.class);
    private static HttpServer server;

    public static void main(String[] args) {
        LOGGER.info("Logger Name: " + LOGGER.getName() + " is started succefull!");

        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/reg", new RegFormHandler());
            server.createContext("/page", new HomePageHandler());
            server.createContext("/", new ResourcesHandler());
            server.start();
        } catch (IOException e) {
            LOGGER.warning("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}