package com.epam.Philippov.http.server.engine.view;

import com.epam.Philippov.http.server.engine.Request;
import com.epam.Philippov.http.server.engine.Response;

public interface ViewInterface {
    Response get(Request request);
    Response post(Request request);
}
