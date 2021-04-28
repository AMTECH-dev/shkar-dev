package com.company.handlers;

import com.company.http.HttpCode;
import com.company.http.HttpHeader;
import com.company.http.HttpMethod;
import com.sun.net.httpserver.HttpExchange;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class PostHandler extends OurHttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase(HttpMethod.POST)) {
            String responseMessage;

            Map<String, String> params = getQueryParams(getRequestBody(exchange));
            responseMessage = String.format("User name: %s \n Sex: %s", params.get("userName"),
                    Sex.getById(Integer.parseInt(params.get("sex"))).getDesc());

            try (FileOutputStream fileOutputStream = new FileOutputStream("output.txt")) {
                fileOutputStream.write(params.toString().getBytes());
            }

            byte[] response = responseMessage.getBytes();
            exchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE, "text/html; charset=utf-8");
            exchange.sendResponseHeaders(HttpCode.SUCCESS, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
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