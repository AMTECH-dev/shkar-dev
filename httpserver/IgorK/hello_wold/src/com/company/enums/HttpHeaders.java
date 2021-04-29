package com.company.enums;

public enum HttpHeaders {
    CONTENT_TYPE("Content-Type");
    private String name;
    HttpHeaders(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
