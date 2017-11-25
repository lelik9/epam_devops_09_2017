package com.epam.Philippov.http.server.engine;

import lombok.Getter;

import java.io.BufferedWriter;
import java.util.HashMap;

public class Request {
    @Getter
    final private String url;
    @Getter
    final private String method;
    @Getter
    final private String query;
    @Getter
    final private String data;
    @Getter
    final private Class viewClass;
    @Getter
    final private BufferedWriter out;
    @Getter
    final private HashMap<String, String> headers;

    public Request(String url, String method, String query, String data, Class viewClass, BufferedWriter out, HashMap<? extends String, ? extends String> headers) {
        this.url = url;
        this.method = method;
        this.query = query;
        this.data = data;
        this.viewClass = viewClass;
        this.out = out;
        this.headers = new HashMap<>(headers);
    }
}
