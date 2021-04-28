package data_processing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class ImageProcessing {
    private static final Logger LOGGER = Logging.getLogger(ImageProcessing.class);

    private ImageProcessing() {
    }

    public static byte[] copyMarkToImage(String pathToImage, String pathToMark) {
        String markFormat = pathToImage.substring(pathToImage.lastIndexOf(".") + 1);

        BufferedImage origImage = null;
        BufferedImage mark;
        try {
            LOGGER.info("Images are reading...");
            origImage = ImageIO.read(Paths.get(pathToImage).toFile());
            mark = ImageIO.read(Paths.get(pathToMark).toFile());

            Graphics graphics = origImage.getGraphics();
            graphics.drawImage(mark, 0, 0, origImage.getWidth(), origImage.getHeight(), null);
            graphics.dispose();
        } catch (IOException e) {
            LOGGER.warning("Image not found!\n" + e.getMessage());
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            LOGGER.info("Image with mark is writing to " + os.getClass());
            if (origImage != null) ImageIO.write(origImage, markFormat, os);
        } catch (IOException e) {
            LOGGER.warning("Too many bytes are written!\n" + e.getMessage());
        }

        return os.toByteArray();
    }
}
