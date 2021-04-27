package amtech.handlers;

import amtech.processor.GetPostClass;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;


public class HomePageHandler implements HttpHandler {
    private StringBuilder sb;

    @Override
    public void handle(HttpExchange httpExchange) {
        this.sb = new StringBuilder();
        String requestType = httpExchange.getRequestMethod();
        String data;
        byte[] response;

        if (requestType.equalsIgnoreCase("post")) {return;
        } else if (requestType.equalsIgnoreCase("get")) {
            try (BufferedReader br = new BufferedReader(new FileReader("./pages/page.html"))) {
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }

                response = sb.toString().getBytes();
                GetPostClass.writeResponse(httpExchange, response);
            } catch (IOException e) {e.printStackTrace();}
        }
    }
}
