import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

    private static Logger log = Logger.getLogger(Utils.class.getName());

    static String getMapResponseBody(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
            String s;
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            log.log(Level.SEVERE,"Not get Response Body",ex);
        }

        String requestBody = sb.toString();
        //System.out.println("getRequestBody:" + requestBody);
        return requestBody;
    }

    static Map<String,String> getParams(String body) {
        Map<String,String> params=new HashMap<String,String>();
        for(String paramN : body.split("&")) {
            String[] keyValue=paramN.split("=");
            if (keyValue.length<2) continue;
            String key=keyValue[0];
            String value=keyValue[1];
            params.put(key,value);
        }
        if(log.isLoggable((Level.FINEST)))
            log.finest("Successful get params to Map");
        return params;
    }

    static byte[] loadFromLocalFile(String localName, int sizeFile) {//throws IOException {
        byte[] buffer=null;
        try {
            FileInputStream in = new FileInputStream(localName);
            BufferedInputStream bin = new BufferedInputStream(in);

            buffer = new byte[sizeFile];
            bin.read(buffer);
            bin.close();
            in.close();

            if(log.isLoggable((Level.FINE)))
                log.fine("Successful load file:"+localName);
        } catch (IOException ex) {
            ex.printStackTrace();
            log.log(Level.SEVERE,"Not found local file",ex);
        }
        return buffer;
    }

    static String getGson (Map<String, String> params) {
        Gson gson = new Gson();
        //Type gsonType=new TypeToken<HashMap<String,String>>(){}.getType();
        //String gsonStr=gson.toJson(params,gsonType);
        String gsonStr = gson.toJson(params);
        System.out.println(gsonStr);
        return gsonStr;
    }

    //Code 404
    //https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D0%BA%D0%BE%D0%B4%D0%BE%D0%B2_%D1%81%D0%BE%D1%81%D1%82%D0%BE%D1%8F%D0%BD%D0%B8%D1%8F_HTTP#404

    static void httpReturnWithBody(HttpExchange exchange, HttpCodeError codeError, String content, byte[] buffer) {
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.getResponseHeaders().add("Content-type", content);
            exchange.sendResponseHeaders(codeError.get(), buffer.length);
            os.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            ex.printStackTrace();
            log.log(Level.WARNING,"Not get response body",ex);
        }
    }

    static void httpReturnWithoutBody(HttpExchange exchange,HttpCodeError codeError) {
        try {
            OutputStream os = exchange.getResponseBody();
            exchange.sendResponseHeaders(codeError.get(), 0);
            os.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            log.log(Level.WARNING,"Not get response body",ex);
        }
    }
}

enum HttpCodeError{
    OK(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    PAYMENT_REQUIRED(402),
    FORBIDDEN(403),
    NOT_FOUND(404);

    private int code;
    HttpCodeError(int code) {
        this.code=code;
    }
    public int get() { return code;}
}
