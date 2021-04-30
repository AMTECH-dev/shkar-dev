package com.company.data_processing;

import com.company.enums.HttpMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ParsingUrls {
    private static final Logger logger = LogManager.getLogger(ParsingUrls.class);

    public static HashSet<String> writeUrlsFromFileToSet(String pathToFile) {
        List<String> lines = new ArrayList<>();

        try {
            logger.info("Reading urls from " + pathToFile);
            lines = Files.readAllLines(Paths.get(pathToFile));

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return new HashSet<>(lines);
    }

    public Map<String, String> requestHttpHeadersFromUrl(String url) throws IOException {
        Map<String, String> headers = new HashMap<>();
        URL address = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) address.openConnection();
        try {
            connection.setRequestMethod(HttpMethod.HEAD.getMethod());

            Map<String, List<String>> reqProperties = connection.getHeaderFields();

            for (Map.Entry<String, List<String>> entry : reqProperties.entrySet()) {
                String headerName = entry.getKey();
                String headerValue = String.join(",", entry.getValue());
                headers.put(headerName, headerValue);
            }
            return headers;

        } finally {
            if (connection != null) connection.disconnect();
        }
    }
}
