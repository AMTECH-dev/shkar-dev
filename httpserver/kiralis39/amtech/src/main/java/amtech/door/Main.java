package amtech.door;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import amtech.handlers.*;
import amtech.registry.ConfigKeys;
import amtech.registry.Configurations;
import amtech.tools.LogConfigurator;
import com.sun.net.httpserver.HttpServer;

public class Main {
    private static HttpServer server;
    private static Logger LOGGER;
    private static File[] needsFilesArray;

    public static void main(String[] args) {
        System.out.println(); // empty line into console

        filesCheck();
        loadProps();
        runAccessCheck();

        LOGGER = LogConfigurator.getLogger(Main.class);
        LOGGER.info(LogConfigurator.ANSI_CYAN + "Logger Name: " + LOGGER.getName() + " is started succefull!" + LogConfigurator.ANSI_RESET);

        try {
        	String port = Configurations.globalConfig.getProperty(ConfigKeys.GLOBAL.SERVER_PORT.name());

            server = HttpServer.create(new InetSocketAddress(Integer.valueOf(port)), 0);
            server.createContext("/passport",   new PassportHandler());
            server.createContext("/seecat",     new SeeCatHandler());
            server.createContext("/page",       new HomePageHandler());
            server.createContext("/parser",     new URLParserHandler());

            server.createContext("/exit",       new MenuPageHandler());
            server.createContext("/",           new ResourcesHandler());
            server.start();
        } catch (IOException e) {
            LOGGER.warning("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void filesCheck() {
        needsFilesArray = new File[] {
                new File("config.cfg")
        };

        for (File f : needsFilesArray) {
            Path self = Paths.get(f.toURI());
            if (Files.notExists(self)) {
                try {
                    Files.createFile(self);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private static void loadProps() {
        try (InputStreamReader ISR = new InputStreamReader(new FileInputStream(needsFilesArray[0]), StandardCharsets.UTF_8)) {
            Configurations.globalConfig.load(ISR);

            Configurations.globalConfig.putIfAbsent(ConfigKeys.LOG.LOG_PATH.name(), "./log/");
            
            Configurations.globalConfig.putIfAbsent(ConfigKeys.GLOBAL.ALLOW_START.name(), "true");            
            Configurations.globalConfig.putIfAbsent(ConfigKeys.GLOBAL.SERVER_PORT.name(), "8000");
            
            Configurations.globalConfig.putIfAbsent(ConfigKeys.GLOBAL.MIN_CASH_VALUE.name(), "10");

            Configurations.globalConfig.store(new OutputStreamWriter(new FileOutputStream(needsFilesArray[0])), "updated");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void runAccessCheck() {
        if (Configurations.globalConfig.getOrDefault(ConfigKeys.GLOBAL.ALLOW_START.name(), "false").equals("false")) {
            System.exit(112);
        }
    }
}