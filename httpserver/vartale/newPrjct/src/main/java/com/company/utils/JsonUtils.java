package com.company.file_utils;

import com.company.data_processing.ParsingUrl;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    private static final Logger LOGGER = LogManager.getLogger(ParsingUrl.class);
    private static final Gson GSON = new Gson();

    public static String toJSON(Object o) {
        return GSON.toJson(o);
    }
}
