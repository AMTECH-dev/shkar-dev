package com.company.handlers;

import com.company.data_processing.HttpProtocol;
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
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class FormHandler implements HttpHandler {
    private static final Logger LOGGER = LogManager.getLogger(FormHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) {
        HttpProtocol.addResponseHeaders(httpExchange, HttpHeader.CONTENT_TYPE, HttpContentType.HTML);

        String requestType = httpExchange.getRequestMethod();
        if (requestType.equalsIgnoreCase(HttpMethod.POST.getMethod())) {

            try {
                String requestBody = HttpProtocol.getRequestBody(httpExchange);

                Map<String, String> options = ReadingAndWritingData.getRequestOptionsMap(requestBody);

                String jsonWithUserData = URLDecoder.decode(Converting.convertMapToJSON(options),
                        StandardCharsets.UTF_8);
                ReadingAndWritingData.writeToFile("optionsJSON.txt", jsonWithUserData);

                String formattedUserData = formatJsonToTextTable(jsonWithUserData);

                HttpProtocol.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                        formattedUserData.getBytes());

            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }

        } else if (requestType.equalsIgnoreCase(HttpMethod.GET.getMethod()))
            HttpProtocol.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                    ReadingAndWritingData.readBytesFromPath("form.html"));
    }

    private static String formatJsonToTextTable(String json) {
        return json.substring(1, json.length() - 1)
                .replaceAll(",", "<br>")
                .replaceAll("\"", " ");
    }
}
