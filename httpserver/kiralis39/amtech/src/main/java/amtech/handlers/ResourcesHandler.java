package amtech.handlers;

import amtech.processor.GetPostClass;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

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
        if (requestData.endsWith(".png") || requestData.endsWith(".jpg")) {
            File image = new File(requestData);
            response = Files.readAllBytes(image.toPath());

            httpExchange.getResponseHeaders().add("Content-Type", "image/png; charset=UTF-8");
            httpExchange.sendResponseHeaders(200, response.length);
            try (OutputStream os = httpExchange.getResponseBody()) {os.write(response);
            } catch (Exception e) {e.printStackTrace();}
        } else {
            int dataInt;
            try (BufferedReader br = new BufferedReader(new FileReader(new File(requestData)))) {
                while ((dataInt = br.read()) != -1) {sb.append((char)dataInt);}
                response = sb.toString().getBytes();

                httpExchange.sendResponseHeaders(200, response.length);
                try (OutputStream os = httpExchange.getResponseBody()) {os.write(response);
                } catch (Exception e) {e.printStackTrace();}
            } catch (IOException e) {e.printStackTrace();}
        }
    }
}
