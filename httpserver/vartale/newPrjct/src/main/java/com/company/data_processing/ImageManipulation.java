package com.company.data_processing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class ImageManipulation {
    private static final Logger LOGGER = LogManager.getLogger(ImageManipulation.class);

    private ImageManipulation() {
    }

    public static byte[] copyMarkToImage(String pathToImage, String pathToMark) {
        String markFormat = pathToMark.substring(pathToMark.lastIndexOf(".") + 1);

        BufferedImage origImage = null;
        BufferedImage mark;
        try {
            LOGGER.info("Reading images...");
            origImage = ImageIO.read(Paths.get(pathToImage).toFile());
            mark = ImageIO.read(Paths.get(pathToMark).toFile());

            Graphics graphics = origImage.getGraphics();
            graphics.drawImage(mark, 0, 0, origImage.getWidth(), origImage.getHeight(), null);
            graphics.dispose();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            LOGGER.info("Writing image to " + os.getClass());
            if (origImage != null) ImageIO.write(origImage, markFormat, os);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        return os.toByteArray();
    }
}
