package com.epam.Philippov.http.server.engine;

import lombok.Getter;

import java.util.HashMap;

public class Request {
    @Getter
    final private String url;
    @Getter
    final private String method;
    @Getter
    final private String query;
    @Getter
    final private Class viewClass;
    @Getter
    final private HashMap<String, String> headers;

    public Request(String url, String method, String query, Class viewClass, HashMap<String, String> headers) {
        this.url = url;
        this.method = method;
        this.query = query;
        this.viewClass = viewClass;
        this.headers = headers;
    }
}
