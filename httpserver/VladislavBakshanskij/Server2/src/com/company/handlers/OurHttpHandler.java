package com.company.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class OurHttpHandler implements HttpHandler {
    protected void sendResponse(HttpExchange exchange, int responseCode, String response) throws IOException {
        sendResponse(exchange, responseCode, response.getBytes());
    }

    protected void sendResponse(HttpExchange exchange, int codeResponse, byte[] response) throws IOException {
        try (OutputStream responseBody = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(codeResponse, response.length);
            responseBody.write(response);
        }
    }

    protected String getRequestBody(HttpExchange exchange) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
            StringBuilder sb = new StringBuilder();
            String bodyRequestPart;
            while ((bodyRequestPart = br.readLine()) != null) {
                sb.append(bodyRequestPart);
            }
            return sb.toString();
        }
    }

    protected Map<String, String> getQueryParams(String body) {
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
