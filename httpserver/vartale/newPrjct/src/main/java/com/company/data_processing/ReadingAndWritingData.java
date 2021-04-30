package com.company.data_processing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ReadingAndWritingData {
    private static final Logger LOGGER = LogManager.getLogger(ReadingAndWritingData.class);

    private ReadingAndWritingData() {
    }

    public static byte[] readBytesFromPath(String path) {
        byte[] data = new byte[]{};
        try {
            LOGGER.info("Reading data...");
            data = Files.readAllBytes(Path.of(Paths.get(path).toUri()));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return data;
    }

    public static void writeToFile(String pathToFile, String data) {
        try {
            FileWriter fw = new FileWriter(pathToFile);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static Map<String, String> getRequestOptionsMap(String requestBody) {
        String[] requestBodyParts = requestBody.split("&");
        Map<String, String> options = new HashMap<>();

        for (String keyValue : requestBodyParts) {
            String[] kv = keyValue.split("=");

            if (kv.length > 1) options.put(kv[0], kv[1]);
        }
        return options;
    }
}
