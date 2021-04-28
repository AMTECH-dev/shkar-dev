package com.company.handlers;

import com.company.factories.LoggerFactory;
import com.company.http.HttpCode;
import com.company.http.HttpContentType;
import com.company.http.HttpHeader;
import com.company.utils.FileUtils;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowFormHandler extends OurHttpHandler {
    private static final Logger logger = LoggerFactory.createLoggerWithConfiguration(ShowFormHandler.class);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            exchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE, HttpContentType.HTML.getFormattedWithCharset());
            sendResponse(exchange, HttpCode.SUCCESS, FileUtils.readAllBytesFromPage("form.html"));
        } catch (URISyntaxException e) {
            logger.log(Level.INFO, "Invalid Resource path", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}