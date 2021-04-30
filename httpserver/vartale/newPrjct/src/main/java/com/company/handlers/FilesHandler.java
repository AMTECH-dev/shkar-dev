package com.company.handlers;

import com.company.data_processing.HttpProtocol;
import com.company.file_utils.FileUtils;
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
        HttpProtocol.addResponseHeaders(httpExchange, HttpHeader.CONTENT_TYPE, HttpContentType.HTML);

        LOGGER.info("FilesHandler in process...");

        String responsePath = httpExchange.getRequestURI().getPath();

        String pathToFile = FileUtils.stringToAbsPathToString(responsePath.substring(1));
        String pathToImage = FileUtils.stringToAbsPathToString("files/stop.png");

        if (responsePath.equals("/files/cat.jpg")) {
            LOGGER.info("Response equals 'cat.jpg'");

            HttpProtocol.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                    ImageManipulation.copyMarkToImage(pathToFile, pathToImage));
        } else
            HttpProtocol.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                    ReadingAndWritingData.readBytesFromPath(pathToFile));
    }
}
