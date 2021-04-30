package com.company.handlers;

import com.company.http.HttpContentType;
import com.company.http.HttpHeader;
import com.company.utils.FileUtils;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

public class ShowUrlPageHandler extends OurHttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            byte[] response = FileUtils.readAllBytesFromPage("allurls.html");
            httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE, HttpContentType.HTML.getFormattedWithCharset());
            sendResponse(httpExchange, HttpURLConnection.HTTP_OK, response);
        } catch (URISyntaxException ignored) {
        }
    }
}
