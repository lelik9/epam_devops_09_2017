package com.my.http.framework.view;


import com.my.http.framework.Request;
import com.my.http.framework.response.Response;

public interface ViewInterface {
    Response get(Request request);
    Response post(Request request);
}
