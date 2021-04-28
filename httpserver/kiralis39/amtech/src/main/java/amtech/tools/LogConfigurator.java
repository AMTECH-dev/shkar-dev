package amtech.tools;

import amtech.handlers.RegFormHandler;
import amtech.registry.TemporaryData;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LogConfigurator {

    private static void configurate(Logger logger, String srcClassName) {
        try {
            FileHandler fh = new FileHandler(
                    TemporaryData.LOG_PATH + "/"
                            + srcClassName + "_"
                            + new SimpleDateFormat("dd.MM.YYYY:HH.mm.ss")
                                .format(System.currentTimeMillis())
                            + ".log");

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
