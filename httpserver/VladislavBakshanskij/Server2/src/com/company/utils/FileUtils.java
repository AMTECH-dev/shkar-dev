package com.company.utils;

import com.company.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    private static final String STATIC_FOLDER = "static/";
    private static final String PATH_TO_IMAGE_FOLDER = STATIC_FOLDER + "img/";
    private static final String PAGE_FOLDER = STATIC_FOLDER + "pages/";

    public static byte[] readAllBytesFromPage(String pageName) throws URISyntaxException, IOException {
        return readAllBytesFromPath(PAGE_FOLDER + pageName);
    }

    public static byte[] readAllBytesFromStaticFile(String path) throws URISyntaxException, IOException {
        return readAllBytesFromPath(STATIC_FOLDER + path);
    }

    private static byte[] readAllBytesFromPath(String path) throws URISyntaxException, IOException {
        URL resource = getURLResource(path);
        if (resource == null) throw new FileNotFoundException();
        return Files.readAllBytes(Paths.get(resource.toURI()));
    }

    public static URI getImageURI(String imageName) throws URISyntaxException {
        return getURLResource(PATH_TO_IMAGE_FOLDER + imageName).toURI();
    }

    public static String getFileExtension(String fileName) {
        int dotExtension = fileName.lastIndexOf(".");
        return fileName.substring(dotExtension + 1);
    }

    public static BufferedImage getImage(String imageName) throws IOException {
        return ImageIO.read(Main.class.getResource(PATH_TO_IMAGE_FOLDER + imageName));
    }

    public static void writeToFile(String fileName, String message) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(message.getBytes());
        }
    }

    private static URL getURLResource(String pathToResource) {
        return Main.class.getResource(pathToResource);
    }

    public static InputStream getConfigFileStream(String configFileName) {
        return Main.class.getResourceAsStream(configFileName);
    }
}
