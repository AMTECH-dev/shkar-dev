package com.company.handlers;

import com.company.factories.LoggerFactory;
import com.company.enums.HttpCode;
import com.company.enums.HttpHeaders;
import com.company.utils.WriteToFile;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class MyHandler implements HttpHandler {
    private static final Logger logger = LoggerFactory.createLogger();

    @Override
    public void handle(HttpExchange t) throws IOException {
        logger.info("run method handle / MyHandle.class");
        StringBuilder sb = new StringBuilder();
        if (t.getRequestMethod().equalsIgnoreCase("POST")) {
            logger.info(" POST method");
            String data;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(t.getRequestBody()))) {
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }
            }
            byte[] response = String.format(sb.toString()).getBytes();
            t.getResponseHeaders().add(HttpHeaders.CONTENT_TYPE.getName(), "text/html; charset=UTF-8");
            t.sendResponseHeaders(HttpCode.CORRECT.getNumber(), response.length);
            OutputStream os = t.getResponseBody();
            os.write(response);
            os.close();
            WriteToFile.writeToFile(sb.toString());
        } else {
            logger.info(" GET method");
            String form;
            try {
                logger.info("read to 'text.html' file");
                FileReader a = new FileReader("test.html");
                BufferedReader io = new BufferedReader(a);
                while ((form = io.readLine()) != null) {
                    sb.append(form);
                }
                io.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            form = sb.toString();
            byte[] response = form.getBytes(StandardCharsets.UTF_8);
            t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            t.sendResponseHeaders(200, response.length);
            OutputStream os = t.getResponseBody();
            os.write(response);
            os.close();
        }
        SerialToJSON.toJSON(sb.toString());
    }



    /*public void writeToFile(String s) throws IOException {
        FileWriter fw = new FileWriter("UserInfo.txt", false);
        fw.write(s);
        fw.flush();
    }*/
}


