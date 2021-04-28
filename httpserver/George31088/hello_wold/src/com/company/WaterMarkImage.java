package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class WaterMarkImage implements HttpHandler {
    private static Logger log = Testing.createLogger("logWaterMarkImage.log");

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        BufferedImage img = null, temp;

        try {
            img = ImageIO.read(new File("files/Cat.jpg"));
            temp = ImageIO.read(new File("files/Fig_3.png"));

            Graphics graphics = img.getGraphics();
            graphics.drawImage(temp, 0, 0, img.getWidth(), img.getWidth(), null);

            graphics.dispose();

        } catch (Exception e) {
            e.printStackTrace();
            log.log(Level.SEVERE, "Test OK");
        }

        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        ImageIO.write(img, "png", ba);


        byte[] response = ba.toByteArray();
        exchange.getResponseHeaders().add("Content-Type", "image/png; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.length);

        OutputStream os = exchange.getResponseBody();
        os.write(response);
        os.close();
    }
}

