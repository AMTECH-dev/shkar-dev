package com.company.handlers;

import com.company.dto.ResponseHeaderDTO;
import com.company.http.HttpContentType;
import com.company.http.HttpHeader;
import com.company.utils.FileUtils;
import com.company.utils.JsonUtils;
import com.company.utils.WebClient;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAllUrls extends OurHttpHandler {
    private static final WebClient WEB_CLIENT = new WebClient();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        List<ResponseHeaderDTO> headerDTOS = new ArrayList<>();
        for (String url : FileUtils.loadAllUrlsFromFile("urls.txt")) {
            Map<String, String> responseHeaders = WEB_CLIENT.getResponseHeaders(url);
            headerDTOS.add(new ResponseHeaderDTO(url, responseHeaders));
        }
        var response = JsonUtils.toJson(headerDTOS).getBytes();
        httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE, HttpContentType.JSON.getFormattedWithCharset());
        sendResponse(httpExchange, HttpURLConnection.HTTP_OK, response);
    }
}
