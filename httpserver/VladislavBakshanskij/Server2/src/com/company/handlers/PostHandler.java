package com.company.handlers;

import com.company.error.InvalidRequestException;
import com.company.factories.LoggerFactory;
import com.company.http.HttpCode;
import com.company.http.HttpContentType;
import com.company.http.HttpHeader;
import com.company.http.HttpMethod;
import com.company.utils.FileUtils;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostHandler extends OurHttpHandler {
    private static final Logger logger = LoggerFactory.createLoggerWithConfiguration(PostHandler.class);

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
                FileUtils.writeToFile("output.txt", params.toString());
                String response = String.format("User name: %s \n Sex: %s", params.get(userName),
                        Sex.getById(Integer.parseInt(params.get(sex))).getDesc());

                exchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE, HttpContentType.HTML.getFormattedWithCharset());
                sendResponse(exchange, HttpCode.SUCCESS, response);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        } catch (InvalidRequestException e) {
            logger.log(Level.INFO,  e.getMessage());
            sendResponse(exchange, HttpCode.BAD_REQUEST, "Username or Sex is not set.");
        }
    }

    private enum Sex {
        UNKNOWN(-1, "ТЫ КТО??"),
        MALE(1, "Мужчина"),
        FEMALE(2, "Женщина"),
        ;
        private final int id;
        private final String desc;


        Sex(int id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public String getDesc() {
            return desc;
        }

        public static Sex getById(int id) {
            for (Sex value : values()) {
                if (value.getId() == id) {
                    return value;
                }
            }

            return UNKNOWN;
        }
    }
}