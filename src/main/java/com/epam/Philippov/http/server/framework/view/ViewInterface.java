package com.epam.Philippov.http.server.framework.view;

import com.epam.Philippov.http.server.framework.Request;
import com.epam.Philippov.http.server.framework.response.Response;

public interface ViewInterface {
    Response get(Request request);
    Response post(Request request);
}
