package com.company.enums;

public enum HttpMethod {
    POST("POST"),
    GET("GET"),
    HEAD("HEAD");

    private final String method;

    HttpMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
