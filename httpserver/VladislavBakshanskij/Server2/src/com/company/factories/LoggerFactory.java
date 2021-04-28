package com.company.factories;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LoggerFactory {
    private static final String PATH_TO_LOG_DIR = "log/";
    private static final String LOG_FILE_EXTENSION = "log";

    private static final int FILE_SIZE_IN_BYTE = 1000_000_000;
    private static final boolean APPEND = true;
    private static final int FILE_COUNT = 3;

    public static Logger createLoggerWithConfiguration(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz.getName());
        String filePattern = PATH_TO_LOG_DIR + clazz.getSimpleName() + "%u." + LOG_FILE_EXTENSION;
        try {
            logger.addHandler(new FileHandler(filePattern, FILE_SIZE_IN_BYTE, FILE_COUNT, APPEND));
        } catch (Exception ignored) {
        }

        return logger;
    }
}
