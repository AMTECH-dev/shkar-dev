package com.company.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


class MyHandlerForFilesFolder implements HttpHandler {

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
