import java.io.*;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpServer;
import data_processing.Logging;
import handlers.*;

public class Main {
    private static final Logger LOGGER = Logging.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            LOGGER.info("Server is creating...");
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/form", new FormHandler());
            server.createContext("/page", new PageHandler());
            server.createContext("/files", new FilesHandler());
            server.start();
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
