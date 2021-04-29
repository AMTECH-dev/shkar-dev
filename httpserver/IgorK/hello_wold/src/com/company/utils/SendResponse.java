package com.company.utils;

import com.company.enums.HttpCode;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class SendResponse {
    public static OutputStream response(HttpExchange t, int numCode, byte[] response) throws IOException {
        t.sendResponseHeaders(HttpCode.CORRECT.getNumber(), response.length);
        OutputStream os = t.getResponseBody();
        os.write(response);
        os.close();
        return os;
    }

}
