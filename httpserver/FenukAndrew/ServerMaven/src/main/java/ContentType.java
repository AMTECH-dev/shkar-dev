import java.util.HashMap;
import java.util.Map;

//https://developer.mozilla.org/ru/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
class ContentType {
    private static Map<String, String> contentTypes = new HashMap<String, String>();

    static {
        contentTypes.put(".bmp", "image/bmp");
        contentTypes.put(".css", "text/css; charset=utf-8");
        contentTypes.put(".gif", "image/gif");
        contentTypes.put(".htm", "text/html; charset=utf-8");
        contentTypes.put(".html", "text/html; charset=utf-8");
        contentTypes.put(".ico", "image/vnd.microsoft.icon");
        contentTypes.put(".jpeg", "image/jpeg");
        contentTypes.put(".jpg", "image/jpeg");
        contentTypes.put(".js", "text/javascript; charset=utf-8");
        contentTypes.put(".json", "application/json; charset=utf-8");
        contentTypes.put(".png", "image/png");
        contentTypes.put(".pdf", "application/pdf");
        contentTypes.put(".tif", "image/tiff");
        contentTypes.put(".tiff", "image/tiff");
        contentTypes.put(".txt", "text/plain; charset=utf-8");
        contentTypes.put(".webp", "image/webp");
        contentTypes.put(".xml", "application/xml; charset=utf-8");
    }

    public static String getExtension(String path) {
        int index = path.lastIndexOf(".");
        if (index == -1) return "";
        else return path.substring(index);
    }

    public static String getContentType(String extension) {
        return contentTypes.get(extension);
    }
}
