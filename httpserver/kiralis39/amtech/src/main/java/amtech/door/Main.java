package amtech.door;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import amtech.handlers.*;
import config.ConfigKeys;
import config.Configurations;
import config.LogConfigurator;

import com.sun.net.httpserver.HttpServer;


public class Main {
    private static HttpServer server;
    private static Logger LOGGER;
    private static File[] needsFilesArray;

    
    public static void main(String[] args) {
        System.out.println(); // empty line into console

        primaryFilesExistsCheck();
        loadProps();
        logsDirExistsCheck((String) Configurations.globalConfig.get(ConfigKeys.LOG.LOG_PATH.name()));
        runAccessCheck();
        logEnabledCorrectlyTest();

        try {
        	String port = Configurations.globalConfig.getProperty(ConfigKeys.GLOBAL.SERVER_PORT.name());

            server = HttpServer.create(new InetSocketAddress(Integer.valueOf(port)), 0);
            
            server.createContext("/seecat",     new SeeCatHandler());
            server.createContext("/catspage",   new CatsPageHandler());
            
            server.createContext("/parser",     new URLParserHandler());
            server.createContext("/passport",   new PassportHandler());

            server.createContext("/exit",       new MenuPageHandler());
            server.createContext("/",           new ResourcesHandler());
            
            server.start();
        } catch (Exception e) {
            LOGGER.severe("SERVER START ERROR: " + e.getMessage());
            e.printStackTrace();
            System.exit(124);
        }
    }

	private static void primaryFilesExistsCheck() {
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
            Configurations.globalConfig.putIfAbsent(ConfigKeys.LOG.PREFERRED_LOG_COUNT.name(), "30");
            
            
            Configurations.globalConfig.putIfAbsent(ConfigKeys.GLOBAL.ALLOW_START.name(), "true");            
            Configurations.globalConfig.putIfAbsent(ConfigKeys.GLOBAL.SERVER_PORT.name(), "8000");
            
            Configurations.globalConfig.putIfAbsent(ConfigKeys.GLOBAL.PREFERRED_CASH_VALUE.name(), "10");

            Configurations.globalConfig.store(new OutputStreamWriter(new FileOutputStream(needsFilesArray[0])), "updated");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void logsDirExistsCheck(String logDirPath) {
    	if (Files.notExists(Paths.get(logDirPath))) {
    		try {
				Files.createDirectory(Paths.get(logDirPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
	}

	private static void runAccessCheck() {
        if (Configurations.globalConfig.getOrDefault(ConfigKeys.GLOBAL.ALLOW_START.name(), "false").equals("false")) {
            System.exit(112);
        }
    }
	
	private static void logEnabledCorrectlyTest() {
    	LOGGER = LogConfigurator.getLogger();
        LOGGER.info("Logger '" + LOGGER.getName() + "' started succefull!");
	}
}