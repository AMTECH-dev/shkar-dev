package amtech.handlers;

import amtech.tools.LogConfigurator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import amtech.registry.TemporaryData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;


public class HomePageHandler implements HttpHandler {
    private static final Logger LOGGER = LogConfigurator.getLogger(HomePageHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestType = httpExchange.getRequestMethod();

        if (requestType.equalsIgnoreCase(TemporaryData.POST)) {
            LOGGER.info("page.html not work for 'POST' now, than returned nothing...");
            return;
        } else if (requestType.equalsIgnoreCase(TemporaryData.GET)) {
        	byte[] response = Files.readAllBytes(Path.of(Paths.get("./pages/page.html").toUri()));
            httpExchange.getResponseHeaders().add(TemporaryData.CONTENT_TYPE, TemporaryData.TEXT_HTML);
            writeResponse(httpExchange, response);
        }
    }
    
    private static void writeResponse(HttpExchange httpExchange, byte[] writeData) {
        if (writeData.length <= 0) {LOGGER.severe("Exception: HomePageHandler(): writeData is NULL or empty");}

        try {
            httpExchange.sendResponseHeaders(TemporaryData.OK, writeData.length);

            LOGGER.info("Writing a response to page.html...");
            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(writeData);
            } catch (Exception e) {
                LOGGER.warning("Exception: " + e.getMessage());
            	e.printStackTrace();
            }
        } catch (IOException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        	e.printStackTrace();
        }
    }
}
