package com.company.file_utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class FileUtils {
    private static final String PATH_URL_FILE = "files/urls.txt";

    private FileUtils() {
    }

    public static Path toAbsPath(String path) {
        return Paths.get(path).toAbsolutePath();
    }

    // TODO ОБДУМАТЬ ПЕРЕИМЕНОВАТЬ
    public static String toAbsolutePathString(String path) {
        return toAbsPath(path).toString();
    }

    public static Set<String> getAllUrls() throws IOException  {
        return new HashSet<>(Files.readAllLines(Paths.get(PATH_URL_FILE)));
    }

    public static String getFileExtension(String path) {
        if (Files.isDirectory(toAbsPath(path))) throw new RuntimeException("PATH IS DIRECTORY");
        return path.substring(path.lastIndexOf(".") + 1);
    }
}
