import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;

//https://urvanov.ru/2019/07/03/%D0%BB%D0%BE%D0%B3%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5-%D1%81-jul-java-util-logging/
//https://javarush.ru/groups/posts/2200-logirovanie-razmotatjh-klubok-stektreysa

public class LoggerFactory {

    static {
        try{
            FileInputStream in=new FileInputStream("log.prop");
            LogManager.getLogManager().readConfiguration(in);
        } catch (IOException e) {
            System.err.println("LOGGER: Can't read config log from log.prop");
            e.printStackTrace();
        }
    }

    static Logger getLogger(Class clazz) {
        Logger log = Logger.getLogger(clazz.getName());

        log.setLevel(Level.ALL);

        String patternName="logs/"+clazz.getName()+"%u.log";
        try {
            FileHandler fh = new FileHandler(patternName,1000,3,true);
            fh.setFormatter(new SimpleFormatter());
            log.addHandler(fh);
        } catch (IOException e) {
            System.err.println("Logging only to console!");
            e.printStackTrace();
        }

        /*if (log.isLoggable(Level.FINE)) {
            log.fine("Test LOG:Text is FINE!");
            log.log(Level.WARNING,"Exception:",new Exception("Test Exception"));
        }*/

        return log;
    }
}
