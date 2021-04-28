package com.company.factories;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LoggerFactory {
    private static final String PATH_TO_LOG_DIR = "log/";
    private static final String LOG_FILE_EXTENSION = "log";

    public static Logger createLoggerWithConfiguration(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz.getName());
        String filePattern = PATH_TO_LOG_DIR + clazz.getSimpleName() + "%u." + LOG_FILE_EXTENSION;
        try {
            boolean append = true;
            int fileSizeByte = 1000_000_000;
            logger.addHandler(new FileHandler(filePattern, fileSizeByte, 3, append));
        } catch (Exception ignored) {
        }

        return logger;
    }
}
