package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HandlerFiles implements HttpHandler {

    public void handle(HttpExchange t) {
        StringBuilder sb = new StringBuilder();
        String line;
        String fileName = t.getRequestURI().getPath();
        Path fullFilePath = Paths.get(fileName.substring(1)).toAbsolutePath();
        try {
            BufferedReader io = new BufferedReader(new FileReader(fullFilePath.toString()));
            while ((line = io.readLine()) != null) {
                sb.append(line);
            }

            String result = sb.toString();
            io.close();

            byte[] response = result.getBytes( /*StandardCharsets.UTF_8*/);
            // t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            t.sendResponseHeaders(200, response.length);
            OutputStream os = t.getResponseBody();
            os.write(response);
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
