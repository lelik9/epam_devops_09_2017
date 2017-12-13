package com.epam.Philippov.http.server.framework.middleware;

import com.epam.Philippov.http.server.framework.Request;

public interface PreFilter {
    Request call(Request request);
}
