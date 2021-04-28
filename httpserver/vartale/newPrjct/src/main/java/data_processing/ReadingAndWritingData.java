package data_processing;

import com.sun.net.httpserver.HttpExchange;
import enums.HttpStatusCode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadingAndWritingData {

    private ReadingAndWritingData() {
    }

    public static void writeResponse(HttpExchange httpExchange, HttpStatusCode responseCode, byte[] data) {
        try {
            httpExchange.sendResponseHeaders(responseCode.getStatusCode(), data.length);

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
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
