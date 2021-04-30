package com.company.dto;

import java.util.Map;

public final class ResponseHeaderDTO {
    private final Map<String, String> responseHeaders;
    private final String url;

    public ResponseHeaderDTO(String url, Map<String, String> responseHeaders) {
        this.url = url;
        this.responseHeaders = responseHeaders;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public String getUrl() {
        return url;
    }
}
