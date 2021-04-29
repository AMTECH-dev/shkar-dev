package com.company.data_processing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class ImageProcessing {
    private static final Logger logger = LogManager.getLogger(ImageProcessing.class);

    private ImageProcessing() {
    }

    public static byte[] copyMarkToImage(String pathToImage, String pathToMark) {
        String markFormat = pathToMark.substring(pathToMark.lastIndexOf(".") + 1);

        BufferedImage origImage = null;
        BufferedImage mark;
        try {
            logger.info("Reading images...");
            origImage = ImageIO.read(Paths.get(pathToImage).toFile());
            mark = ImageIO.read(Paths.get(pathToMark).toFile());

            Graphics graphics = origImage.getGraphics();
            graphics.drawImage(mark, 0, 0, origImage.getWidth(), origImage.getHeight(), null);
            graphics.dispose();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            logger.info("Writing image to " + os.getClass());
            if (origImage != null) ImageIO.write(origImage, markFormat, os);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return os.toByteArray();
    }
}
