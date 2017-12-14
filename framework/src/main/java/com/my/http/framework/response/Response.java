package com.my.http.framework.response;

import java.util.HashMap;
import lombok.Getter;

public class Response implements ResponseInterface{
    @Getter
    protected int statusCode;
    @Getter
    protected String response;
    @Getter
    protected String error;
    @Getter
    protected HashMap<String, String> headers = new HashMap<>();

    public Response(String response) {
        this(200, response, "");
    }

    public Response(int statusCode, String response) {
        this(statusCode, response, "");
    }

    public Response(int statusCode, String response, String error) {
        this.statusCode = statusCode;
        this.response = response;
        this.error = error;
    }

    public Response() {
        this(404, "", "");
    }

    @Override
    public void setHeader(String key, String value) {
        headers.put(key, value);
    }

//    public BufferedReader getResponse(){
//        System.out.println(response + statusCode + error);
//        return null;
//    }
}
