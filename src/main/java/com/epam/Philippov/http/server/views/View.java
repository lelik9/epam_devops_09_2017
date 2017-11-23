package com.epam.Philippov.http.server.views;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.charset.StandardCharsets;

public abstract class View implements ViewInterface{
//    StringWriter writer = new StringWriter();
//    BufferedWriter bufferedWriter = new BufferedWriter(writer);
    PrintWriter outWriter = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);
    String staticPath = "/site/";

    @Override
    @SneakyThrows
    public void get(String query) {
        try {
            outWriter.write("404 Resource not found.");
        }finally {
            outWriter.close();
        }
    }

    @Override
    @SneakyThrows
    public void post() {
        try {
            outWriter.write("404 Resource not found.");
        }finally {
            outWriter.close();
        }
    }

    @Override
    @SneakyThrows
    public void returnResource(String resourceName) {
        try (InputStream resourceReader = Class.class.getResourceAsStream(staticPath+resourceName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "utf-8"))){

                for(String line = reader.readLine(); line != null; line = reader.readLine()) {
                    outWriter.write(line + "\n");
                }

            }catch (NullPointerException e){
                outWriter.write("404 File not found.");
        } finally {
            outWriter.close();
        }
    }
}
