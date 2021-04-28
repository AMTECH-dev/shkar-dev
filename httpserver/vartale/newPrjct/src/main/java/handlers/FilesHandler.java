package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import data_processing.ImageProcessing;
import data_processing.ReadingAndWritingData;
import enums.HttpContentType;
import enums.HttpHeader;
import enums.HttpStatusCode;

import java.nio.file.Paths;

public class FilesHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) {
        httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE.getHeaderName(),
                HttpContentType.HTML.getContentType());

        String responsePath = httpExchange.getRequestURI().getPath();

        String pathToFile = Paths.get(responsePath.substring(1)).toAbsolutePath().toString();
        String pathToImage = Paths.get("files/stop.png").toAbsolutePath().toString();

        if (responsePath.equals("/files/cat.jpg"))
            ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                    ImageProcessing.copyMarkToImage(pathToFile, pathToImage));
        else
            ReadingAndWritingData.writeResponse(httpExchange, HttpStatusCode.SUCCESS,
                    ReadingAndWritingData.readBytesFromPath(pathToFile));
    }
}
