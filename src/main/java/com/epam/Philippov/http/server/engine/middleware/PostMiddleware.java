package com.epam.Philippov.http.server.engine.middleware;

import com.epam.Philippov.http.server.engine.ResponseInterface;

public interface PostMiddleware {
    ResponseInterface call(ResponseInterface response);
}
