package handlers;

import com.company.Testing;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HandlerFiles implements HttpHandler {
    private static Logger log = Testing.createLogger("logHandlerFiles.log");

    public void handle(HttpExchange t) {
        StringBuilder sb = new StringBuilder();
        String line;
        String fileName = t.getRequestURI().getPath();
        Path fullFilePath = Paths.get(fileName.substring(1)).toAbsolutePath();

        try {
            byte[] response = new byte[0];

            if (fileName.endsWith(".jpg") | fileName.endsWith(".png")) {
                response = Files.readAllBytes(new File("./" + fileName).toPath());
                t.getResponseHeaders().add("Content-Type", "image/png; charset=UTF-8");
            } else {
                try (BufferedReader io = new BufferedReader(new FileReader(fullFilePath.toString()))) {
                    while ((line = io.readLine()) != null) {
                        sb.append(line);
                    }

                    response = sb.toString().getBytes();
                    t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            t.sendResponseHeaders(200, response.length);

            try (OutputStream os = t.getResponseBody()) {
                os.write(response);
            } catch (Exception e) {
                e.printStackTrace();
                log.log(Level.SEVERE, "Test OK");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
