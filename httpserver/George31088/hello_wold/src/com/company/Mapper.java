package com.company;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Mapper {
    public static Map<String, String> getParametersFromQueryString(String query) {
        String[] pairs = query.split("&");
        Map<String, String> map = new HashMap<>();
        for (String s : pairs) {
            String[] keyValue = s.split("=");
            if (keyValue.length > 1) {
                map.put(keyValue[0], keyValue[1]);
            }
        }
        return map;
    }

    public static String toJSON(Object o) {
        String gson = new Gson().toJson(o);
        return gson;
    }

}
