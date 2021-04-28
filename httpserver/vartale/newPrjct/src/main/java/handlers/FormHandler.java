package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import data_processing.Logging;
import data_processing.ReadingAndWritingData;
import enums.HttpContentType;
import enums.HttpHeader;
import enums.HttpStatusCode;
import enums.HttpMethod;

import java.io.*;
import java.util.logging.Logger;

public class FormHandler implements HttpHandler {
    private static final Logger LOGGER = Logging.getLogger(FormHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) {
        httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE.getHeaderName(),
                HttpContentType.HTML.getContentType());

        String requestType = httpExchange.getRequestMethod();

        if (requestType.equalsIgnoreCase(HttpMethod.POST.getMethod())) {
            try {
                LOGGER.info("Data are reading from request...");
                ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                        ReadingAndWritingData.getRequestBody(httpExchange).getBytes());
            } catch (IOException e) {
                LOGGER.warning("Too many bytes are written from request body!\n" + e.getMessage());
            }

        } else if (requestType.equalsIgnoreCase(HttpMethod.GET.getMethod()))
            ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                    ReadingAndWritingData.readBytesFromPath("form.html"));
    }
}