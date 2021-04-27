package amtech.handlers;

import amtech.processor.GetPostClass;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class HomePageHandler implements HttpHandler {
    private StringBuilder sb;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        this.sb = new StringBuilder();
        String requestType = httpExchange.getRequestMethod();
        String data;
        byte[] response;

        if (requestType.equalsIgnoreCase("post")) {return;
        } else if (requestType.equalsIgnoreCase("get")) {
            response = Files.readAllBytes(Path.of(Paths.get("./pages/page.html").toUri()));
            GetPostClass.writeResponse(httpExchange, response);
        }
    }
}
