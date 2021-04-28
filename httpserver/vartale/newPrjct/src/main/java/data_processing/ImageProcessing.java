package data_processing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class ImageProcessing {

    private ImageProcessing() {
    }

    public static byte[] copyMarkToImage(String pathToImage, String pathToMark) {
        String markFormat = pathToImage.substring(pathToImage.lastIndexOf(".") + 1);

        BufferedImage origImage = null;
        BufferedImage mark;
        try {
            origImage = ImageIO.read(Paths.get(pathToImage).toFile());
            mark = ImageIO.read(Paths.get(pathToMark).toFile());

            Graphics graphics = origImage.getGraphics();
            graphics.drawImage(mark, 0, 0, origImage.getWidth(), origImage.getHeight(), null);
            graphics.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            if (origImage != null) ImageIO.write(origImage, markFormat, os);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return os.toByteArray();
    }
}
