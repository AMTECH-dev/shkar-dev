package amtech.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import amtech.registry.TemporaryData;


public class RegFormHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestType = httpExchange.getRequestMethod();
        StringBuilder sb = new StringBuilder();

        if (requestType.equalsIgnoreCase(TemporaryData.POST)) {

            System.out.println("Req body: " + httpExchange.getRequestBody());
            try (BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))) {

            	String data;
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                    System.out.println("Appended: " + data);
                }

                System.out.println("sb = '" + sb.toString() + "'."); //delete this line

                sb.append("<hr><h4><a href=\"page.html\">Continue</a>");

                getUserData(sb.toString().split("&"));

                httpExchange.getResponseHeaders().add(TemporaryData.CONTENT_TYPE, TemporaryData.TEXT_HTML);
                System.out.println("Writing to stream..."); //delete this line
                writeResponse(httpExchange, sb.toString().getBytes());

            } catch (IOException e) {
            	e.printStackTrace();
            }
            
        } else if (requestType.equalsIgnoreCase(TemporaryData.GET)) {
        	byte[] response = Files.readAllBytes(Path.of(Paths.get("./pages/reg.html").toUri()));
        	httpExchange.getResponseHeaders().add(TemporaryData.CONTENT_TYPE, TemporaryData.TEXT_HTML);
            writeResponse(httpExchange, response);
        }
    }
    
    private void getUserData(String[] splittedUserData) {
    	TemporaryData.clientName = splittedUserData[0].replace("input=", "");
        TemporaryData.clientSex = splittedUserData[1].replace("sex=", "");
	}

	private void writeResponse(HttpExchange httpExchange, byte[] writeData) {
        if (writeData.length <= 0) {throw new RuntimeException("writeData is NULL or empty");}

        try {
        	httpExchange.sendResponseHeaders(TemporaryData.OK, writeData.length);

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(writeData);
            } catch (Exception e) {
            	e.printStackTrace();
            }
            
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
}