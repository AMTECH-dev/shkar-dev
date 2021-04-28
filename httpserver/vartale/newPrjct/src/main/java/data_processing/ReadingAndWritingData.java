package data_processing;

import com.sun.net.httpserver.HttpExchange;
import enums.HttpStatusCode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class ReadingAndWritingData {
    private static final Logger logger = Logging.createLoggerWithSetting(Logging.DEFAULT_CONFIG);

    private ReadingAndWritingData() {}

    public static void writeResponse(HttpExchange httpExchange, HttpStatusCode responseCode, byte[] data) {
        try {
            httpExchange.sendResponseHeaders(responseCode.getStatusCode(), data.length);
            logger.info("Writing response...");

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(data);
                logger.info("Writing data to OutputStream...");
            } catch (IOException e) {
                logger.warning("I/O error occurs!" + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] readBytesFromPath(String path) {
        byte[] data = new byte[]{};
        try {
            data = Files.readAllBytes(Path.of(Paths.get(path).toUri()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getRequestBody(HttpExchange exchange) throws IOException {
        StringBuilder sb = new StringBuilder();
        String data;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
            while ((data = br.readLine()) != null) {
                sb.append(data);
            }
            return sb.toString();
        }
    }
}
