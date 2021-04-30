package amtech.handlers;

import amtech.logic.Responser;
import amtech.registry.AnswerMessages;
import amtech.registry.ContentTypes;
import amtech.registry.QueryTypes;
import amtech.tools.LogConfigurator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Logger;


public class MenuPageHandler extends Responser implements HttpHandler {
    private static final Logger LOGGER = LogConfigurator.getLogger(SeeCatHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) {
        String requestType = httpExchange.getRequestMethod();

        if (requestType.equalsIgnoreCase(QueryTypes.POST)) {
            LOGGER.info("This page not work for 'POST' now, than returned nothing...");
            return;
        }  else if (requestType.equalsIgnoreCase(QueryTypes.GET)) {
            giveThisPage(httpExchange);
        }
    }

    private void giveThisPage(HttpExchange httpExchange) {
        try {
            byte[] response = Files.readAllBytes(Path.of(Paths.get("./pages/exit.html").toUri()));
            httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.TEXT_HTML);
            writeResponse(httpExchange, response);
        } catch (Exception e) {
            LOGGER.warning("Exception: " + e.getMessage());
            e.printStackTrace();
            writeResponse(httpExchange, HttpURLConnection.HTTP_NOT_FOUND);
        }
    }
}