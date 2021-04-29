package com.company.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ParseUrlsFile {


    public static void main(String[] args) throws IOException {

        List<String> list = new ArrayList<>();
        Scanner in = new Scanner(new File("urls.txt"));
        while (in.hasNext())
            list.add(in.nextLine());
        in.close();
        for (int i = 0; i < list.size(); i++) {

            URL url = new URL(list.get(i));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("HEAD");
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Map<String, List<String>> requestProperties = con.getRequestProperties();
                System.out.println(requestProperties);
            }
            con.disconnect();
        }

    }
}

