package amtech.handlers;

import amtech.processor.GetPostClass;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class RegFormHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) {
        String requestType = httpExchange.getRequestMethod();

        if (requestType.equalsIgnoreCase("post")) {
            GetPostClass.doPOST(httpExchange);
        } else if (requestType.equalsIgnoreCase("get")) {
            GetPostClass.doGET(httpExchange, "./pages/reg.html");
        }
    }
}
