package com.company.data_processing;

import com.sun.net.httpserver.HttpExchange;
import com.company.enums.HttpStatusCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ReadingAndWritingData {
    private static final Logger logger = LogManager.getLogger(ReadingAndWritingData.class);

    private ReadingAndWritingData() {
    }

    public static void writeResponse(HttpExchange httpExchange, HttpStatusCode responseCode, byte[] data) {
        try {
            httpExchange.sendResponseHeaders(responseCode.getStatusCode(), data.length);
            logger.info("Writing response...");

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(data);
                logger.info("Writing data to OutputStream...");
            } catch (IOException e) {
                logger.error("I/O error occurs!" + e.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public static byte[] readBytesFromPath(String path) {
        byte[] data = new byte[]{};
        try {
            logger.info("Reading data...");
            data = Files.readAllBytes(Path.of(Paths.get(path).toUri()));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return data;
    }

    public static void writeToFile(String pathToFile, String data) {
        try {
            FileWriter fw = new FileWriter(pathToFile);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static String getRequestBody(HttpExchange exchange) throws IOException {
        StringBuilder sb = new StringBuilder();
        String data;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
            logger.info("Reading data...");
            while ((data = br.readLine()) != null) {
                sb.append(data);
            }
            return sb.toString();
        }
    }

    public static Map<String, String> getRequestOptions(String requestBody) {
        String[] requestBodyParts = requestBody.split("&");
        Map<String, String> options = new HashMap<>();

        for (String keyValue : requestBodyParts) {
            String[] kv = keyValue.split("=");

            if (kv.length > 1) options.put(kv[0], kv[1]);
        }
        return options;
    }
}
