package amtech.handlers;

import amtech.logic.Responser;
import amtech.registry.AnswerMessages;
import amtech.registry.ContentTypes;
import amtech.registry.QueryTypes;
import amtech.tools.LogConfigurator;
import amtech.tools.PassportChecker;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class PassportHandler extends Responser implements HttpHandler {
    private static final Logger LOGGER = LogConfigurator.getLogger(PassportHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestType = httpExchange.getRequestMethod();
        StringBuilder sb = new StringBuilder();


        if (requestType.equalsIgnoreCase(QueryTypes.POST)) {

            try (BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))) {
                LOGGER.info("Read a request from passportForm.html...");

                String data;
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }

                Map<String, String> userMap = getFormDataMap(sb.toString());
                if (userMap == null) {
                    httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.TEXT_HTML);
                    writeResponse(httpExchange, AnswerMessages.notEnougtDataMessage.getBytes());
                    return;
                }

                httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.TEXT_HTML);
                writeResponse(httpExchange, (
                        "Уважаемый, " + userMap.get("uName") +
                        "!<br>Ваша форма была принята! Благодарим за доверие." +
                        "<hr><h4><a href=\"/exit.html\">To exit page</a>"
                ).getBytes());

            } catch (Exception e) {
                LOGGER.warning("RegForm exception: " + e.getMessage());
                e.printStackTrace();
                writeResponse(httpExchange, HttpURLConnection.HTTP_BAD_REQUEST);
            }

        }  else if (requestType.equalsIgnoreCase(QueryTypes.GET)) {
            giveThisPage(httpExchange);
        }
    }

    private void giveThisPage(HttpExchange httpExchange) {
        try {
            byte[] response = Files.readAllBytes(Path.of(Paths.get("./pages/passportForm.html").toUri()));
            httpExchange.getResponseHeaders().add(ContentTypes.CONTENT_TYPE, ContentTypes.TEXT_HTML);
            writeResponse(httpExchange, response);
        } catch (Exception e) {
            LOGGER.warning("Exception: " + e.getMessage());
            e.printStackTrace();
            writeResponse(httpExchange, HttpURLConnection.HTTP_NOT_FOUND);
        }
    }

    private Map<String, String> getFormDataMap(String passportFormData) {
        LOGGER.info("Get userform data-map:");
        return PassportChecker.check(passportFormData);
    }
}
