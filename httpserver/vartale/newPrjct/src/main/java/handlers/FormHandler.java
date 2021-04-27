package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;

public class FormHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) {
        StringBuilder sb = new StringBuilder();
        String data;
        byte[] response;
        String requestType = httpExchange.getRequestMethod();

        if (requestType.equalsIgnoreCase("post")) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))) {
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }

                response = sb.toString().getBytes();
                ResponseHandling.writeResponse(httpExchange, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestType.equalsIgnoreCase("get")) {
            try {
                ResponseHandling.writeResponse(httpExchange, ResponseHandling.getResponse("form.html"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
