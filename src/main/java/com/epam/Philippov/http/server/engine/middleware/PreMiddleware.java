package com.epam.Philippov.http.server.engine.middleware;

import com.epam.Philippov.http.server.engine.Request;

public interface PreMiddleware {
    Request call(Request request);
}
