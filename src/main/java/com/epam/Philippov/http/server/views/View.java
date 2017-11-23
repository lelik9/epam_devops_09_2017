package com.epam.Philippov.http.server.views;

import lombok.SneakyThrows;

import java.io.*;

public abstract class View implements ViewInterface{
    StringWriter writer = new StringWriter();
    BufferedWriter bufferedWriter = new BufferedWriter(writer);
    PrintWriter outWriter = new PrintWriter(System.out, true);
    FileReader fileReader;
    BufferedReader reader;

    @Override
    @SneakyThrows
    public void get(String query) {
        outWriter.write("404 Resource not found.");
        outWriter.close();
    }

    @Override
    @SneakyThrows
    public void post() {
        outWriter.write("404 Resource not found.");
        outWriter.close();
    }

    @Override
    @SneakyThrows
    public void returnResource(String resourceName) {
        outWriter.write("404 Resource not found.");
        outWriter.close();
    }
}
