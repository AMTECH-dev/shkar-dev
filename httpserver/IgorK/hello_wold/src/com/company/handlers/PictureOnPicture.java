package com.company.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

public class PictureOnPicture implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) {
        try {
            BufferedImage bi = ImageIO.read(new File("./src/files/cat.jpg"));
            BufferedImage bi2 = ImageIO.read(new File("./src/files/square.jpeg"));

            Graphics graphics = bi.getGraphics();
            graphics.drawImage(bi2, 0, 0, bi2.getWidth(), bi2.getHeight(), null);
            graphics.dispose();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", byteArrayOutputStream);

            byte[] response = byteArrayOutputStream.toByteArray();
            exchange.getResponseHeaders().add("Content-Type", "image/jpg; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
