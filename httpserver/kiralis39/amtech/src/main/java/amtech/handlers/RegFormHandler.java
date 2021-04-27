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
    
        if (requestType.equalsIgnoreCase(TemporaryData.POST)) {
        	
            try (BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))) {
            	StringBuilder sb = new StringBuilder();
            	
            	String data;
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }

                sb.append("<hr><h4><a href=\"page.html\">Continue</a>");

                writeResponse(httpExchange, sb.toString().getBytes());
                getUserData(sb.toString().split("&"));
                
            } catch (IOException e) {
            	e.printStackTrace();
            }
            
        } else if (requestType.equalsIgnoreCase(TemporaryData.GET)) {
        	byte[] response = Files.readAllBytes(Path.of(Paths.get("./pages/reg.html").toUri()));
        	httpExchange.getResponseHeaders().add(TemporaryData.CONTENT_TYPE, TemporaryData.TEXT_HTML);
            writeResponse(httpExchange, response);
        }
    }
    
    private static void getUserData(String[] splittedUserData) {
    	TemporaryData.clientName = splittedUserData[0].replace("input=", "");
        TemporaryData.clientSex = splittedUserData[1].replace("sex=", "");
	}

	private static void writeResponse(HttpExchange httpExchange, byte[] writeData) {
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