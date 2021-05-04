package com.company;

import com.company.handlers.*;
import com.company.http.HttpContentType;
import com.company.http.HttpHeader;
import com.company.http.HttpMethod;
import com.company.utils.FileUtils;
import com.company.utils.JsonUtils;
import com.company.utils.Sex;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new RootHandler());
        server.createContext("/test", new TestHandler());
        server.createContext("/getForm", new ShowFormHandler());
        server.createContext("/postquery", new PostHandler());
        server.createContext("/static/"  , new StaticHandler());
        server.createContext("/api/urls/"  , new GetAllUrls());
        server.createContext("/urls"  , new ShowUrlPageHandler());
        server.createContext("/password", new OurHttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                httpExchange.getResponseHeaders().add(HttpHeader.CONTENT_TYPE, HttpContentType.HTML.getFormattedWithCharset());
                try {
                    sendResponse(httpExchange, HttpURLConnection.HTTP_OK, FileUtils.readAllBytesFromPage("passworddata.html"));
                } catch (URISyntaxException ignored) {
                }
            }
        });
        server.createContext("/createpasswordcard", new PasswordHandler());
        server.start();
    }
}
