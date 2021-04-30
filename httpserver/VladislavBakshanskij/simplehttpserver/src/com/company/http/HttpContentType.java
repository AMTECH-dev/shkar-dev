package com.company.http;

import com.company.utils.FileUtils;

public enum HttpContentType {
    HTML("text/html", "html", "htm"),
    PNG("image/png", "png"),
    CSS("text/css", "css"),
    JAVASCRIPT("text/javascript", "js", "javascript"),
    JSON("application/json");
    private static final String FORMAT_WITH_CHARSET = "%s; charset=utf-8";

    private final String value;
    private final String[] alternatives;

    HttpContentType(String value, String... alternatives) {
        this.value = value;
        this.alternatives = alternatives;
    }

    public String getValue() {
        return value;
    }

    public String getFormattedWithCharset() {
        return String.format(FORMAT_WITH_CHARSET, value);
    }

    public static HttpContentType getByFileName(String fileName) {
        return getByFileExtension(FileUtils.getFileExtension(fileName));
    }

    public static HttpContentType getByFileExtension(String fileExtension) {
        for (HttpContentType contentType : values()) {
            for (String alternative : contentType.alternatives) {
                if (alternative.equalsIgnoreCase(fileExtension)) {
                    return contentType;
                }
            }
        }

        return null;
    }
}