package com.epam.Philippov.http.server.engine.middleware;

import com.epam.Philippov.http.server.engine.Request;

public interface PreFilter {
    Request call(Request request);
}
