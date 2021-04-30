package com.company.data_processing;

import com.company.enums.HttpMethod;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class ParsingUrl {

    public Map<String, String> requestHttpHeadersFromUrl(String url) throws IOException {
        Map<String, String> headers = new HashMap<>();
        URL address = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) address.openConnection();

        try {
            connection.setRequestMethod(HttpMethod.HEAD.getMethod());

            Map<String, List<String>> headerFields = connection.getHeaderFields();

            for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                String headerName = entry.getKey();
                String headerValue = String.join(",", entry.getValue());
                headers.put(headerName, headerValue);
            }
            return headers;

        } finally {
            if (connection != null) connection.disconnect();
        }
    }
}
