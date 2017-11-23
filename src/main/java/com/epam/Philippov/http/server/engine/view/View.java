package com.epam.Philippov.http.server.engine.view;

import com.epam.Philippov.http.server.engine.Request;
import com.epam.Philippov.http.server.engine.Response;
import com.epam.Philippov.http.server.engine.ResponseInterface;

import lombok.SneakyThrows;

public abstract class View implements ViewInterface {
    public String staticPath = "/site/";

    @Override
    @SneakyThrows
    public ResponseInterface get(Request request) {
        return new Response(404, "", "404 Resource not found.");
    }

    @Override
    @SneakyThrows
    public ResponseInterface post(Request request) {
        return new Response(404, "", "404 Resource not found.");
    }
}
