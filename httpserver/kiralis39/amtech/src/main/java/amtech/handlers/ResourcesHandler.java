package amtech.handlers;

import amtech.processor.GetPostClass;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ResourcesHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        StringBuilder sb = new StringBuilder();
        String requestData = "./" + httpExchange.getRequestURI().getPath();

        System.out.println("RD: " + requestData);

        byte[] response;
        if (requestData.endsWith(".png")) {
            if (requestData.endsWith("cat.png")) {
                response = getCatWaterMark(ImageIO.read(new File("./files/restrict.png")));
            } else {
                File image = new File(requestData);
                response = Files.readAllBytes(image.toPath());
            }

            httpExchange.getResponseHeaders().add("Content-Type", "image/png; charset=UTF-8");
            httpExchange.sendResponseHeaders(200, response.length);
            try (OutputStream os = httpExchange.getResponseBody()) {os.write(response);
            } catch (Exception e) {e.printStackTrace();}
        } else {
            int dataInt;
            if (!new File(requestData).isDirectory()) {
                try (BufferedReader br = new BufferedReader(new FileReader(new File(requestData)))) {
                    while ((dataInt = br.read()) != -1) {
                        sb.append((char) dataInt);
                    }
                    response = sb.toString().getBytes();

                    writeAnswer(200, response, httpExchange);
                } catch (IOException e) {
//                    e.printStackTrace();
                    writeAnswer(300, new byte[] {0}, httpExchange);
                }
            } else {writeAnswer(404, new byte[] {0}, httpExchange);}
        }
    }

    private void writeAnswer(int code, byte[] response, HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(code, response.length);
        try (OutputStream os = httpExchange.getResponseBody()) {os.write(response);
        } catch (Exception e) {e.printStackTrace();}
    }

    private byte[] getCatWaterMark(BufferedImage upperCatImage) throws IOException {
        BufferedImage originCat = ImageIO.read(new File("./files/cat.png"));

        Graphics2D g2D = (Graphics2D) originCat.getGraphics();
        g2D.drawImage(upperCatImage, 0, 0, originCat.getWidth(), originCat.getHeight(), null);
        g2D.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(originCat, "png", baos);

        return baos.toByteArray();
    }
}
