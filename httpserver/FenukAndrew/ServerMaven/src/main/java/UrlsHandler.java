import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UrlsHandler implements HttpHandler {
    private static Logger log = Logger.getLogger(UrlsHandler.class.getName());

    private Map<String,String> results=new HashMap<String,String>();

    @Override
    public void handle(HttpExchange httpExchange) {
        try {
            String method = httpExchange.getRequestMethod();
            log.finest("Method:" + method);
            if (method.equalsIgnoreCase("GET")) {
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("urls.txt")));
                String s;
                while((s=in.readLine())!=null)
                {
                    results.put(s,null);
                }

                byte[] buffer=new byte[0];
                Utils.httpReturnWithBody(httpExchange,HttpCodeError.OK,"text/html; charset=utf-8",buffer);

            } else
                Utils.httpReturnWithoutBody(httpExchange,HttpCodeError.BAD_REQUEST);
        } catch (IOException ex) {
            ex.printStackTrace();
            log.log(Level.SEVERE,"Urls",ex);
            Utils.httpReturnWithoutBody(httpExchange,HttpCodeError.BAD_REQUEST);
        }

    }
}
