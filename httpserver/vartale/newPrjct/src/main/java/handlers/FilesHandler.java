package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.file.Paths;

public class FilesHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) {
        String responsePath = httpExchange.getRequestURI().getPath();
        String pathToFile = Paths.get(responsePath.substring(1)).toAbsolutePath().toString();
        try {
            ResponseHandling.writeResponse(httpExchange, ResponseHandling.getResponse(pathToFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
