package amtech.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import amtech.registry.TemporaryData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class HomePageHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestType = httpExchange.getRequestMethod();

        if (requestType.equalsIgnoreCase(TemporaryData.POST)) {return;
        } else if (requestType.equalsIgnoreCase(TemporaryData.GET)) {
        	byte[] response = Files.readAllBytes(Path.of(Paths.get("./pages/page.html").toUri()));
            httpExchange.getResponseHeaders().add(TemporaryData.CONTENT_TYPE, TemporaryData.TEXT_HTML);
            writeResponse(httpExchange, response);
        }
    }
    
    private static void writeResponse(HttpExchange httpExchange, byte[] writeData) {
        try {
            httpExchange.sendResponseHeaders(TemporaryData.OK, writeData.length);

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(writeData);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
}
