package amtech.handlers;


import amtech.logic.Responser;
import amtech.registry.ContentTypes;
import amtech.registry.QueryTypes;
import amtech.registry.ReturnCodes;
import amtech.tools.LogConfigurator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;


public class URLParserHandler extends Responser implements HttpHandler {
    private static final Logger LOGGER = LogConfigurator.getLogger(RegFormHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) {
        String requestType = httpExchange.getRequestMethod();

        try {
            Set<String> urlData = new HashSet<>(Files.readAllLines(Paths.get("urls.txt")));
            StringBuilder sb = new StringBuilder();
            HttpURLConnection conn;

            for (String url: urlData) {
                conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("HEAD");

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Map<String, List<String>> fieldsMap = conn.getHeaderFields();
                    for (Map.Entry<String, List<String>> entry : fieldsMap.entrySet()) {
                        sb.append(Arrays.asList(entry) + "<br>");
                    }
                    sb.append("<hr>");
                }

                if (conn != null) {conn.disconnect();}
            }


            httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.TEXT_HTML);
            writeResponse(httpExchange, sb.toString().getBytes());
        } catch (Exception e) {
            LOGGER.severe("Exception: " + e.getMessage());
            e.printStackTrace();
            writeResponse(httpExchange, HttpURLConnection.HTTP_BAD_REQUEST);
        }

    }
}