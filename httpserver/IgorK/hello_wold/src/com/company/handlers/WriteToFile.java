package com.company.handlers;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    public static void writeToFile(String s) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter("UserInfo.txt", false);
        } catch (IOException e) {
            e.getMessage();
        }
        fw.write(s);
        fw.flush();
    }
}