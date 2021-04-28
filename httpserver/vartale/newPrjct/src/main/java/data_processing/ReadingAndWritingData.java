package data_processing;

import com.sun.net.httpserver.HttpExchange;
import enums.HttpStatusCode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;


public class ReadingAndWritingData {
    private static final Logger LOGGER = Logging.getLogger(ReadingAndWritingData.class);

    private ReadingAndWritingData() {}

    public static void writeResponse(HttpExchange httpExchange, HttpStatusCode responseCode, byte[] data) {
        try {
            httpExchange.sendResponseHeaders(responseCode.getStatusCode(), data.length);

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(data);

                LOGGER.info("Data are writing to " + os.getClass());
            } catch (IOException e) {
                LOGGER.warning("Too many bytes are written!\n" + e.getMessage());
            }
        } catch (IOException e) {
            LOGGER.warning("No response body is being sent!\n" + e.getMessage());
        }
    }

    public static byte[] readBytesFromPath(String path) {
        byte[] data = new byte[]{};
        try {
            data = Files.readAllBytes(Path.of(Paths.get(path).toUri()));
            LOGGER.info("Data are reading from " + path);
        } catch (IOException e) {
            LOGGER.warning("Path string cannot be converted to a Path!\n" + e.getMessage());
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
