package com.company.handlers;

import com.company.enums.HttpCode;
import com.company.factories.LoggerFactory;
import com.company.utils.SendResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class PictureOnPicture implements HttpHandler {
    private static final Logger logger = LoggerFactory.createLogger();

    @Override
    public void handle(HttpExchange exchange) {
        logger.info("Start handle method, class PictureOnPicture ");
        try {
        BufferedImage bi = ImageIO.read(new File("./files/cat.jpg"));
        BufferedImage bi2 = ImageIO.read(new File("./files/square.jpeg"));

        Graphics graphics = bi.getGraphics();
        graphics.drawImage(bi2, 0, 0, bi2.getWidth(), bi2.getHeight(), null);
        graphics.dispose();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", byteArrayOutputStream);

            byte[] response = byteArrayOutputStream.toByteArray();
            exchange.getResponseHeaders().add("Content-Type", "image/jpg; charset=UTF-8");
            SendResponse.response(exchange, HttpCode.CORRECT.getNumber(), response);
        } catch (IOException e) {
            e.getMessage();
        }


    }
}
