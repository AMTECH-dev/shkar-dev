import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

class FileHttpHandler implements HttpHandler {

    private static Logger log = Logger.getLogger(FileHttpHandler.class.getName());

    @Override
    public void handle(HttpExchange exchange) {
        String method = exchange.getRequestMethod();
        System.out.println("Method:" + method);
        log.finest("Method:" + method);
        if (method.equalsIgnoreCase(HttpMethod.GET)) {
            handleGet(exchange);
        } else if (method.equalsIgnoreCase(HttpMethod.POST)) {
            handlePost(exchange);
        }
    }

    final static String[] fields=new String[]{"name","lastname","age","login","password"};

    private void handlePost(HttpExchange exchange) {
        Map<String, String> params = Utils.getParams(Utils.getResponseBody(exchange.getRequestBody()));
        Set<String> requestFields=new HashSet<String>();
        for(String field : fields) {
            if(!params.containsKey(field)) {
                requestFields.add(field);
            }
        }

        if(!requestFields.isEmpty()) {
            String message="<html><body><div style=\"background-color: green;height: 300px;width: 300px;\">Not found next fields:"+requestFields.toString()+"</div></body></html>";
            byte[] data=message.getBytes(StandardCharsets.UTF_8);
            Utils.sendResponseWithBody(exchange, HttpCode.BAD_REQUEST,"text/html; charset=utf-8", data);
        } else {
            String gsonStr = Utils.toJSON(params);
            byte[] gsonB = gsonStr.getBytes(StandardCharsets.UTF_8);
            Utils.sendResponseWithBody(exchange, HttpCode.OK,"application/json; charset=utf-8", gsonB);
        }
    }

    final static Set<String> imageFileNames=new HashSet<String>(Arrays.asList("jpg","jpeg","png","bmp","tif","tiff"));

    private void handleGet(HttpExchange exchange) {
        String fileName = exchange.getRequestURI().getPath();
        //Path localPath=Paths.get("static",fileName).toAbsolutePath();
        Path localPath = Paths.get(fileName.substring(1)).toAbsolutePath();
        String localName = localPath.toString();
        System.out.println("Path:" + localName);
        long sizeFile = 0;
        try {
            sizeFile = Files.size(localPath);
            System.out.println("Size:" + sizeFile);
        } catch (Exception ex) {
            ex.printStackTrace();
            Utils.sendResponseWithoutBody(exchange, HttpCode.NOT_FOUND);
            log.log(Level.WARNING,"Not found",ex);
            return;
        }

        String extension = Utils.getExtension(fileName);

        byte[] buffer;
        String ext = extension.substring(1).toLowerCase();
        if (imageFileNames.contains(ext)) {
            buffer = ProcessImage.process(localName, ext);
        } else {
            buffer = Utils.loadFromLocalFile(localName, (int) sizeFile);
        }

        String content = ContentType.getContentType(extension);
        Utils.sendResponseWithBody(exchange, HttpCode.OK, content, buffer);
    }
}