package data_processing;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {

    public static Logger getLogger(Class className) {
        Logger logger = Logger.getLogger(className.getName());
        configLog(logger, className.getSimpleName());

        return logger;
    }

    public static void configLog(Logger logger, String className) {
        final String pathToLogsFolder = Paths.get("./logs/").toString();

        final SimpleDateFormat dateFormat= new SimpleDateFormat(" dd.MM.yyyy HH.mm.ss");
        final String logFileName = pathToLogsFolder + "/"
                        + className + "_"
                        + dateFormat.format(System.currentTimeMillis()) + ".log";

        try {
            FileHandler fileHandler = new FileHandler(logFileName);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
