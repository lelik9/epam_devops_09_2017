package com.epam.Philippov.http.server.framework.errors;

import com.epam.Philippov.http.server.framework.response.ResponseInterface;

public class HttpException extends RuntimeException implements ResponseInterface{
    @Override
    public void setHeader(String key, String value) {

    }
}
