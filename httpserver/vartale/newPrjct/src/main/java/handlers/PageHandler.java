package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class PageHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) {
        try {
            ResponseHandling.writeResponse(httpExchange, ResponseHandling.getResponse("page.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
