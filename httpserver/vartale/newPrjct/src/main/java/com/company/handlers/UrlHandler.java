package com.company.handlers;

import com.company.data_processing.ParsingUrls;
import com.company.data_processing.ReadingAndWritingData;
import com.company.enums.HttpContentType;
import com.company.enums.HttpHeader;
import com.company.enums.HttpStatusCode;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;

public class UrlHandler implements HttpHandler {
    private static final Logger logger = LogManager.getLogger(UrlHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) {
        final String pathToFileWithUrls = "files/urls.txt";
        ParsingUrls site = new ParsingUrls();

        Set<String> urls = ParsingUrls.writeUrlsFromFileToSet(pathToFileWithUrls);

        Map<String, String> headerFields = new HashMap<>();
        Map<String, Map<String, String>> headers = new LinkedHashMap<>();

        for (String url : urls) {
            try {
                headerFields = site.requestHttpHeadersFromUrl(url);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            headers.put(url, headerFields);
        }

        String jsonWithHeaders = new Gson().toJson(headers);

        httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE.getHeaderName(),
                HttpContentType.HTML.getContentType());

        ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                jsonWithHeaders.getBytes());
    }
}
