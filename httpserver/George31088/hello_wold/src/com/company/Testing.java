package com.company;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Testing {
    public static final String DEFAULT_CONFIG = "log/logging.properties";
    public static Logger createLogger(String fileName) {
        Logger logger = Logger.getLogger("status");
        logger.addHandler(new ConsoleHandler());
        try {
            logger.addHandler(new FileHandler("log/" + fileName));
        } catch (IOException ignored) {
        }
        return logger;
    }
}
