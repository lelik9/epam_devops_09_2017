package com.epam.Philippov.http.server.engine.framework.middleware;

import com.epam.Philippov.http.server.engine.framework.Request;

public interface PreFilter {
    Request call(Request request);
}
