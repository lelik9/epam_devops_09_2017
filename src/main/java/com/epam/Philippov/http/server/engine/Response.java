package com.epam.Philippov.http.server.engine;

import lombok.Getter;

import java.io.BufferedReader;

public class Response implements ResponseInterface{
    @Getter
    final int statusCode;
    @Getter
    final String response;
    @Getter
    final String error;

    public Response(int statusCode, String response) {
        this(statusCode, response, "");
    }

    public Response(int statusCode, String response, String error) {
        this.statusCode = statusCode;
        this.response = response;
        this.error = error;
    }

    public BufferedReader getResponse(){
        System.out.println(response + statusCode + error);
        return null;
    }
}
