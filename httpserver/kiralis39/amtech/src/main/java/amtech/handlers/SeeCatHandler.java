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
import amtech.registry.ContentTypes;
import amtech.registry.QueryTypes;
import amtech.tools.LogConfigurator;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class SeeCatHandler extends Responser implements HttpHandler {
    private static final Logger LOGGER = LogConfigurator.getLogger(SeeCatHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) {
        String requestType = httpExchange.getRequestMethod();
        StringBuilder sb = new StringBuilder();

        if (requestType.equalsIgnoreCase(QueryTypes.POST)) {

            try (BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))) {
                LOGGER.info("Read a request from seecat.html...");

            	String data;
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }

                Map<String, String> userMap = getUserDataMap(sb.toString().split("&"));
                if (userMap == null) {
                    httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.TEXT_HTML);
                    writeResponse(httpExchange, HttpURLConnection.HTTP_NOT_ACCEPTABLE);
                    return;
                }

                gsonizer(userMap);

                sb.append("<hr><h4><a href=\"page.html\">Continue</a>");
                httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.TEXT_HTML);
                writeResponse(httpExchange, sb.toString().getBytes());

            } catch (Exception e) {
                LOGGER.warning("RegForm exception: " + e.getMessage());
            	e.printStackTrace();
                writeResponse(httpExchange, HttpURLConnection.HTTP_BAD_REQUEST);
            }
            
        } else if (requestType.equalsIgnoreCase(QueryTypes.GET)) {
            giveThisPage(httpExchange);
        }
    }

    private void giveThisPage(HttpExchange httpExchange) {
        try {
            byte[] response = Files.readAllBytes(Path.of(Paths.get("./pages/seecat.html").toUri()));
            httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.TEXT_HTML);
            writeResponse(httpExchange, response);
        } catch (Exception e) {
            LOGGER.warning("RegForm exception: " + e.getMessage());
            e.printStackTrace();
            writeResponse(httpExchange, HttpURLConnection.HTTP_NOT_FOUND);
        }
    }

    private void gsonizer(Object objToGson) {
        String gsonSerial = new Gson().toJson(objToGson);
        System.out.println(gsonSerial);
    }

    private Map<String, String> getUserDataMap(String[] splittedUserData) {
        LOGGER.info("Get user form data:");

        boolean isDataCorrect = formDataChecker(splittedUserData);

        return isDataCorrect ? new HashMap<>() {
            {
                put("userName", uName);
                put("userSex", uSex);

            }
        } : null;
    }

    private String uName, uSex;
    private boolean formDataChecker(String[] splittedUserData) {
        uName = splittedUserData[0].contains("userName=") ? splittedUserData[0].replace("userName=", "") : "";
        uSex = splittedUserData[1].contains("sex=") ? splittedUserData[1].replace("sex=", "") : "";

        LOGGER.info("User name: " + uName);
        LOGGER.info("User sex: " + uSex);

        if (uName.isBlank() || uSex.isBlank()) {
            return false;
        }

        return true;
    }
}