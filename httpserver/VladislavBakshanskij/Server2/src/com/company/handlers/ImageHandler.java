package com.company.handlers;

import com.company.Main;
import com.company.http.HttpCode;
import com.company.utils.FileUtils;
import com.sun.net.httpserver.HttpExchange;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class ImageHandler extends StaticHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        BufferedImage catImage = readImage("cat.png");

        Graphics graphics = catImage.getGraphics();
        graphics.drawImage(catImage, 0, 0, null);
        graphics.drawImage(readImage("triangle.png"), 0, catImage.getHeight() / 2, null);
        graphics.dispose();

        try {
            File outputFile = new File(Main.class.getResource("static/img/catWithTriangle.png").toURI());
            ImageIO.write(catImage, "png", outputFile);

            byte[] response = FileUtils.readAllBytesFromStaticFile("img/catWithTriangle.png");
            sendResponse(httpExchange, HttpCode.SUCCESS, response);
        } catch (URISyntaxException ignored) {
        }
    }

    private BufferedImage readImage(String imageName) throws IOException {
        return ImageIO.read(Main.class.getResource("static/img/" + imageName));
    }
}
