package com.company.handlers;

import com.company.factories.LoggerFactory;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestHandler extends OurHttpHandler {
    private static final Logger logger = LoggerFactory.createLogger(TestHandler.class);

    @Override
    public void handle(HttpExchange exchange) {
        try {
            sendResponse(exchange, HttpURLConnection.HTTP_OK, "THIS IS TEST HANDLER");
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}