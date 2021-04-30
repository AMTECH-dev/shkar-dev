package handlers;

import com.company.Testing;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HandlerUrl implements HttpHandler {
    private static Logger log = Testing.createLogger("logHandlerUrl.log");

    public void handle(HttpExchange t) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader io = new BufferedReader(new FileReader("files/urls.txt"));
        while ((line = io.readLine()) != null) {
            HashSet<String> urlHashSet = new HashSet<>();
            for (int a = 0; a > urlHashSet.size(); a++) {
                urlHashSet.add(line);
            }
        }
// NOT FINISHED !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
        String result = sb.toString();
        io.close();


        byte[] response = result.getBytes(StandardCharsets.UTF_8);
        t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        t.sendResponseHeaders(200, response.length);
        OutputStream os = t.getResponseBody();
        os.write(response);
        os.close();
        log.log(Level.SEVERE, "Test OK");
    }

}