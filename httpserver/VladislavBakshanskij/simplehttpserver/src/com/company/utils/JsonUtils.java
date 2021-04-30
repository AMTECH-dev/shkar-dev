package com.company.utils;

import com.google.gson.Gson;

public class JsonUtils {
    private static final Gson GSON = new Gson();

    private JsonUtils() {
    }

    public static String toJson(Object o) {
        return GSON.toJson(o);
    }
}
