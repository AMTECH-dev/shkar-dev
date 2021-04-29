package com.company.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public final class ImageUtils {
    private ImageUtils() {
    }

    public static byte[] createWaterMark(String targetImageFileName, String waterMarkFileName, String outputFileName) throws IOException, URISyntaxException {
        BufferedImage catImage = FileUtils.getImage(targetImageFileName);

        Graphics graphics = catImage.getGraphics();
        graphics.drawImage(catImage, 0, 0, null);
        graphics.drawImage(FileUtils.getImage(waterMarkFileName), 0, catImage.getHeight() / 2, null);
        graphics.dispose();

        File outputFile = new File(FileUtils.getImageURI(outputFileName));
        ImageIO.write(catImage, "png", outputFile);

        return FileUtils.readAllBytesFromStaticFile("img/" + outputFileName);
    }
}
