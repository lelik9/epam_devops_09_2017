package com.epam.Philippov.http.server.engine.framework.middleware;

import com.epam.Philippov.http.server.engine.framework.response.Response;

public interface PostMiddleware {
    Response call(Response response);
}
