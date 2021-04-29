package com.company.handlers;

import com.company.http.HttpCode;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class StaticHandler implements OurHttpHandler{

    @Override
    public void handle(HttpExchange httpExchange) {//throws IOException {
        
        String fileName = httpExchange.getRequestURI().getPath();
        //System.out.println("GET FileName:" + fileName);
        try {
            byte[] response = readAllBytesFromPage(fileName);
            //System.out.println("Size:" + response.length);
            //httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE, "text/html; charset=utf-8");
            httpExchange.sendResponseHeaders(HttpCode.SUCCESS, response.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(response);
            os.close();

        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
