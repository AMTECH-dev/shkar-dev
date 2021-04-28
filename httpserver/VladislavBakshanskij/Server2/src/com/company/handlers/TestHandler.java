package com.company.handlers;

import com.company.factories.LoggerFactory;
import com.company.http.HttpCode;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestHandler extends OurHttpHandler {
    private static final Logger logger = LoggerFactory.createLoggerWithConfiguration(TestHandler.class);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            sendResponse(exchange, HttpCode.SUCCESS, "THIS IS TEST HANDLER");
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}