package amtech.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import amtech.registry.TemporaryData;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;


public class ResourcesHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        StringBuilder sb = new StringBuilder();
        String requestData = "./" + httpExchange.getRequestURI().getPath();
        
        System.out.println("RD: " + requestData); // временная отладка. смотрим, что запрашивается.

        byte[] response;
        if (requestData.endsWith("." + TemporaryData.PNG)) {
        	
            if (requestData.endsWith("cat.png")) {
                response = getCatWaterMark();
            } else {
                File image = new File(requestData);
                response = Files.readAllBytes(image.toPath());
            }

            httpExchange.getResponseHeaders().add(TemporaryData.CONTENT_TYPE, TemporaryData.IMAGE_PNG);
            httpExchange.sendResponseHeaders(TemporaryData.OK, response.length);
            
            try (OutputStream os = httpExchange.getResponseBody()) {
            	os.write(response);
            } catch (Exception e) {
            	e.printStackTrace();
            }
            
        } else {
        	
            int dataInt;
            if (!new File(requestData).isDirectory()) {
                try (BufferedReader br = new BufferedReader(new FileReader(new File(requestData)))) {
                    while ((dataInt = br.read()) != -1) {
                        sb.append((char) dataInt);
                    }
                    response = sb.toString().getBytes();

                    writeAnswer(TemporaryData.OK, response, httpExchange);
                } catch (IOException e) {
                    e.printStackTrace();
                    writeAnswer(300, new byte[] {0}, httpExchange); // "всё не ок". случайный код для отладки. (временно)
                }
            } else {
            	writeAnswer(404, new byte[] {0}, httpExchange); // "всё не ок". случайный код для отладки. (временно)
            }
        }
    }

    private static void writeAnswer(int code, byte[] response, HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(code, response.length);
        try (OutputStream os = httpExchange.getResponseBody()) {os.write(response);
        } catch (Exception e) {e.printStackTrace();}
    }

    private static byte[] getCatWaterMark() throws IOException {
    	BufferedImage originCat = ImageIO.read(new File("./files/cat.png"));
    	BufferedImage waterSign = ImageIO.read(new File("./files/restrict.png"));

        Graphics2D g2D = (Graphics2D) originCat.getGraphics();
        g2D.drawImage(waterSign, 0, 0, originCat.getWidth(), originCat.getHeight(), null);
        g2D.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(originCat, TemporaryData.PNG, baos);

        return baos.toByteArray();
    }
}
