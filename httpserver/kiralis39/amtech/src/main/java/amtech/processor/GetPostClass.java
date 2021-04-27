package amtech.processor;

import amtech.data.ClientData;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;


public class GetPostClass {
    private static StringBuilder sb;

    public static void doGET(HttpExchange httpExchange, String address) {
        sb = new StringBuilder();

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

    public static void doPOST(HttpExchange httpExchange) {
        String data;
        byte[] response;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))) {
            while ((data = br.readLine()) != null) {
                sb.append(data);
            }

            sb.append("<hr><br><h5><a href=\"page.html\">Continue</a>");

            response = sb.toString().getBytes();
            writeResponse(httpExchange, response);

            ClientData.clientName = sb.toString().split("&")[0].replace("input=", "");
            ClientData.clientSex = sb.toString().split("&")[1].replace("sex=", "");
        } catch (IOException e) {e.printStackTrace();}
    }

    private static void writeResponse(HttpExchange httpExchange, byte[] writeData) {
        try {
            httpExchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            httpExchange.sendResponseHeaders(200, writeData.length);

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(writeData);
            } catch (Exception e) {e.printStackTrace();}
        } catch (IOException e) {e.printStackTrace();}
    }
}
