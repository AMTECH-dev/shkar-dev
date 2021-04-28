package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import data_processing.ReadingAndWritingData;
import enums.HttpContentType;
import enums.HttpHeader;
import enums.HttpStatusCode;

public class PageHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) {
        httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE.getHeaderName(),
                HttpContentType.HTML.getContentType());

        ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                ReadingAndWritingData.readBytesFromPath("page.html"));
    }
}
