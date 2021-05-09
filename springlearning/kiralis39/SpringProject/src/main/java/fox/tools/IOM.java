package fox.tools;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class IOM {
    private static List<Properties> propsArray = new ArrayList<>();

    public static void addProperty(String name, File file) {
        if (Files.notExists(file.toPath())) {

            File parentDir = file.getParentFile();
            parentDir.mkdirs();

            try {
                Files.createFile(file.toPath());
                try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file))) {
                    osw.write("PROP_NAME=" + name);
                } catch (IOException e) {
                    System.err.println("WARN! CANT WRITE THE PROP-FILE NAME!");
                    e.printStackTrace();
                }
            } catch (IOException e) {
                System.err.println("ERROR! CANT CREATE THE PROP-FILE!");
                e.printStackTrace();
                return;
            }
        };

        Properties newProp = new Properties();
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            newProp.load(isr);
        } catch (Exception e) {
            System.err.println("WARN! CANT LOAD THE FILE INTO PROPERTY!");
            e.printStackTrace();
        }

        propsArray.add(newProp);
    }

    public static String get(String key) {
        for (Properties p : propsArray) {
            if (p.containsKey(key)) {
                return (String) p.getOrDefault(key, "NA");
            }
        }

        return null;
    }

    public static void set(String propName, String key, String value, boolean replace) {
        for (Properties p : propsArray) {
            if (p.get("PROP_NAME").equals(propName)) {
                if (p.containsKey(key)) {
                    if (replace) p.put(key, value);
                } else {
                    p.put(key, value);
                }
            }
        }
    }
}
