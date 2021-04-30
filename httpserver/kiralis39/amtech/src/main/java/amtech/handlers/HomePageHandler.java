package amtech.handlers;

import amtech.logic.Responser;
import amtech.registry.ContentTypes;
import amtech.registry.QueryTypes;
import amtech.tools.LogConfigurator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;


public class HomePageHandler extends Responser implements HttpHandler {
    private static final Logger LOGGER = LogConfigurator.getLogger(HomePageHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestType = httpExchange.getRequestMethod();

        if (requestType.equalsIgnoreCase(QueryTypes.POST)) {
            LOGGER.info("This page not work for 'POST' now, than returned nothing...");
            return;
        } else if (requestType.equalsIgnoreCase(QueryTypes.GET)) {
        	byte[] response = Files.readAllBytes(Path.of(Paths.get("./pages/page.html").toUri()));
            httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.TEXT_HTML);
            writeResponse(httpExchange, response);
        }
    }
}
