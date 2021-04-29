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
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class URLParserHandler extends Responser implements HttpHandler {
    private static final Logger LOGGER = LogConfigurator.getLogger(RegFormHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) {
        String requestType = httpExchange.getRequestMethod();

        try {
            ArrayList<String> urlData = new ArrayList<String>(Files.readAllLines(Paths.get("urls.txt")));
            StringBuilder sb = new StringBuilder();
            String urlAnswer;

            for (String url: urlData) {
                Map<String, List<String>> tmp = new URL(url).openConnection().getHeaderFields();
                sb.append("<p>");
                for (Map.Entry<String, List<String>> entry : tmp.entrySet()) {
                    sb.append(Arrays.asList(entry) + "<br>");
                }
                sb.append("</p>");
            }

            httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.TEXT_HTML);
            writeResponse(httpExchange, sb.toString().getBytes());
        } catch (Exception e) {
            LOGGER.severe("Exception: " + e.getMessage());
            e.printStackTrace();
            writeResponse(httpExchange, ReturnCodes.BAD_REQUEST);
        }

    }
}