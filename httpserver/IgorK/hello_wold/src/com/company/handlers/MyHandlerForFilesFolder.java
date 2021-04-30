package com.company.handlers;

import com.company.enums.HttpCode;
import com.company.utils.SendResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class MyHandlerForFilesFolder implements HttpHandler {
    // private static final Logger logger = LoggerFactory.createLoggerWithSetting(LoggerFactory.DEFAULT_CONFIG);

    public void handle(HttpExchange t) throws IOException {
        String pathRequest = t.getRequestURI().getPath();
        String pathToFile = Paths.get(pathRequest.substring(1)).toAbsolutePath().toString();
        System.out.println(pathToFile.toString());
        byte[] bytes = Files.readAllBytes((Paths.get(pathToFile)));
        SendResponse.response(t, HttpCode.CORRECT.getNumber(), bytes);
    }
}
