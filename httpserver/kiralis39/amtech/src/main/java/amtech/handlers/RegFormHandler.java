package amtech.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import amtech.door.Main;
import amtech.tools.LogConfigurator;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import amtech.registry.TemporaryData;


public class RegFormHandler implements HttpHandler {
    private static final Logger LOGGER = LogConfigurator.getLogger(RegFormHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestType = httpExchange.getRequestMethod();
        StringBuilder sb = new StringBuilder();

        if (requestType.equalsIgnoreCase(TemporaryData.POST)) {

            try (BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))) {
                LOGGER.info("Read a request from reg.html...");
            	String data;
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }

                sb.append("&<hr><h4><a href=\"page.html\">Continue</a>");

                System.out.println(sb.toString());
                getUserData(sb.toString().split("&"));

                httpExchange.getResponseHeaders().add(TemporaryData.CONTENT_TYPE, TemporaryData.TEXT_HTML);
                writeResponse(httpExchange, sb.toString().getBytes());

            } catch (IOException e) {
                LOGGER.warning("Exception: " + e.getMessage());
            	e.printStackTrace();
            }
            
        } else if (requestType.equalsIgnoreCase(TemporaryData.GET)) {
        	byte[] response = Files.readAllBytes(Path.of(Paths.get("./pages/reg.html").toUri()));
        	httpExchange.getResponseHeaders().add(TemporaryData.CONTENT_TYPE, TemporaryData.TEXT_HTML);
            writeResponse(httpExchange, response);
        }
    }
    
    private void getUserData(String[] splittedUserData) {
        LOGGER.info("Get user form data:");
    	TemporaryData.userData.put("userName", splittedUserData[0].replace("input=", ""));
        TemporaryData.userData.put("userSex", splittedUserData[1].replace("sex=", ""));
        LOGGER.info("User name: " + TemporaryData.userData.get("userName"));
        LOGGER.info("User sex: " + TemporaryData.userData.get("userSex"));

        String gsonSerial = new Gson().toJson(TemporaryData.userData);
        System.out.println(gsonSerial);
    }

	private void writeResponse(HttpExchange httpExchange, byte[] writeData) {
        if (writeData.length <= 0) {LOGGER.severe("RegFormHandler(): writeData is NULL or empty");}

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