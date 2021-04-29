package com.company.handlers;

import com.company.enums.HttpCode;
import com.company.enums.HttpHeaders;
import com.company.utils.SendResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MyHandlerForPage implements HttpHandler {
    //private static final Logger logger = LoggerFactory.createLoggerWithSetting(LoggerFactory.DEFAULT_CONFIG);
    String form;
    StringBuilder sb = new StringBuilder();

    public void handle(HttpExchange t) throws IOException {
        returnPage();
        byte[] response = form.getBytes(StandardCharsets.UTF_8);
        t.getResponseHeaders().add(HttpHeaders.CONTENT_TYPE.getName(), "text/html; charset=UTF-8");
        SendResponse.response(t, HttpCode.CORRECT.getNumber(), response);
    }

    public void returnPage() throws IOException {
        BufferedReader io = new BufferedReader(new FileReader("page.html"));
        while ((form = io.readLine()) != null) {
            sb.append(form);
        }
        form = sb.toString();
        io.close();

    }
}
