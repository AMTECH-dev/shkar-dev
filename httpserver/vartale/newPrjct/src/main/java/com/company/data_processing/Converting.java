package com.company.data_processing;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Converting {
    private static final Logger LOGGER = LogManager.getLogger(ParsingUrl.class);

    public static String convertMapToJSON(Map<String, String> map) {
        return new Gson().toJson(map);
    }

    public static HashSet<String> writeUrlsFromFileToSet(String pathToFile) {
        List<String> lines = new ArrayList<>();

        try {
            LOGGER.info("Reading urls from " + pathToFile);
            lines = Files.readAllLines(Paths.get(pathToFile));

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return new HashSet<>(lines);
    }

    public static Path toAbsPath(String path) {
        return Paths.get(path).toAbsolutePath();
    }

    public static String stringToAbsPathToString(String path) {
        return toAbsPath(path).toString();
    }
}
