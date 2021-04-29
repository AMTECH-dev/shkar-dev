package com.company.handlers;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class SerialToJSON {
    public static void toJSON(String query) {
        String[] splitStart = query.split("&");
        String[] splitEnd = null;
        Map<String, String> map = new HashMap<>();
        for (String s : splitStart) {
            splitEnd = s.split("=");
            map.put(splitEnd[0], splitEnd[1]);
        }
        String gson = new Gson().toJson(map);
        System.out.println(gson);

    }
}