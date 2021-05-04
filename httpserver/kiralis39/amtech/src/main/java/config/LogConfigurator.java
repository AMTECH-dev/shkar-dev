package config;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.*;



public class LogConfigurator {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY_HH.mm.ss");
    private static final String LOGFILE_EXT = ".log";
    
	private static Logger LOGGER;	
    private static FileHandler fileHandler;
    
    static {
    	trimLogsFolder();
    }
    
    
    private static void configurate(Logger logger, String srcClassName) {
        try {
        	
        	if(Files.notExists(Paths.get("./log/"))) {
        		Files.createDirectory(Paths.get("./log/"));
        	}
        	
        	if (fileHandler == null) {
	            fileHandler = new FileHandler(
	                    Configurations.globalConfig.get(ConfigKeys.LOG.LOG_PATH.name()) + "/"
	                            + srcClassName + "_"
	                            + sdf.format(System.currentTimeMillis())
	                            + LOGFILE_EXT, false) {
	            	{
	            		setLevel(Level.ALL);
	            		setFormatter(new SimpleFormatter());
	            	}
	            };
        	}

            logger.addHandler(fileHandler);
            
        } catch (Exception e) {
            System.err.println("Configurations of LOGGER is FAILED. Out console only. (" + e.getMessage() + ")");
            e.printStackTrace();
        }
    }

    private static void trimLogsFolder() {
		int maxCount = Integer.parseInt((String) Configurations.globalConfig.get(ConfigKeys.LOG.PREFERRED_LOG_COUNT.name()));		
		File[] logz = new File((String) Configurations.globalConfig.get(ConfigKeys.LOG.LOG_PATH.name())).listFiles();
		
		if (logz.length > maxCount) {
			Deque<File> fstc = new ArrayDeque<File>();
			for (File f : logz) {
				fstc.addFirst(f);
			}
			
			for (int i = 0; i < logz.length - maxCount; i++) {
				try {
					Files.delete(logz[i].toPath());
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
	}

    @Deprecated
	public static Logger getLogger(Class<?> cl) {
        Logger tmp = Logger.getLogger(cl.getName());
        configurate(tmp, cl.getSimpleName());

        return tmp;
    }
	
	public static Logger getLogger() {
		if (LOGGER == null) {
			LOGGER = Logger.getLogger(Thread.currentThread().getClass().getName());
	        configurate(LOGGER, Thread.currentThread().getClass().getName());
		}
		
        return LOGGER;
    }
}