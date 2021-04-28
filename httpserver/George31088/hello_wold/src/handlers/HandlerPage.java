package handlers;

import bin.Testing;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class HandlerPage implements HttpHandler {
private static final Logger log = Testing.createLogger("log1.log");
    public void handle(HttpExchange t) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader io = new BufferedReader(new FileReader("page.html"));
        while ((line = io.readLine()) != null) {
            sb.append(line);
        }

        String result = sb.toString();
        io.close();

        byte[] response = result.getBytes(StandardCharsets.UTF_8);
        t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        t.sendResponseHeaders(200, response.length);
        OutputStream os = t.getResponseBody();
        os.write(response);
        os.close();
    }
}
