package com.epam.Philippov.http.server.engine.framework.view;

import com.epam.Philippov.http.server.engine.framework.Request;
import com.epam.Philippov.http.server.engine.framework.response.Response;

public interface ViewInterface {
    Response get(Request request);
    Response post(Request request);
}
