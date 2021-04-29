package com.company.factories;

import com.company.utils.FileUtils;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerFactory {
    private static final String DEFAULT_CONFIG_FILE_NAME = "application.properties";

    public static Logger createLogger(Class<?> clazz) {
        return createLoggerFromConfiguration(clazz, DEFAULT_CONFIG_FILE_NAME);
    }

    public static Logger createLoggerFromConfiguration(Class<?> clazz, String configFileName) {
        Logger logger = Logger.getLogger(clazz.getName());
        try {
            LogManager.getLogManager().readConfiguration(FileUtils.getResourceAsStream(configFileName));
        } catch (Exception ignored) {
        }

        return logger;
    }
}
