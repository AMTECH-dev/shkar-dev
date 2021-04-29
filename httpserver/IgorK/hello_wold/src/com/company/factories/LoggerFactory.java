package com.company.factories;

import com.company.Main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public final class LoggerFactory {
    public static final String DEFAULT_CONFIG = "logging.properties";

    public static Logger createLogger() {
        return createLoggerWithDefaultSetting(LoggerFactory.class);
    }

    public static Logger createLoggerWithDefaultSetting(Class<?> clazz) {
        return createLoggerWithSetting(clazz, DEFAULT_CONFIG);
    }

    public static Logger createLoggerWithSetting(Class<?> clazz, String fileSettings) {
        Logger log = Logger.getLogger(clazz.getName());
        try {
            LogManager.getLogManager().readConfiguration(ClassLoader.getSystemClassLoader().getResourceAsStream(fileSettings));
        } catch (IOException ignored) {
            ignored.getMessage();
            log.severe("ERROR in LoggerFactory FILE" );
        }
        return log;
    }
}
