import java.net.*;

import com.sun.net.httpserver.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Main{
    private static Logger log=LoggerFactory.getLogger(Main.class);

    public static void main(String[] argvs) {//throws IOException {
        System.err.println("Server RUN!");
        try{
            if(log.isLoggable(Level.FINE))
                log.fine("Application was RUN");

            InetSocketAddress inet=new InetSocketAddress(8080);
            HttpServer httpServer=HttpServer.create();//inet,0);
            httpServer.bind(inet,0);
            //httpServer.createContext("/test",new MyHandler());//DELETE
            httpServer.createContext("/register",new RegisterHandler());
            httpServer.createContext("/urls",new UrlsHandler());
            httpServer.createContext("/",new FileHttpHandler());
            httpServer.setExecutor(null);
            httpServer.start();
        } catch(IOException ex) {
            ex.printStackTrace();
            log.log(Level.SEVERE,"Server not started",ex);
        }
    }
}

//https://www.favicon.by/collection

//Maven
//https://www.jetbrains.com/help/idea/work-with-maven-dependencies.html

