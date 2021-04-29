package com.company.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyHandlerDoublePictures implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        BufferedImage bo = null,square ;
        square = ImageIO.read(new File("files/1.jpeg"));
    }
}
