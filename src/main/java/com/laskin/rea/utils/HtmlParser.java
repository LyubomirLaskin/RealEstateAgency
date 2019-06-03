package com.laskin.rea.utils;

import java.io.*;

public class HtmlParser {

    public String read(String htmlPath) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(htmlPath))));

        String line;
        StringBuilder htmlBuilder = new StringBuilder();

        while ((line = reader.readLine()) != null){
            htmlBuilder.append(line);
        }

        return htmlBuilder.toString();
    }
}
