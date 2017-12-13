package com.my.http.framework.errors;


import com.my.http.framework.response.ResponseInterface;

public class HttpException extends RuntimeException implements ResponseInterface {
    @Override
    public void setHeader(String key, String value) {

    }
}
