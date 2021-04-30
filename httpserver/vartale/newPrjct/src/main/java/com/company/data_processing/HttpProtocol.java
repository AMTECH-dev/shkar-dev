package com.company.data_processing;

import com.company.enums.HttpContentType;
import com.company.enums.HttpHeader;
import com.company.enums.HttpStatusCode;
import com.sun.net.httpserver.HttpExchange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class HttpProtocol {
    private static final Logger LOGGER = LogManager.getLogger(HttpProtocol.class);

    private HttpProtocol() {
    }

    public static void writeResponse(HttpExchange httpExchange, HttpStatusCode responseCode, byte[] data) {
        try {
            httpExchange.sendResponseHeaders(responseCode.getStatusCode(), data.length);
            LOGGER.info("Writing response... Data length = " + data.length);

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(data);
                LOGGER.info("Writing data to OutputStream...");
            } catch (IOException e) {
                LOGGER.error("I/O error occurs!" + e.getMessage());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void addResponseHeaders(HttpExchange httpExchange,
                                          HttpHeader headerName, HttpContentType contentType) {
        httpExchange.getResponseHeaders().add(headerName.getHeaderName(), contentType.getContentType());
    }

    public static String getRequestBody(HttpExchange exchange) throws IOException {
        StringBuilder sb = new StringBuilder();
        String data;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
            LOGGER.info("Reading data...");
            while ((data = br.readLine()) != null) {
                sb.append(data);
            }
            return sb.toString();
        }
    }
}
