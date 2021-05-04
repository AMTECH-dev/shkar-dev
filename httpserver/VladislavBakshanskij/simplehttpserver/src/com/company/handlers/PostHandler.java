package com.company.handlers;

import com.company.error.InvalidRequestException;
import com.company.factories.LoggerFactory;
import com.company.http.HttpContentType;
import com.company.http.HttpHeader;
import com.company.http.HttpMethod;
import com.company.utils.FileUtils;
import com.company.utils.JsonUtils;
import com.company.utils.Sex;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostHandler extends OurHttpHandler {
    private static final Logger logger = LoggerFactory.createLogger(PostHandler.class);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().equalsIgnoreCase(HttpMethod.POST)) {
                Map<String, String> params = getQueryParams(getRequestBody(exchange));
                String userName = "userName";
                String sex = "sex";
                if (!params.containsKey(userName) || !params.containsKey(sex)) {
                    throw new InvalidRequestException("username or sex not set\nresponse body: " + params);
                }
                FileUtils.writeToFile("output.json", JsonUtils.toJson(params));
                String response = String.format("User name: %s \n Sex: %s", params.get(userName),
                        Sex.getById(Integer.parseInt(params.get(sex))).getDesc());

                exchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE, HttpContentType.HTML.getFormattedWithCharset());
                sendResponse(exchange, HttpURLConnection.HTTP_OK, response);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        } catch (InvalidRequestException e) {
            logger.log(Level.INFO,  e.getMessage());
            sendResponse(exchange, HttpURLConnection.HTTP_BAD_REQUEST, "Username or Sex is not set.");
        }
    }
}