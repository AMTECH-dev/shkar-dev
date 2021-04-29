package com.company.handlers;

import com.company.enums.HttpCode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


public class MyHandlerForFilesFolder implements HttpHandler {
   // private static final Logger logger = LoggerFactory.createLoggerWithSetting(LoggerFactory.DEFAULT_CONFIG);


    public void handle(HttpExchange t) throws IOException {
        String pathRequest = t.getRequestURI().getPath();
        String pathToFile = Paths.get(pathRequest.substring(1)).toAbsolutePath().toString();

        byte[] bytes = Files.readAllBytes((Paths.get(pathToFile)));
        t.sendResponseHeaders(HttpCode.CORRECT.getNumber(), bytes.length);
        OutputStream os = t.getResponseBody();
        os.write(bytes);

        os.close();
    }
}
