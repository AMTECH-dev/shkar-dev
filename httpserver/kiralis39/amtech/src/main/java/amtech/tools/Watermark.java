package amtech.tools;

import amtech.registry.TemporaryData;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;


public class Watermark {
    private static final Logger LOGGER = LogConfigurator.getLogger(Watermark.class);

    public static byte[] setWaterMark(BufferedImage canvas, BufferedImage waterSign) throws IOException {
        try {
            Graphics2D g2D = (Graphics2D) canvas.getGraphics();
            g2D.drawImage(waterSign, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
            g2D.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(canvas, TemporaryData.PNG, baos);
            return baos.toByteArray();
        } catch (Exception e) {
            LOGGER.warning("Exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
