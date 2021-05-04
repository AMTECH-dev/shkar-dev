package amtech.tools;


import javax.imageio.ImageIO;

import config.LogConfigurator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;


public class Watermark {
    private static final Logger LOGGER = LogConfigurator.getLogger();

    public static byte[] setWaterMark(BufferedImage canvas, BufferedImage waterSign) throws IOException {
    	LOGGER.fine("Preparing watermarked image to view...");
    	
        try {
        	
            Graphics2D g2D = (Graphics2D) canvas.getGraphics();
            
            g2D.drawImage(
            		waterSign, 
            		3, 3, 
            		canvas.getWidth() - 6, canvas.getHeight() - 6, 
            		null);
            
            g2D.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(canvas, "png", baos);
            return baos.toByteArray();
            
        } catch (Exception e) {
            LOGGER.warning("Exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}