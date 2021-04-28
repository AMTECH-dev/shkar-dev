package amtech.door;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import amtech.handlers.HomePageHandler;
import amtech.handlers.RegFormHandler;
import amtech.handlers.ResourcesHandler;
import amtech.registry.TemporaryData;
import amtech.tools.LogConfigurator;
import com.sun.net.httpserver.HttpServer;

public class Main {
    private static HttpServer server;
    private static Logger LOGGER;
    private static File[] needsFilesArray;

    public static void main(String[] args) {
        filesCheck();
        loadProps();

        LOGGER = LogConfigurator.getLogger(Main.class);
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

    private static void filesCheck() {
        needsFilesArray = new File[] {
                new File("config.cfg"),
                new File("log.prop")
        };

        for (File f : needsFilesArray) {
            Path self = Paths.get(f.toURI());
            while (Files.notExists(self)) {
                LOGGER.info("Попытка создания файла '" + self + "'...");

                try {
                    Files.createFile(self);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private static void loadProps() {
        Properties config = new Properties();
        try (InputStreamReader ISR = new InputStreamReader(new FileInputStream(needsFilesArray[0]), StandardCharsets.UTF_8)) {
            config.load(ISR);

            TemporaryData.LOG_PATH = config.getProperty("logPath");
            if (TemporaryData.LOG_PATH == null) {
                config.setProperty("logPath", "./log/");
                TemporaryData.LOG_PATH = config.getProperty("logPath");
            }

            config.store(new OutputStreamWriter(new FileOutputStream(needsFilesArray[0])), "updated");
        } catch (Exception ex) {
            LOGGER.info("Проблема при чтении config.properties");
            ex.printStackTrace();
        }
    }
}