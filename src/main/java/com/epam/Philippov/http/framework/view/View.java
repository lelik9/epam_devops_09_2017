package com.epam.Philippov.http.framework.view;

import com.epam.Philippov.http.framework.Request;
import com.epam.Philippov.http.framework.response.Response;

import lombok.SneakyThrows;

public abstract class View implements ViewInterface {
    public String staticPath = "/site/";

    @Override
    @SneakyThrows
    public Response get(Request request) {
        return new Response(404, "", "404 Resource not found.");
    }

    @Override
    @SneakyThrows
    public Response post(Request request) {
        return new Response(404, "", "404 Resource not found.");
    }
}
