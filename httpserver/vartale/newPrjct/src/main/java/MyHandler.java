import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;


class MyHandler implements HttpHandler {
    StringBuilder sb;

    @Override
    public void handle(HttpExchange httpExchange) {
        sb = new StringBuilder();
        String data;
        byte[] response;
        String requestType = httpExchange.getRequestMethod();

        if (requestType.equalsIgnoreCase("post")) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))) {
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }

                response = sb.toString().getBytes();
                writeResponse(httpExchange, response);
            } catch (IOException e) {e.printStackTrace();}
        } else if (requestType.equalsIgnoreCase("get")) {
            try (BufferedReader br = new BufferedReader(new FileReader("./newHtml.html"))) {
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }

                response = sb.toString().getBytes();
                writeResponse(httpExchange, response);
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    private void writeResponse(HttpExchange httpExchange, byte[] writeData) {
        try {
            httpExchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            httpExchange.sendResponseHeaders(200, writeData.length);

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(writeData);
            } catch (Exception e) {e.printStackTrace();}
        } catch (IOException e) {e.printStackTrace();}
    }
}
