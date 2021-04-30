package com.company.utils;

import com.company.http.HttpMethod;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebClient {
    public Map<String, String> getResponseHeaders(String url) throws IOException {
        var urlAddress = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlAddress.openConnection();
        try {
            connection.setRequestMethod(HttpMethod.HEAD);
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) return null;
            Map<String, String> headerMap = new HashMap<>();
            for (Map.Entry<String, List<String>> headerEntry : connection.getHeaderFields().entrySet()) {
                String headerName = headerEntry.getKey();
                String headerValue = String.join(",", headerEntry.getValue());
                headerMap.put(headerName, headerValue);
            }
            return headerMap;
        } finally {
            if (connection != null) connection.disconnect();
        }

    }
}
