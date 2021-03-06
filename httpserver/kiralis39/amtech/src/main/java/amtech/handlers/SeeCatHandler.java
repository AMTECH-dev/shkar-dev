package amtech.handlers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import amtech.logic.Responser;
import amtech.registry.ContentTypes;
import amtech.registry.QueryTypes;
import config.LogConfigurator;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class SeeCatHandler extends Responser implements HttpHandler {
    private static final Logger LOGGER = LogConfigurator.getLogger();
    private Map<String, String> userMap = new HashMap<>();
    
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
    	StringBuilder sb = new StringBuilder();
    	
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))) {
            LOGGER.info("Read a request from seecat.html...");

        	String data;
            while ((data = br.readLine()) != null) {
                sb.append(data);
            }

            if (fillUserMapSuccefull(sb.toString().split("&"))) {
            	
            	gsonizer(userMap);

                sb.append("<hr><h4><a href=\"catspage.html\">Continue</a>");
                httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.HTML);
                writeResponse(httpExchange, sb.toString().getBytes());
                
            } else {
                httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.HTML);
                writeResponse(httpExchange, HttpURLConnection.HTTP_NOT_ACCEPTABLE);
                return;
            }

        } catch (Exception e) {
            LOGGER.warning("Exception: " + e.getMessage());
        	e.printStackTrace();
            writeResponse(httpExchange, HttpURLConnection.HTTP_BAD_REQUEST);
        }
	}
    
    @Override	
    public void getRequest(HttpExchange httpExchange) {
        try {
            byte[] response = Files.readAllBytes(Path.of(Paths.get("./pages/seecat.html").toUri()));
            httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.HTML);
            writeResponse(httpExchange, response);
        } catch (Exception e) {
            LOGGER.warning("Exception: " + e.getMessage());
            e.printStackTrace();
            writeResponse(httpExchange, HttpURLConnection.HTTP_NOT_FOUND);
        }
    }

    
    private void gsonizer(Object objToGson) {
        String gsonSerial = new Gson().toJson(objToGson);
        System.out.println("Gsonizer: " + gsonSerial);
    }

	private boolean fillUserMapSuccefull(String[] splittedUserData) {
        LOGGER.info("Get user form data: " + Arrays.asList(splittedUserData));

        boolean isDataCorrect = formDataChecker(splittedUserData);
        if (isDataCorrect) {
        	userMap.put("userName", uName);
        	userMap.put("userSex", uSex);
        	
        	return true;
        }
        
        return false;
    }

    private String uName, uSex;
    private boolean formDataChecker(String[] splittedUserData) {
    	try {
    		
    		uName = splittedUserData[0].contains("userName=") ? splittedUserData[0].replace("userName=", "") : "";
	        uSex = splittedUserData[1].contains("sex=") ? splittedUserData[1].replace("sex=", "") : "";
	
	        LOGGER.info("User name: " + uName);
	        LOGGER.info("User sex: " + uSex);
	        
	        if (uName.isBlank() || uSex.isBlank()) {
	        	return false;
	        }
	        
    	} catch (Exception e) {
			return false;
    	}
    	
        return true;
    }
}