package enums;

public enum HttpContentType {
    HTML("text/html; charset=UTF-8"),
    PNG("image/png"),
    CSS("text/css"),
    JAVASCRIPT("text/javascript");

    private final String CONTENT_TYPE;

    HttpContentType(String contentType) {
        this.CONTENT_TYPE = contentType;
    }

    public String getContentType() {
        return CONTENT_TYPE;
    }
}
