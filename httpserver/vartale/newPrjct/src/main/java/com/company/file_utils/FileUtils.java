package com.company.file_utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    private FileUtils() {
    }

    public static Path toAbsPath(String path) {
        return Paths.get(path).toAbsolutePath();
    }

    public static String stringToAbsPathToString(String path) {
        return toAbsPath(path).toString();
    }
}
