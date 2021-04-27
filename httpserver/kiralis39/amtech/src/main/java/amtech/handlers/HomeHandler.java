package amtech.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;


public class HomeHandler implements HttpHandler {
    private StringBuilder sb;

    @Override
    public void handle(HttpExchange httpExchange) {
        this.sb = new StringBuilder();
        String requestType = httpExchange.getRequestMethod();
        String data;
        byte[] response;

        if (requestType.equalsIgnoreCase("post")) {return;
        } else if (requestType.equalsIgnoreCase("get")) {
            try (BufferedReader br = new BufferedReader(new FileReader("./index.html"))) {
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }

                response = sb.toString().getBytes();
                writeResponse(httpExchange, response);
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    private void writeResponse(HttpExchange httpExchange, byte[] writeData) {
        try {
            httpExchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            httpExchange.sendResponseHeaders(200, writeData.length);

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(writeData);
            } catch (Exception e) {e.printStackTrace();}
        } catch (IOException e) {e.printStackTrace();}
    }
}
