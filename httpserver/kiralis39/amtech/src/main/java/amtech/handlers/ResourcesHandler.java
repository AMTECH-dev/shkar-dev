package amtech.handlers;

import amtech.tools.LogConfigurator;
import amtech.tools.Watermark;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import amtech.registry.TemporaryData;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.logging.Logger;


public class ResourcesHandler implements HttpHandler {
    private static final Logger LOGGER = LogConfigurator.getLogger(ResourcesHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) {
        String requestData = "./" + httpExchange.getRequestURI().getPath();
        LOGGER.info("Clients reqested resource: " + requestData);

        try {
            byte[] response;
            if (requestData.endsWith("." + TemporaryData.PNG)) {
                LOGGER.info("Image processing: requstData is '" + requestData + "'.");

                if (requestData.endsWith("cat.png")) {
                    BufferedImage originCat = ImageIO.read(new File("./files/cat.png"));
                    BufferedImage waterSign = ImageIO.read(new File("./files/restrict.png"));
                    response = Watermark.setWaterMark(originCat, waterSign);
                } else {
                    File image = new File(requestData);
                    response = Files.readAllBytes(image.toPath());
                }

                httpExchange.getResponseHeaders().add(TemporaryData.CONTENT_TYPE, TemporaryData.IMAGE_PNG);
                writeAnswer(TemporaryData.OK, response, httpExchange);

            } else {
                StringBuilder sb = new StringBuilder();

                int dataInt;
                if (!new File(requestData).isDirectory()) {
                    try (BufferedReader br = new BufferedReader(new FileReader(new File(requestData)))) {
                        while ((dataInt = br.read()) != -1) {
                            sb.append((char) dataInt);
                        }

                        response = sb.toString().getBytes();
                        writeAnswer(TemporaryData.OK, response, httpExchange);
                    } catch (IOException e) {
                        LOGGER.warning("Exception: " + e.getMessage());
                        e.printStackTrace();
                        writeAnswer(TemporaryData.ERR_300, new byte[]{0}, httpExchange); // "всё не ок". случайный код для отладки. (временно)
                    }
                } else {
                    LOGGER.warning("Requared resource is a directory!");
                    writeAnswer(TemporaryData.ERR_404, new byte[]{0}, httpExchange); // "всё не ок". случайный код для отладки. (временно)
                }
            }
        } catch (Exception e) {
            LOGGER.warning("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void writeAnswer(int code, byte[] response, HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(code, response.length);

        try (OutputStream os = httpExchange.getResponseBody()) {
            LOGGER.info("Writing a response to page.html...");
            os.write(response);
        } catch (Exception e) {
            LOGGER.warning("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
