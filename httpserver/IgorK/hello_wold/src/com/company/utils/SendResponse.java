package com.company.utils;

import com.company.enums.HttpCode;
import com.company.factories.LoggerFactory;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

public class SendResponse {
    private static final Logger logger = LoggerFactory.createLogger();

    public static OutputStream response(HttpExchange t, int numCode, byte[] response) {
        logger.info("Start response method, class SendResponse ");
        OutputStream os=null;
        try {
            t.sendResponseHeaders(HttpCode.CORRECT.getNumber(), response.length);
             os = t.getResponseBody();
            os.write(response);
            os.close();
        } catch (IOException e) {
            logger.severe("Exceptions: " + SendResponse.class.getName());
            e.printStackTrace();
        }
        return os;
    }

}
