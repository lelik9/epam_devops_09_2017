package com.my.http.framework.middleware;


import com.my.http.framework.response.Response;

public interface PostMiddleware {
    Response call(Response response);
}
