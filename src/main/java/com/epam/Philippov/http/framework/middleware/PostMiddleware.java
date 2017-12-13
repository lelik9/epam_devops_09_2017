package com.epam.Philippov.http.framework.middleware;

import com.epam.Philippov.http.framework.response.Response;

public interface PostMiddleware {
    Response call(Response response);
}
