package handlers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResponseHandling {

    static void writeResponse(HttpExchange httpExchange, byte[] writeData) {
        try {
            httpExchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            httpExchange.sendResponseHeaders(200, writeData.length);

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(writeData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static byte[] getResponse(String path) throws IOException {
        return Files.readAllBytes(Path.of(Paths.get(path).toUri()));
    }
}
