package com.company.handlers;

import com.company.data_processing.HttpProtocol;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.company.data_processing.ReadingAndWritingData;
import com.company.enums.HttpContentType;
import com.company.enums.HttpHeader;
import com.company.enums.HttpStatusCode;

public class PageHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) {
        HttpProtocol.addResponseHeaders(httpExchange, HttpHeader.CONTENT_TYPE, HttpContentType.HTML);

        HttpProtocol.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                ReadingAndWritingData.readBytesFromPath("page.html"));
    }
}
