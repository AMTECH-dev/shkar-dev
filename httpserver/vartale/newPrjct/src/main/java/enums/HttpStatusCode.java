package enums;

public enum HttpStatusCode {
    INFORMATIONAL(100),
    SUCCESS(200),
    REDIRECTION(300),
    CLIENT_ERROR(400),
    SERVER_ERROR(500);

    private final int statusCode;

    HttpStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
