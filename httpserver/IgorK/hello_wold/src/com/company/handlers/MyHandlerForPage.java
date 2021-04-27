package com.company.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;

class MyHandlerForPage implements HttpHandler {
    String form;
    StringBuilder sb = new StringBuilder();

    public void handle(HttpExchange t) throws IOException {
        returnPage();

        byte[] response = form.getBytes(StandardCharsets.UTF_8);
        t.getResponseHeaders().add(HttpHeaders.CONTENT_TYPE.getName(), "text/html; charset=UTF-8");
        t.sendResponseHeaders(HttpCode.CORRECT.getNumber(), response.length);
        OutputStream os = t.getResponseBody();
        os.write(response);
        os.close();
    }

    public void returnPage() {
        BufferedReader io = null;
        try {
            io = new BufferedReader(new FileReader("page.html"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                if (!((form = io.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(form);
        }
        form = sb.toString();
        try {
            io.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
