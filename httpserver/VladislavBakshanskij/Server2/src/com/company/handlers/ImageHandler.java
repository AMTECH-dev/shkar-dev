package com.company.handlers;

import com.company.Main;
import com.company.http.HttpCode;
import com.company.utils.FileUtils;
import com.sun.net.httpserver.HttpExchange;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class ImageHandler extends StaticHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        ImageIcon icon = new ImageIcon(Main.class.getResource("static/img/cat.png"));
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(),
                icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(icon.getImage(), 0, 0, null);

        graphics.drawImage(ImageIO.read(Main.class.getResource("static/img/triangle.png")), 0, icon.getIconHeight() / 2, null);
        graphics.dispose();

        try {
            File outputFile = new File(Main.class.getResource("static/img/catWithTriangle.png").toURI());
            ImageIO.write(bufferedImage, "png", outputFile);

            byte[] response = FileUtils.readAllBytesFromStaticFile("img/output.png");
            sendResponse(httpExchange, HttpCode.SUCCESS, response);
        } catch (URISyntaxException ignored) {
        }
    }
}
