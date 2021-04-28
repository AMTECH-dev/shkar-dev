package data_processing;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Logging {
    public static final String DEFAULT_CONFIG = "logging.properties";

    public static Logger createLoggerWithSetting(String fileSettings) {
        Logger logger = Logger.getLogger(Logging.class.getName());
        try {
            LogManager.getLogManager().readConfiguration(Logging.class.getResourceAsStream(fileSettings));
        } catch (Exception ignored) {
            logger.severe("Error in Logging class");
        }
        return logger;
    }
}