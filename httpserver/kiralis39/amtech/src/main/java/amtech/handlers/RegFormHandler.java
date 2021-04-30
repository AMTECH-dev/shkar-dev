package amtech.handlers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import amtech.logic.Responser;
import amtech.registry.AnswerMessages;
import amtech.registry.ContentTypes;
import amtech.registry.QueryTypes;
import amtech.registry.ReturnCodes;
import amtech.tools.LogConfigurator;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class RegFormHandler extends Responser implements HttpHandler {
    private static final Logger LOGGER = LogConfigurator.getLogger(RegFormHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) {
        String requestType = httpExchange.getRequestMethod();
        StringBuilder sb = new StringBuilder();

        if (requestType.equalsIgnoreCase(QueryTypes.POST)) {

            try (BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))) {
                LOGGER.info("Read a request from reg.html...");

            	String data;
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }
                sb.append("&<hr><h4><a href=\"page.html\">Continue</a>");

                Map<String, String> userMap = getUserDataMap(sb.toString().split("&"));
                if (userMap == null) {
                    httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.TEXT_HTML);
                    writeResponse(httpExchange, HttpURLConnection.HTTP_NOT_ACCEPTABLE);
                    return;
                }

                LOGGER.info("User name: " + userMap.get("userName"));
                LOGGER.info("User sex: " + userMap.get("userSex"));

                gsonizer(userMap);

                httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.TEXT_HTML);
                writeResponse(httpExchange, sb.toString().getBytes());

            } catch (Exception e) {
                LOGGER.warning("RegForm exception: " + e.getMessage());
            	e.printStackTrace();
                writeResponse(httpExchange, HttpURLConnection.HTTP_BAD_REQUEST);
            }
            
        } else if (requestType.equalsIgnoreCase(QueryTypes.GET)) {

            try {
                byte[] response = Files.readAllBytes(Path.of(Paths.get("./pages/reg.html").toUri()));
                httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.TEXT_HTML);
                writeResponse(httpExchange, response);
            } catch (Exception e) {
                LOGGER.warning("RegForm exception: " + e.getMessage());
                e.printStackTrace();
                writeResponse(httpExchange, HttpURLConnection.HTTP_NOT_FOUND);
            }

        }
    }

    private void gsonizer(Object objToGson) {
        String gsonSerial = new Gson().toJson(objToGson);
        System.out.println(gsonSerial);
    }

    private Map<String, String> getUserDataMap(String[] splittedUserData) {
        LOGGER.info("Get user form data:");

        String uName = splittedUserData[0].contains("userName=") ? splittedUserData[0].replace("userName=", "") : "";
        String uSex = splittedUserData[1].contains("sex=") ? splittedUserData[1].replace("sex=", "") : "";
        if (uName.isBlank() || uSex.isBlank()) {
            return null;
        }

        return new HashMap<>() {
            {
                put("userName", uName);
                put("userSex", uSex);
            }
        };
    }
}