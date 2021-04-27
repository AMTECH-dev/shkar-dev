package com.company.utils;

import com.company.Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    private static final String STATIC_FOLDER = "static/";
    private static final String PAGE_FOLDER = STATIC_FOLDER + "pages/";

    public static byte[] readAllBytesFromPage(String pageName) throws URISyntaxException, IOException {
        return readAllBytesFromPath(PAGE_FOLDER + pageName);
    }

    public static byte[] readAllBytesFromStaticFile(String path) throws URISyntaxException, IOException {
        return readAllBytesFromPath(STATIC_FOLDER + path);
    }

    private static byte[] readAllBytesFromPath(String path) throws URISyntaxException, IOException {
        URL resource = Main.class.getResource(path);
        if (resource == null) throw new FileNotFoundException();
        return Files.readAllBytes(Paths.get(resource.toURI()));
    }

}
