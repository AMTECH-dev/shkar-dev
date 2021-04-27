package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Main {

  public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    server.createContext("/test", new MyHandler());
    server.createContext("/page", new MyHandlerForPage());
    server.setExecutor(null); // creates a default executor
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
        String form;

        BufferedReader io = null;
        try {
          FileReader a =new FileReader("test.html");
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
}