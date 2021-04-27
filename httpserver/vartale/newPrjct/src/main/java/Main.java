import java.io.*;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import handlers.*;

public class Main {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);
            server.createContext("/test", new FormHandler());
            server.createContext("/page", new PageHandler());
            server.createContext("/files", new FilesHandler());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
