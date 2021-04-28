package amtech.tools;

import amtech.handlers.RegFormHandler;
import amtech.registry.TemporaryData;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.*;


public class LogConfigurator {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY:HH.mm.ss");
    private static final String LOGFILE_EXT = ".log";

    private static void configurate(Logger logger, String srcClassName) {
        try {
            FileHandler fh = new FileHandler(
                    TemporaryData.LOG_PATH + "/"
                            + srcClassName + "_"
                            + sdf.format(System.currentTimeMillis())
                            + LOGFILE_EXT, 0, 100, true);

            SimpleFormatter txtFormatter = new SimpleFormatter();
            fh.setFormatter(txtFormatter);

            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
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
