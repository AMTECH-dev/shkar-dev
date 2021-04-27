package com.company.handlers;

import com.company.SimpleHttpServer;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public interface OurHttpHandler extends HttpHandler {

    default byte[] readAllBytesFromPage(String pageName) throws URISyntaxException, IOException {
        return Files.readAllBytes(Paths.get(SimpleHttpServer.class.getResource("./pages/" + pageName).toURI()));
    }

    default Map<String, String> getParams(String body) {
        Map<String, String> parameters = new HashMap<>();
        for (String pair : body.split("&")) {
            String[] keyValue = pair.split("=");
            String key = keyValue[0];

            if (keyValue.length > 1) {
                parameters.put(key, keyValue[1]);
            }
        }

        return parameters;
    }
}
