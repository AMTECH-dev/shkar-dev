package com.company.handlers;

import com.company.SimpleHttpServer;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.lang.*;


public interface OurHttpHandler extends HttpHandler {
    final String PAGE_FOLDER = "pages/";

    default byte[] readAllBytesFromPage(String pageName) throws URISyntaxException, IOException {
        /*
        URL url=SimpleHttpServer.class.getResource(Path.of(PAGE_FOLDER + pageName).toString());
        System.out.println(url);
        //if (url==null) System.out.println("url NULL");
        URI uri=url.toURI();
        System.out.println(uri);

        return Files.readAllBytes(Paths.get(uri));*/

        Path path = Paths.get("src/com/company/pages",pageName);
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);
        return Files.readAllBytes(absolutePath);
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
