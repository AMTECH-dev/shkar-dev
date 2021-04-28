package com.company.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        StringBuilder sb = new StringBuilder();

        if (t.getRequestMethod().equalsIgnoreCase("POST")) {
            String data;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(t.getRequestBody()))) {
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }
            }
            byte[] response = String.format("Ваши параметры: %s", sb.toString()).getBytes();
            t.getResponseHeaders().add(HttpHeaders.CONTENT_TYPE.getName(), "text/html; charset=UTF-8");
            t.sendResponseHeaders(HttpCode.CORRECT.getNumber(), response.length);
            WriteToFile(sb.toString());
            OutputStream os = t.getResponseBody();
            os.write(response);
            os.close();
        } else {
            String form;
            BufferedReader io = null;
            try {
                FileReader a = new FileReader("test.html");
                io = new BufferedReader(a);
                while ((form = io.readLine()) != null) {
                    sb.append(form);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }

            form = sb.toString();
            io.close();

            byte[] response = form.getBytes(StandardCharsets.UTF_8);
            t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            t.sendResponseHeaders(200, response.length);
            OutputStream os = t.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    public void WriteToFile(String s) throws IOException {
        FileWriter fw = new FileWriter("UserInfo.txt", false);
        fw.write(s);
        fw.flush();

    }

}


