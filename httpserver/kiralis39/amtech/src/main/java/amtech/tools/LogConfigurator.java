package amtech.tools;

import amtech.registry.ConfigKeys;
import amtech.registry.Configurations;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.logging.*;


public class LogConfigurator {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY_HH.mm.ss");
    private static final String LOGFILE_EXT = ".log";

    public static final String ANSI_RESET   = "\u001B[0m";
    public static final String ANSI_BLACK   = "\u001B[30m";
    public static final String ANSI_RED     = "\u001B[31m";
    public static final String ANSI_GREEN   = "\u001B[32m";
    public static final String ANSI_YELLOW  = "\u001B[33m";
    public static final String ANSI_BLUE    = "\u001B[34m";
    public static final String ANSI_PURPLE  = "\u001B[35m";
    public static final String ANSI_CYAN    = "\u001B[36m";
    public static final String ANSI_WHITE   = "\u001B[37m";

    private static void configurate(Logger logger, String srcClassName) {
        try {
        	if(Files.notExists(Paths.get("./log/"))) {
        		Files.createDirectory(Paths.get("./log/"));
        	}
        	
            FileHandler fileHandler = new FileHandler(
                    Configurations.globalConfig.get(ConfigKeys.LOG.LOG_PATH.name()) + "/"
                            + srcClassName + "_"
                            + sdf.format(System.currentTimeMillis())
                            + LOGFILE_EXT, 0, 30, false);

            logger.addHandler(new ConsoleHandler());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
        } catch (Exception e) {
            System.err.println("Configurations of LOGGER is FAILED. Out console only. (" + e.getMessage() + ")");
            e.printStackTrace();
        }
    }

    public static Logger getLogger(Class<?> cl) {
        Logger tmp = Logger.getLogger(cl.getName());
        configurate(tmp, cl.getSimpleName());

        return tmp;
    }
}
