package com.epam.Philippov.http.server.engine;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileResponse implements ResponseInterface{
    final private String staticPath;
    final private String resourceName;

    public FileResponse(String resourceName) {
        this("/",resourceName);
    }

    public FileResponse(String staticPath, String resourceName) {
        this.staticPath = staticPath;
        this.resourceName = resourceName;
    }

    @Override
    @SneakyThrows
    public BufferedReader getResponse() {
        InputStream resourceReader = Class.class.getResourceAsStream(staticPath+resourceName);
        return new BufferedReader(new InputStreamReader(resourceReader, "utf-8"));
    }
}
