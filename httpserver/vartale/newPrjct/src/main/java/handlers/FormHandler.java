package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import data_processing.Converting;
import data_processing.Logging;
import data_processing.ReadingAndWritingData;
import enums.HttpContentType;
import enums.HttpHeader;
import enums.HttpStatusCode;
import enums.HttpMethod;

import java.io.*;
import java.util.Map;
import java.util.logging.Logger;

public class FormHandler implements HttpHandler {
    private static final Logger logger = Logging.createLoggerWithSetting(Logging.DEFAULT_CONFIG);

    @Override
    public void handle(HttpExchange httpExchange) {
        httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE.getHeaderName(),
                HttpContentType.HTML.getContentType());

        String requestType = httpExchange.getRequestMethod();

        if (requestType.equalsIgnoreCase(HttpMethod.POST.getMethod())) {
            final String name = "name";
            final String gender = "gender";

            try {
                String requestBody = ReadingAndWritingData.getRequestBody(httpExchange);
                ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                        requestBody.getBytes());

                Map<String, String> options = ReadingAndWritingData.getRequestOptions(requestBody);
                if (!options.containsKey(name) || !options.containsKey(gender)) {
                    logger.warning("Options are empty!\t" + options);
                }
                String jsonWithOptions = Converting.convertMapToJSON(options);
                System.out.println(jsonWithOptions);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (requestType.equalsIgnoreCase(HttpMethod.GET.getMethod()))
            ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                    ReadingAndWritingData.readBytesFromPath("form.html"));
    }
}