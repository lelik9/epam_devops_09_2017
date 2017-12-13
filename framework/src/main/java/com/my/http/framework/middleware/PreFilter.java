package com.my.http.framework.middleware;


import com.my.http.framework.Request;

public interface PreFilter {
    Request call(Request request);
}
