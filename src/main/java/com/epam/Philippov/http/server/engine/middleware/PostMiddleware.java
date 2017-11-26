package com.epam.Philippov.http.server.engine.middleware;

import com.epam.Philippov.http.server.engine.Response;

public interface PostMiddleware {
    Response call(Response response);
}
