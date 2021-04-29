package com.company.handlers;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public final class LoggerFactory {
    public static final String DEFAULT_CONFIG = "logging.properties";

    public static Logger createLoggerWithSetting(String fileSettings) {
        Logger log = Logger.getLogger(LoggerFactory.class.getName());
        try {
            LogManager.getLogManager().readConfiguration(LoggerFactory.class.getResourceAsStream(fileSettings));
        } catch (IOException ignored) {
            ignored.getMessage();
            log.severe("ERROR in LoggerFactory FILE" );

        }
        return log;
    }
}
