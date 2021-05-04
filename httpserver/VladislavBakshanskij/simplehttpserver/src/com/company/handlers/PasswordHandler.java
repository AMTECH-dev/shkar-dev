package com.company.handlers;

import com.company.factories.LoggerFactory;
import com.company.http.HttpContentType;
import com.company.http.HttpHeader;
import com.company.http.HttpMethod;
import com.company.utils.JsonUtils;
import com.company.utils.Sex;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class PasswordHandler extends OurHttpHandler {
    private static final Logger logger = LoggerFactory.createLogger(PasswordHandler.class);
    private static final Pattern PASSPORT_PATTERN = Pattern.compile("^[0-9]{4}\\s{1}[0-9]{6}$");

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            if (httpExchange.getRequestMethod().equalsIgnoreCase(HttpMethod.POST)) {
                Map<String, String> requestBody = getRequestBodyFromExchange(httpExchange);
                String passport = requestBody.get("passport");

                if (!requestBody.containsKey("sex")) {
                    httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE, HttpContentType.HTML.getFormattedWithCharset());
                    sendResponse(httpExchange, HttpURLConnection.HTTP_BAD_REQUEST, "<h1>ПОЛ НЕ ВЫБРАН<h1/>");
                } else if (!PASSPORT_PATTERN.matcher(passport).find()) {
                    httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE, HttpContentType.HTML.getFormattedWithCharset());
                    sendResponse(httpExchange, HttpURLConnection.HTTP_BAD_REQUEST, "<h1>ВВЕДЕНЫ НЕВЕРЕНЫЕ ДАННЫЕ ПАСПОРТА<h1/>");
                } else {
                    String sex = Sex.getById(Integer.parseInt(requestBody.get("sex"))).getDesc();
                    requestBody.put("sex", sex);
                    httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE, HttpContentType.JSON.getFormattedWithCharset());
                    sendResponse(httpExchange, HttpURLConnection.HTTP_OK, JsonUtils.toJson(requestBody));
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "ERROR", e);
        }
    }
}
