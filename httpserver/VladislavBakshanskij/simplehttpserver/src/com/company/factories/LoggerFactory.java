package com.company.factories;

import com.company.utils.FileUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerFactory {
    private static final String CONFIG_FILE_NAME = "application.properties";
    private static final String LOG_FOLDER = "./log/";

    public static Logger createLogger(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz.getName());
        try {
            if (Files.notExists(Paths.get(LOG_FOLDER))) Files.createDirectory(Paths.get(LOG_FOLDER));
            LogManager.getLogManager().readConfiguration(FileUtils.getResourceAsStream(CONFIG_FILE_NAME));
        } catch (Exception ignored) {
        }

        return logger;
    }
}
