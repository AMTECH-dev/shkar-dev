package com.company.data_processing;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {

    private Utils() {
    }

    public static Path toAbsPath(String path) {
        return Paths.get(path).toAbsolutePath();
    }

    public static String toAbsPathToString(String path) {
        return Paths.get(path).toAbsolutePath().toString();
    }
}
