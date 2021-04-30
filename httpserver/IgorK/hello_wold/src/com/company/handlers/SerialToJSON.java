package com.company.handlers;

import com.company.factories.LoggerFactory;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SerialToJSON {
    private static final Logger logger = LoggerFactory.createLogger();
    public static Map<String, String> toJSON(String query) {
        logger.info("Start toJSON method, class SerialToJSON ");
        String[] splitStart = query.split("&");
        String[] splitEnd = null;
        Map<String, String> map = new HashMap<>();
        for (String s : splitStart) {
            splitEnd = s.split("=");
            if (splitEnd[1] != null) {
                map.put(splitEnd[0], splitEnd[1]);
            } else {
                splitEnd[1] = "unknown";
                map.put(splitEnd[0], splitEnd[1]);
            }
        }
        return map;
    }

    public static String returnGSON(Map<String, String> aMap) {
        logger.info("Start returnGSON method, class SerialToJSON ");
        String gson = new Gson().toJson(aMap);
        System.out.println(gson);
        return gson;
    }

}