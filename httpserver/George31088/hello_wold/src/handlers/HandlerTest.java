package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class HandlerTest implements HttpHandler {

    public void handle(HttpExchange t) throws IOException {
        StringBuilder sb = new StringBuilder();

        if (t.getRequestMethod().equalsIgnoreCase("POST")) {
            String data;
           BufferedReader br = new BufferedReader(new InputStreamReader(t.getRequestBody()));
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }

            byte[] response = String.format("Ваши параметры: %s", sb).getBytes();
            t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            t.sendResponseHeaders(200, response.length);
            writeToFile(sb.toString());
            OutputStream os = t.getResponseBody();
            os.write(response);
            os.close();
        } else {
            String line;
            BufferedReader io = new BufferedReader(new FileReader("test.html"));
            while ((line = io.readLine()) != null) {
                sb.append(line);
            }

            String result = sb.toString();
            io.close();

            byte[] response = result.getBytes(StandardCharsets.UTF_8);
            t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            t.sendResponseHeaders(200, response.length);
            OutputStream os = t.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    public void writeToFile(String s) throws IOException {
        FileWriter fw = new FileWriter("UserInfo.txt", false);
        fw.write(s);
        fw.flush();
    }
}
