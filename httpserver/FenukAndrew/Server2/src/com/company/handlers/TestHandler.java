package com.company.handlers;

import com.company.http.HttpCode;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class TestHandler implements OurHttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        byte[] response = "Welcome Real's HowTo test page".getBytes();
        exchange.sendResponseHeaders(HttpCode.SUCCESS, response.length);
        OutputStream os = exchange.getResponseBody();
        os.write(response);
        os.close();
    }
}