package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.createContext("/page", new MyHandler2());
        server.createContext("/files", new MyHandler3());
        server.start();
    }

    static class MyHandler implements HttpHandler {

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
                t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
                t.sendResponseHeaders(200, response.length);
                WriteToFile(sb.toString());
                OutputStream os = t.getResponseBody();
                os.write(response);
                os.close();
            } else {
                String line;
                BufferedReader io = new BufferedReader(new FileReader("test.html"));
                while ((line = io.readLine()) != null) {
                    sb.append(line);
                }

                String result = sb.toString();
                io.close();

                byte[] response = result.getBytes(StandardCharsets.UTF_8);
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

    static class MyHandler2 implements HttpHandler {

        public void handle(HttpExchange t) throws IOException {
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader io = new BufferedReader(new FileReader("page.html"));
            while ((line = io.readLine()) != null) {
                sb.append(line);
            }

            String result = sb.toString();
            io.close();

            byte[] response = result.getBytes(StandardCharsets.UTF_8);
            t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            t.sendResponseHeaders(200, response.length);
            OutputStream os = t.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    static class MyHandler3 implements HttpHandler {

        public void handle(HttpExchange t) {
            StringBuilder sb = new StringBuilder();
            String line;
            String fileName = t.getRequestURI().getPath();
            Path fullFilePath = Paths.get(fileName.substring(1)).toAbsolutePath();
            try {
                BufferedReader io = new BufferedReader(new FileReader(fullFilePath.toString()));
                while ((line = io.readLine()) != null) {
                    sb.append(line);
                }

                String result = sb.toString();
                io.close();

                byte[] response = result.getBytes( /*StandardCharsets.UTF_8*/);
                // t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
                t.sendResponseHeaders(200, response.length);
                OutputStream os = t.getResponseBody();
                os.write(response);
                os.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}