package com.company.handlers;

import com.company.http.HttpCode;
import com.company.utils.FileUtils;
import com.sun.net.httpserver.HttpExchange;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class StaticHandler extends OurHttpHandler {
    private static final String PATH_TO_HANDLER = "/static/";

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().getPath().replace(PATH_TO_HANDLER, "");
        try {
            byte[] response = FileUtils.readAllBytesFromStaticFile(path);
            sendResponse(httpExchange, HttpCode.SUCCESS, response);
        } catch (FileNotFoundException e) {
            sendResponse(httpExchange, HttpCode.NOT_FOUND, "Resource not found".getBytes());
        } catch (URISyntaxException ignore) {
        }
    }
}
