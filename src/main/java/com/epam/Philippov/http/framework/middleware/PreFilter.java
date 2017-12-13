package com.epam.Philippov.http.framework.middleware;

import com.epam.Philippov.http.framework.Request;

public interface PreFilter {
    Request call(Request request);
}
