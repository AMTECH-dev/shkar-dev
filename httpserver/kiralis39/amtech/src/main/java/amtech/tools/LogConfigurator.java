package amtech.tools;

import amtech.registry.ConfigKeys;
import amtech.registry.Configurations;

import java.text.SimpleDateFormat;
import java.util.logging.*;


public class LogConfigurator {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY:HH.mm.ss");
    private static final String LOGFILE_EXT = ".log";

    private static void configurate(Logger logger, String srcClassName) {
        try {
            FileHandler fileHandler = new FileHandler(
                    Configurations.globalConfig.get(ConfigKeys.LOG.LOG_PATH.name()) + "/"
                            + srcClassName + "_"
                            + sdf.format(System.currentTimeMillis())
                            + LOGFILE_EXT, 0, 30, true);

            logger.addHandler(new ConsoleHandler());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (Exception e) {
            System.err.println("Configurations of LOGGER is FAILED. Out console only. (" + e.getMessage() + ")");
            e.printStackTrace();
        }
    }

    public static Logger getLogger(Class cl) {
        Logger tmp = Logger.getLogger(cl.getName());
        configurate(tmp, cl.getSimpleName());

        return tmp;
    }
}
