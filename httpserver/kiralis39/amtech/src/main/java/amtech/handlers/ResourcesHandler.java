package amtech.handlers;

import amtech.logic.Responser;
import amtech.registry.ContentTypes;
import amtech.registry.MediaTypes;
import amtech.registry.QueryTypes;
import amtech.registry.ReturnCodes;
import amtech.tools.Watermark;
import config.ConfigKeys;
import config.Configurations;
import config.LogConfigurator;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;


public class ResourcesHandler extends Responser implements HttpHandler {
	private static final Logger LOGGER = LogConfigurator.getLogger();
    private static final Map<URI, byte[]> cash = new LinkedHashMap<URI, byte[]>(Integer.parseInt(Configurations.globalConfig.getProperty(ConfigKeys.GLOBAL.PREFERRED_CASH_VALUE.name())), .75f, true);
    
    
    @Override
    public void handle(HttpExchange httpExchange) {
        LOGGER.fine("Clients reqested resource: " + httpExchange.getRequestURI().getPath());

        if (cash.containsKey(httpExchange.getRequestURI())) {
        	LOGGER.info(httpExchange.getRequestURI().getPath() + " exist into cash already, than get It from...");
        	writeResponse(httpExchange, cash.get(httpExchange.getRequestURI()));
        	return;
        }
        
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
        	String[] sm = httpExchange.getRequestURI().getPath().split("\\.");
        	if (sm.length > 1 && MediaTypes.mediaTypesArray.contains(sm[sm.length - 1])) {
        		writeMediaResponse(httpExchange);
            } else {
                writeDataResponse(httpExchange);
            }

            if (isCashHasTooMuchMemory()) {
            	cashTrim();
            }
            
        } catch (Exception e) {
        	e.printStackTrace();
            LOGGER.warning("Something wrong?.. Exc.: " + e.getMessage());
            writeResponse(httpExchange, ReturnCodes.UNDEFINED_PROBLEM);
        }
	}
    

	private void writeDataResponse(HttpExchange httpExchange) {
        String requestData = "./" + httpExchange.getRequestURI().getPath();
        StringBuilder sb = new StringBuilder();

        int dataInt;
        byte[] response;
        if (!new File(requestData).isDirectory() && Files.exists(Paths.get(requestData))) {
            try (BufferedReader br = new BufferedReader(new FileReader(new File(requestData)))) {
                while ((dataInt = br.read()) != -1) {
                    sb.append((char) dataInt);
                }

                response = sb.toString().getBytes();
                cashAdd(httpExchange.getRequestURI(), response);
                writeResponse(httpExchange, response);
            } catch (IOException e) {
                LOGGER.warning("Exception: " + e.getMessage());
                e.printStackTrace();
                writeResponse(httpExchange, HttpURLConnection.HTTP_NOT_FOUND);
            }
        } else {
            LOGGER.warning("Requared resource '" + requestData + "' is a directory or absent?");
            writeResponse(httpExchange, HttpURLConnection.HTTP_NO_CONTENT);
        }
    }

    private void writeMediaResponse(HttpExchange httpExchange) {
        String requestData = "./" + httpExchange.getRequestURI().getPath();
        LOGGER.info("Media requested: '" + requestData + "'.");

        byte[] response;

        try {
            if (requestData.matches(".??//?(cat\\.png)")) {

                BufferedImage originCat = ImageIO.read(new File("./files/cat.png"));
                BufferedImage waterSign = ImageIO.read(new File("./files/restrict.png"));
                response = Watermark.setWaterMark(originCat, waterSign);

            } else {

                File image = new File(requestData);
                response = Files.readAllBytes(image.toPath());

            }

            httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.PNG);
            cashAdd(httpExchange.getRequestURI(), response);
            writeResponse(httpExchange, response);
        } catch (IOException e) {
            LOGGER.warning("May be a requared resource is a directory or absent? Exc.: " + e.getMessage());
            e.printStackTrace();
            writeResponse(httpExchange, HttpURLConnection.HTTP_NO_CONTENT);
        }
    }



	private boolean isCashHasTooMuchMemory() {
		long memory = 0L;
		
		for (byte[] byteArr : cash.values()) {
			memory += byteArr.length;
		}
		
		LOGGER.fine("Cash has now " + memory + " byte. Can used: " + ((long) (Runtime.getRuntime().maxMemory() * 0.2D)) + "byte.");
		return memory > (long) (Runtime.getRuntime().maxMemory() * 0.2D);
	}
    
	private void cashAdd(URI requestURI, byte[] response) {
    	cash.put(requestURI, response);
	}
	
    private void cashTrim() {
    	LOGGER.info("Cash has now " + cash.size() + " elements: " + Arrays.asList(cash.keySet()));
    	LOGGER.info("Max elements is " + Configurations.globalConfig.getProperty(ConfigKeys.GLOBAL.PREFERRED_CASH_VALUE.name()));
		
		if (cash.size() > Integer.parseInt(Configurations.globalConfig.getProperty(ConfigKeys.GLOBAL.PREFERRED_CASH_VALUE.name()))) {
			cash.remove(cash.entrySet().toArray()[cash.size() - 1]);
		}
	}
}