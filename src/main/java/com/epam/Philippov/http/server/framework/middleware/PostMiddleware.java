package com.epam.Philippov.http.server.framework.middleware;

import com.epam.Philippov.http.server.framework.response.Response;

public interface PostMiddleware {
    Response call(Response response);
}
