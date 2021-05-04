package amtech.handlers;

import amtech.logic.Responser;
import amtech.registry.ContentTypes;
import amtech.registry.QueryTypes;
import config.LogConfigurator;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;


public class CatsPageHandler extends Responser implements HttpHandler {
    private static final Logger LOGGER = LogConfigurator.getLogger();
    
    
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestType = httpExchange.getRequestMethod();

        if (requestType.equalsIgnoreCase(QueryTypes.POST)) {
        	postRequest(httpExchange);
        } else if (requestType.equalsIgnoreCase(QueryTypes.GET)) {
        	getRequest(httpExchange);
        }
    }


	@Override
	public void postRequest(HttpExchange httpExchange) {
		LOGGER.info("This page not work for 'POST' now, than returned nothing...");
        return;
	}

	@Override
	public void getRequest(HttpExchange httpExchange) {
		try {
			byte[] response = Files.readAllBytes(Path.of(Paths.get("./pages/catspage.html").toUri()));
			
			httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.HTML);
			writeResponse(httpExchange, response);
		} catch (IOException e) {
			LOGGER.warning("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}



}