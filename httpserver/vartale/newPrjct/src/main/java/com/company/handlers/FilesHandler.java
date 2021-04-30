package com.company.handlers;

import com.company.data_processing.Converting;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.company.data_processing.ImageManipulation;
import com.company.data_processing.ReadingAndWritingData;
import com.company.enums.HttpContentType;
import com.company.enums.HttpHeader;
import com.company.enums.HttpStatusCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FilesHandler implements HttpHandler {
    private static final Logger LOGGER = LogManager.getLogger(FilesHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) {
        httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE.getHeaderName(),
                HttpContentType.HTML.getContentType());

        LOGGER.info("FilesHandler in process...");

        String responsePath = httpExchange.getRequestURI().getPath();

        String pathToFile = Converting.stringToAbsPathToString(responsePath.substring(1));
        String pathToImage = Converting.stringToAbsPathToString("files/stop.png");

        if (responsePath.equals("/files/cat.jpg")) {
            LOGGER.info("Response equals 'cat.jpg'");

            ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                    ImageManipulation.copyMarkToImage(pathToFile, pathToImage));
        } else
            ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                    ReadingAndWritingData.readBytesFromPath(pathToFile));
    }
}
