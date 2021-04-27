package amtech.handlers;

import amtech.door.ClientData;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;


public class RegFormHandler implements HttpHandler {
    private StringBuilder sb;

    @Override
    public void handle(HttpExchange httpExchange) {
        this.sb = new StringBuilder();
        String requestType = httpExchange.getRequestMethod();

        if (requestType.equalsIgnoreCase("post")) {doPOST(httpExchange);
        } else if (requestType.equalsIgnoreCase("get")) {doGET(httpExchange, "./RegHtml.html");}
    }

    private void doGET(HttpExchange httpExchange, String address) {
        String data;
        byte[] response;
        try (BufferedReader br = new BufferedReader(new FileReader(address))) {
            while ((data = br.readLine()) != null) {
                sb.append(data);
            }

            response = sb.toString().getBytes();
            writeResponse(httpExchange, response);
        } catch (IOException e) {e.printStackTrace();}
    }

    private void doPOST(HttpExchange httpExchange) {
        String data;
        byte[] response;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))) {
            while ((data = br.readLine()) != null) {
                sb.append(data);
            }

            sb.append("<hr><br><a href=\"page.html\">Continue</a>");

            response = sb.toString().getBytes();
            writeResponse(httpExchange, response);

            ClientData.clientName = sb.toString().split("&")[0].replace("input=", "");
            ClientData.clientSex = sb.toString().split("&")[1].replace("sex=", "");
        } catch (IOException e) {e.printStackTrace();}
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
