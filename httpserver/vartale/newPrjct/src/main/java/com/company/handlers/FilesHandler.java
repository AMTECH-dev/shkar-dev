package com.company.handlers;

import com.company.data_processing.Utils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.company.data_processing.ImageProcessing;
import com.company.data_processing.ReadingAndWritingData;
import com.company.enums.HttpContentType;
import com.company.enums.HttpHeader;
import com.company.enums.HttpStatusCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;

public class FilesHandler implements HttpHandler {
    private static final Logger logger = LogManager.getLogger(FilesHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) {
        httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE.getHeaderName(),
                HttpContentType.HTML.getContentType());

        logger.info("FilesHandler in process...");

        String responsePath = httpExchange.getRequestURI().getPath();

        String pathToFile = Utils.toAbsPathToString(responsePath.substring(1));
        String pathToImage = Utils.toAbsPathToString("files/stop.png");

        if (responsePath.equals("/files/cat.jpg")) {
            logger.info("Response equals 'cat.jpg'");

            ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                    ImageProcessing.copyMarkToImage(pathToFile, pathToImage));
        } else
            ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                    ReadingAndWritingData.readBytesFromPath(pathToFile));
    }
}
