import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessImage {

    private static Logger log = Logger.getLogger(ProcessImage.class.getName());

    private static BufferedImage logo;

    static {
        File imageLogoFile = new File("static/LOGO.jpg");
        try {
            logo = ImageIO.read(imageLogoFile);
        } catch (IOException e) {
            e.printStackTrace();
            log.log(Level.SEVERE,"Not found LOGO.jpg",e);
        }
        log.fine("Loaded static/LOGO.jpg");
    }

    //https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html

    public static byte[] process(String nameFile,String ext) {
        File imageFile = new File(nameFile);
        try {
            if(log.isLoggable(Level.FINE)) {
                log.fine("Processing image:"+nameFile);
            }

            BufferedImage bi = ImageIO.read(imageFile);

            int height=bi.getHeight();
            int width=bi.getWidth();

            if(log.isLoggable(Level.INFO)) {
                log.info("Height:"+height+" Width:"+width);
            }

            Graphics2D gr=bi.createGraphics();
            gr.setColor(Color.RED);
            gr.setStroke(new BasicStroke(10));
            gr.drawLine(0,0,width,height);
            gr.drawLine(width,0,0,height);

            gr.setColor(Color.BLUE);
            gr.setFont(new Font("TimesRoman",Font.BOLD,height/6));
            gr.drawString("BAD",width/3,height/2+height/6/2);

            //https://stackoverflow.com/questions/4216123/how-to-scale-a-bufferedimage
            System.out.println("LOGO:"+logo.getHeight()+" BI:"+bi.getHeight());
            double scaleLogo=(double)bi.getHeight()/logo.getHeight()*0.1;//Scale LOGO to image 1:1
            AffineTransform scaleInstance=AffineTransform.getScaleInstance(scaleLogo,scaleLogo);
            //AffineTransform rotateInstance=AffineTransform.getRotateInstance(Math.PI/5);
            AffineTransformOp trans=new AffineTransformOp(scaleInstance,AffineTransformOp.TYPE_BICUBIC);
            //AffineTransformOp trans=new AffineTransformOp(rotateInstance,AffineTransformOp.TYPE_BICUBIC);
            gr.drawImage(logo, trans,9 * width / 10, 9 * height / 10);

            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            ImageIO.write(bi,ext,baos);
            byte[] img=baos.toByteArray();

            //ImageIO.write(bi,"",new File("static/output.jpeg"));
            return img;

        } catch (IOException e) {
            e.printStackTrace();
            log.log(Level.SEVERE,"Problem process image",e);
        }
        return null;
    }

}
