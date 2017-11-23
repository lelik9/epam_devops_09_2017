package com.epam.Philippov.http.server.views;

import lombok.SneakyThrows;

import java.io.*;

public abstract class View implements ViewInterface{
    StringWriter writer = new StringWriter();
    BufferedWriter bufferedWriter = new BufferedWriter(writer);
    PrintWriter outWriter = new PrintWriter(System.out, true);
    InputStream resourceReader;
    BufferedInputStream reader;

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
        try {
            resourceReader = Class.class.getResourceAsStream(resourceName);
            reader = new BufferedInputStream(resourceReader);

            while (reader.available() > 0){
                outWriter.write(reader.read());
            }
        } catch (FileNotFoundException e) {
            outWriter.write("404 File not found.");
        } finally {
            outWriter.close();
            reader.close();
        }
    }
}
