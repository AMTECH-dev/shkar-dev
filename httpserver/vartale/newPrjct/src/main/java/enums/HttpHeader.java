package enums;

public enum HttpHeader {
    CONTENT_TYPE("Content-Type");

    private final String headerName;

    HttpHeader(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return headerName;
    }
}
