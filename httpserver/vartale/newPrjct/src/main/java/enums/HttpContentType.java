package enums;

public enum HttpContentType {
    HTML("text/html; charset=UTF-8");

    private final String contentType;

    HttpContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}
