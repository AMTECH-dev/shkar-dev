package amtech.handlers;


import amtech.logic.Responser;
import amtech.registry.ContentTypes;
import amtech.registry.QueryTypes;
import config.LogConfigurator;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;


public class URLParserHandler extends Responser implements HttpHandler {
    private static final Logger LOGGER = LogConfigurator.getLogger();
	private static final int PARSER_CONNECT_TIMEOUT = 250;
	private static final int PARSER_READING_TIMEOUT = 1000;
	

    @Override
    public void handle(HttpExchange httpExchange) {
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
            Set<String> urlData = new HashSet<>(Files.readAllLines(Paths.get("urls.txt")));
            StringBuilder sb = new StringBuilder();
            HttpURLConnection conn = null;

            for (String url: urlData) {
            	try {
            		conn = (HttpURLConnection) new URL(url).openConnection();
	                conn.setRequestMethod("HEAD");
	                conn.setConnectTimeout(PARSER_CONNECT_TIMEOUT);
	                conn.setReadTimeout(PARSER_READING_TIMEOUT);
	                
	                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
	                    Map<String, List<String>> fieldsMap = conn.getHeaderFields();
	                    for (Map.Entry<String, List<String>> entry : fieldsMap.entrySet()) {
	                        sb.append(Arrays.asList(entry) + "<br>");
	                    }
	                    sb.append("<hr>");
	                }
	
            	} catch (Exception e) {
                    LOGGER.severe("Exception: " + e.getMessage() + (conn != null ? (" >> " + new String(conn.getErrorStream().readAllBytes())) : "."));
                    e.printStackTrace();
                    writeResponse(httpExchange, HttpURLConnection.HTTP_CLIENT_TIMEOUT);
                } finally {
                	if (conn != null) {conn.disconnect();}
                }
            }


            httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.HTML);
            writeResponse(httpExchange, sb.toString().getBytes());
        } catch (Exception e) {
            LOGGER.severe("Exception: " + e.getMessage());
            e.printStackTrace();
            writeResponse(httpExchange, HttpURLConnection.HTTP_BAD_REQUEST);
        }
	}
}