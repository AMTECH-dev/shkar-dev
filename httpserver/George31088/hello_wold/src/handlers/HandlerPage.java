package handlers;

import com.company.Testing;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HandlerPage implements HttpHandler {
    private static final Logger log = Testing.createLogger("logHandlerPage.log");

    @Override
    public void handle(HttpExchange t) {

        StringBuilder sb = new StringBuilder();
        String line;

        try (BufferedReader io = new BufferedReader(new FileReader("./pages/page.html"))) {
            while ((line = io.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.log(Level.SEVERE, "Test OK");

        String result = sb.toString();
        byte[] response = result.getBytes(StandardCharsets.UTF_8);
        t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        try (OutputStream os = t.getResponseBody()) {
            t.sendResponseHeaders(200, response.length);
            os.write(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
