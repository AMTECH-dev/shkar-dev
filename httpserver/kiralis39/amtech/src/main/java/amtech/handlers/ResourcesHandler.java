package amtech.handlers;

import amtech.logic.Responser;
import amtech.registry.ConfigKeys;
import amtech.registry.Configurations;
import amtech.registry.ContentTypes;
import amtech.registry.MediaTypes;
import amtech.registry.ReturnCodes;
import amtech.tools.LogConfigurator;
import amtech.tools.Watermark;
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
	private static final Logger LOGGER = LogConfigurator.getLogger(ResourcesHandler.class);
    private static final Map<URI, byte[]> cash = new LinkedHashMap<URI, byte[]>(Integer.parseInt(Configurations.globalConfig.getProperty(ConfigKeys.GLOBAL.MIN_CASH_VALUE.name())), .75f, true);
    
    
    @Override
    public void handle(HttpExchange httpExchange) {
        LOGGER.fine(LogConfigurator.ANSI_PURPLE + "Clients reqested resource: " + httpExchange.getRequestURI().getPath() + LogConfigurator.ANSI_RESET);

        if (cash.containsKey(httpExchange.getRequestURI())) {
        	writeResponse(httpExchange, cash.get(httpExchange.getRequestURI()));
        	return;
        }
        
        try {

            if (httpExchange.getRequestURI().getPath().endsWith("." + MediaTypes.PNG)
                    || httpExchange.getRequestURI().getPath().endsWith("." + MediaTypes.JPG)
                    || httpExchange.getRequestURI().getPath().endsWith("." + MediaTypes.JPEG)) {
                writeImageResponse(httpExchange);
            } else {
                writeDataResponse(httpExchange);
            }

            debugCashMonitor();
            
        } catch (Exception e) {
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
                cash.put(httpExchange.getRequestURI(), response);
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

    private void writeImageResponse(HttpExchange httpExchange) {
        String requestData = "./" + httpExchange.getRequestURI().getPath();
        LOGGER.info("Image requested: '" + requestData + "'.");

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
            
            cash.put(httpExchange.getRequestURI(), response);
            writeResponse(httpExchange, response);
        } catch (IOException e) {
            LOGGER.warning("May be a requared resource is a directory or absent? Exc.: " + e.getMessage());
            e.printStackTrace();
            writeResponse(httpExchange, HttpURLConnection.HTTP_NO_CONTENT);
        }
    }

    private void debugCashMonitor() {
		System.out.println("Cash has now " + cash.size() + " elements:"); 
		System.out.println(Arrays.asList(cash.keySet()));
		System.out.println("Max elements is " + Configurations.globalConfig.getProperty(ConfigKeys.GLOBAL.MIN_CASH_VALUE.name()));
		
		if (cash.size() > Integer.parseInt(Configurations.globalConfig.getProperty(ConfigKeys.GLOBAL.MIN_CASH_VALUE.name()))) {
			cash.remove(cash.entrySet().toArray()[cash.size() - 1]);
		}
	}
}