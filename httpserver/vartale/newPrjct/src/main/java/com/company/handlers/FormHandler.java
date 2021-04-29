package com.company.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.company.data_processing.Converting;
import com.company.data_processing.ReadingAndWritingData;
import com.company.enums.HttpContentType;
import com.company.enums.HttpHeader;
import com.company.enums.HttpStatusCode;
import com.company.enums.HttpMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Paths;
import java.util.Map;

public class FormHandler implements HttpHandler {
    private static final Logger logger = LogManager.getLogger(FormHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) {
        httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE.getHeaderName(),
                HttpContentType.HTML.getContentType());

        String requestType = httpExchange.getRequestMethod();

        if (requestType.equalsIgnoreCase(HttpMethod.POST.getMethod())) {
            final String name = "name";
            final String gender = "gender";

            try {
                String requestBody = ReadingAndWritingData.getRequestBody(httpExchange);
                ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                        requestBody.getBytes());

                Map<String, String> options = ReadingAndWritingData.getRequestOptions(requestBody);
                if (!options.containsKey(name) || !options.containsKey(gender)) {
                    logger.error("Options are empty!\t" + options);
                }
                String jsonWithOptions = Converting.convertMapToJSON(options);
                ReadingAndWritingData.writeToFile("optionsJSON.txt", jsonWithOptions);

                System.out.println(jsonWithOptions);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }

        } else if (requestType.equalsIgnoreCase(HttpMethod.GET.getMethod()))
            ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                    ReadingAndWritingData.readBytesFromPath("form.html"));
    }
}