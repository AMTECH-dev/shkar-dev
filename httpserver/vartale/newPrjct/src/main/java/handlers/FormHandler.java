package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import data_processing.ReadingAndWritingData;
import enums.HttpContentType;
import enums.HttpHeader;
import enums.HttpStatusCode;
import enums.HttpMethod;

import java.io.*;

public class FormHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) {
        httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE.getHeaderName(),
                HttpContentType.HTML.getContentType());

        String requestType = httpExchange.getRequestMethod();

        if (requestType.equalsIgnoreCase(HttpMethod.POST.getMethod())) {
            try {
                ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                        ReadingAndWritingData.getRequestBody(httpExchange).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (requestType.equalsIgnoreCase(HttpMethod.GET.getMethod()))
            ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                    ReadingAndWritingData.readBytesFromPath("form.html"));
    }
}