package com.company.data_processing;

import com.google.gson.Gson;

import java.util.Map;

public class Converting {
    public static String convertMapToJSON(Map<String, String> map) {
        return new Gson().toJson(map);
    }
}
