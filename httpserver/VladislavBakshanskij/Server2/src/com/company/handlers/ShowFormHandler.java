package com.company.handlers;

import com.company.http.HttpCode;
import com.company.http.HttpHeader;
import com.company.utils.FileUtils;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;

public class ShowFormHandler extends OurHttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            byte[] response = FileUtils.readAllBytesFromPage("form.html");
            exchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE, "text/html; charset=utf-8");
            exchange.sendResponseHeaders(HttpCode.SUCCESS, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        } catch (URISyntaxException ignored) {
        }
    }
}