package com.company.handlers;

import com.company.factories.LoggerFactory;
import com.company.http.HttpContentType;
import com.company.http.HttpHeader;
import com.company.utils.FileUtils;
import com.company.utils.ImageUtils;
import com.sun.net.httpserver.HttpExchange;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaticHandler extends OurHttpHandler {
    private static final Logger logger = LoggerFactory.createLogger(StaticHandler.class);
    private static final String PATH_TO_HANDLER = "/static/";

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            String path = httpExchange.getRequestURI().getPath().replace(PATH_TO_HANDLER, "");
            byte[] response;
            if (path.endsWith("cat.png")) response = ImageUtils.createWaterMark("cat.png", "triangle.png", "catWithTriangle.png");
            else response = FileUtils.readAllBytesFromStaticFile(path);
            httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE, HttpContentType.getByFileName(path).getFormattedWithCharset());
            sendResponse(httpExchange, HttpURLConnection.HTTP_OK, response);
        } catch (FileNotFoundException e) {
            sendResponse(httpExchange, HttpURLConnection.HTTP_NOT_FOUND, "Resource not found".getBytes());
            logger.log(Level.INFO, "Invalid Resource path", e);
        } catch (URISyntaxException e) {
            logger.log(Level.INFO, "Invalid Resource path", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
