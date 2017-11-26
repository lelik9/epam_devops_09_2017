package com.epam.Philippov.http.server.engine;

import com.epam.Philippov.http.server.engine.view.View;
import lombok.Getter;
import lombok.Setter;

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
    final private BufferedWriter out;
    @Getter
    final private HashMap<String, String> headers;
    @Getter
    @Setter
    private Class viewClass;

    public Request(String url, String method, String query, String data, BufferedWriter out, HashMap<? extends String, ? extends String> headers) {
        this.url = url;
        this.method = method;
        this.query = query;
        this.data = data;
        this.out = out;
        this.headers = new HashMap<>(headers);
    }
}
