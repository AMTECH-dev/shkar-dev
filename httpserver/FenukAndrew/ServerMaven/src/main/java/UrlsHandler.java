import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UrlsHandler implements HttpHandler {
    private static Logger log = Logger.getLogger(UrlsHandler.class.getName());

    @Override
    public void handle(HttpExchange httpExchange) {
        try {
            String method = httpExchange.getRequestMethod();
            log.finest("Method:" + method);
            if (method.equalsIgnoreCase(HttpMethod.GET)) {
                List<String> urls = convertTextFileToList("urls.txt");

                Map<String, Map<String,String>> results =new HashMap<String, Map<String,String>>();
                for(String urlStr : urls) {
                    URL url = new URL(urlStr);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod(HttpMethod.HEAD);

                    if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        //System.out.println(urlConnection.getResponseMessage());
                        //System.out.println(urlConnection.getContentType());
                        Map<String, List<String>> fields = urlConnection.getHeaderFields();
                        Map<String, String> heads = fromHeaderFieldsToMap(fields);
                        results.put(urlStr,heads);
                    }
                    urlConnection.disconnect();
                }

                StringBuilder sb = createHtmlTable(results);

                byte[] buffer=sb.toString().getBytes(StandardCharsets.UTF_8);
                Utils.sendResponseWithBody(httpExchange, HttpCode.OK,"text/html; charset=utf-8",buffer);
            } else
                Utils.sendResponseWithoutBody(httpExchange, HttpCode.BAD_REQUEST);
        } catch (IOException ex) {
            ex.printStackTrace();
            log.log(Level.SEVERE,"Urls",ex);
            Utils.sendResponseWithoutBody(httpExchange, HttpCode.BAD_REQUEST);
        }
    }

    private Map<String, String> fromHeaderFieldsToMap(Map<String, List<String>> fields) {
        Map<String,String> heads=new HashMap<String,String>();
        for (String key : fields.keySet()) {
            StringJoiner sb=new StringJoiner(" ; ");
            for(String val : fields.get(key)) {
                sb.add(val);
            }
            heads.put(key,sb.toString());
        }
        return heads;
    }

    private List<String> convertTextFileToList(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        List<String> results=new ArrayList<>();
        String s;
        while((s=in.readLine())!=null)
        {
            results.add(s);
        }
        return results;
    }

    private StringBuilder createHtmlTable(Map<String,Map<String,String>> results) {
        StringBuilder sb=new StringBuilder();
        sb.append("<html><body><table border=\"1\">");
        for(String urlStr : results.keySet()) {
            sb.append("<tr><td colspan=\"2\" bgcolor=\"#BBBBBB\" >");sb.append(urlStr);sb.append("</td></tr>");
            Map<String,String> record=results.get(urlStr);
            for(Map.Entry<String,String> keyValue : record.entrySet()) {
                sb.append("<tr><td>");sb.append(keyValue.getKey());sb.append("</td><td>");
                sb.append(keyValue.getValue());
                sb.append("</td></tr>");
            }
        }
        sb.append("</table></body></html>");
        return sb;
    }
}
