package com.company.utils;

import com.company.factories.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class WriteToFile {
    private static final Logger logger = LoggerFactory.createLogger();
    public static void write(String s) throws IOException {
        logger.info("Start write method, class WriteToFile ");
        FileWriter fw = null;
        try {
            fw = new FileWriter("UserInfo.txt", false);
        } catch (IOException e) {
            logger.severe("Exceptions: "+ WriteToFile.class.getName());
            e.getMessage();
        }
        fw.write(s);
        fw.flush();
    }
}