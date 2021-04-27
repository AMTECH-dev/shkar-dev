package amtech.handlers;

import amtech.processor.GetPostClass;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;


public class ResourcesHandler implements HttpHandler {
    private StringBuilder sb;

    @Override
    public void handle(HttpExchange httpExchange) {
        this.sb = new StringBuilder();

        String requestData = httpExchange.getRequestURI().toString();
        System.out.println("RD: " + requestData);
        File newResource = new File(requestData);

        byte[] response;
        try (BufferedReader br = new BufferedReader(new FileReader("./" + newResource))) {
            int data;
            while ((data = br.read()) != -1) {
                sb.append(data);
            }

            response = sb.toString().getBytes();
            writeResources(httpExchange, response);
        } catch (IOException e) {e.printStackTrace();}
    }

    public static void writeResources(HttpExchange httpExchange, byte[] writeData) {
        try {
//            httpExchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            httpExchange.sendResponseHeaders(200, writeData.length);

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(writeData);
            } catch (Exception e) {e.printStackTrace();}
        } catch (IOException e) {e.printStackTrace();}
    }
}
